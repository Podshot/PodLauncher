package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.extras.DefaultLaunchSettings;
import io.github.podshot.Podlauncher.extras.DownloadVersionList;
import io.github.podshot.Podlauncher.files.LauncherConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;

import sk.tomsik68.mclauncher.api.common.IObservable;
import sk.tomsik68.mclauncher.api.common.IObserver;
import sk.tomsik68.mclauncher.api.login.IProfile;
import sk.tomsik68.mclauncher.api.login.IProfileIO;
import sk.tomsik68.mclauncher.api.login.ISession;
import sk.tomsik68.mclauncher.api.versions.IVersion;
import sk.tomsik68.mclauncher.impl.common.Platform;
import sk.tomsik68.mclauncher.impl.common.mc.MinecraftInstance;
import sk.tomsik68.mclauncher.impl.login.legacy.LegacyProfile;
import sk.tomsik68.mclauncher.impl.login.yggdrasil.YDLoginService;
import sk.tomsik68.mclauncher.impl.login.yggdrasil.io.YDProfileIO;

public class LaunchMinecraft implements IObserver<IVersion> {

	private String versionToLaunch;
	private IVersion iversionToLaunch;
	private String username;
	private File gameDir;
	private String password;

	public LaunchMinecraft(String profile) {
		DownloadVersionList dwl = new DownloadVersionList();
		dwl.addObserver(this);

		dwl.start();
		JSONObject profileJSON = LauncherConfig.getProfile(profile);
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

	public ISession login() {
		ISession result = null;
		IProfile profile = new LegacyProfile(this.username, new String(this.password));
		YDLoginService service = new YDLoginService();

		try {
			result = service.login(profile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void launch() throws Exception {
		MinecraftInstance mc = new MinecraftInstance(this.gameDir);

		ISession session = this.login();
		iversionToLaunch.getInstaller().install(iversionToLaunch, mc, null);
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
			System.out.println("Found version to launch");
			this.iversionToLaunch = arg1;
		}
	}



}
