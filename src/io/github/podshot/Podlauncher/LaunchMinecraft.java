package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.extras.DefaultLaunchSettings;
import io.github.podshot.Podlauncher.extras.DownloadVersionList;
import io.github.podshot.Podlauncher.files.LauncherConfig;
import io.github.podshot.Podlauncher.gui.MainGUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;

import sk.tomsik68.mclauncher.api.common.IObservable;
import sk.tomsik68.mclauncher.api.common.IObserver;
import sk.tomsik68.mclauncher.api.common.mc.IMinecraftInstance;
import sk.tomsik68.mclauncher.api.login.IProfile;
import sk.tomsik68.mclauncher.api.login.ISession;
import sk.tomsik68.mclauncher.api.versions.IVersion;
import sk.tomsik68.mclauncher.impl.common.mc.MinecraftInstance;
import sk.tomsik68.mclauncher.impl.login.legacy.LegacyProfile;
import sk.tomsik68.mclauncher.impl.login.yggdrasil.YDLoginService;

public class LaunchMinecraft implements IObserver<IVersion> {

	private String versionToLaunch;
	private IVersion iversionToLaunch;
	private String username;
	private File gameDir;
	private String password;
	private DownloadVersionList dwl;

	public LaunchMinecraft(String profile) {
		dwl = new DownloadVersionList();
		dwl.addObserver(this);

		dwl.start();
		
		LauncherConfig.updateLastProfile(profile);

		JSONObject profileJSON = LauncherConfig.getProfile(profile, false);
		this.username = (String) profileJSON.get("Username");
		this.password = (String) profileJSON.get("Password");
		this.gameDir = new File((String) profileJSON.get("Game Directory"));
		this.versionToLaunch = (String) profileJSON.get("Minecraft Version");
		try {
			this.launch();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ISession login(IMinecraftInstance mc) throws Exception {
		ISession result = null;
		IProfile profile = new LegacyProfile(this.username, new String(this.password));
		YDLoginService service = new YDLoginService();
		service.load(mc.getLocation());

		result = service.login(profile);
		return result;
	}

	public void launch() throws Exception {
		MinecraftInstance mc = new MinecraftInstance(this.gameDir);

		ISession session = this.login(mc);
		iversionToLaunch.getInstaller().install(iversionToLaunch, mc, MainGUI.getInstance());
		Process proc = iversionToLaunch.getLauncher().launch(session, mc, null, iversionToLaunch, new DefaultLaunchSettings());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream())); 
		while (isProcessAlive(proc)) { 
			String line = br.readLine(); 
			if (line != null && line.length() > 0) 
				System.out.println(line); 
		}
	}

	private boolean isProcessAlive(Process proc) {
		try {
			proc.exitValue();
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	@Override
	public void onUpdate(IObservable<IVersion> arg0, IVersion arg1) {
		if (arg1.getId().equals(this.versionToLaunch)) {
			this.iversionToLaunch = arg1;
			try {
				this.launch();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
