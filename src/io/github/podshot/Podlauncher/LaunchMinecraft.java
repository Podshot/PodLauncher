package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.extras.DownloadVersionList;
import io.github.podshot.Podlauncher.files.LauncherConfig;
import io.github.podshot.Podlauncher.gui.ErrorGUI;
import io.github.podshot.Podlauncher.gui.MainGUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import sk.tomsik68.mclauncher.api.common.ILaunchSettings;
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
	private String username;
	private File gameDir;
	private String password;
	private DownloadVersionList dwl;
	private String RAMinitHeap;
	private String RAMmaxHeap;

	/**
	 * Starts the Launch methods for the specified profile
	 * @param profile The profile to launch
	 */
	@SuppressWarnings("unchecked")
	public LaunchMinecraft(String profile) {
		boolean shouldFixProfile = false;
		dwl = new DownloadVersionList();
		dwl.addObserver(this);

		LauncherConfig.updateLastProfile(profile);

		JSONObject profileJSON = LauncherConfig.getProfile(profile, false);
		this.username = (String) profileJSON.get("Username");
		this.password = (String) profileJSON.get("Password");
		this.gameDir = new File((String) profileJSON.get("Game Directory"));
		this.versionToLaunch = (String) profileJSON.get("Minecraft Version");
		this.RAMinitHeap = (String) profileJSON.get("Init RAM Heap");
		this.RAMmaxHeap = (String) profileJSON.get("Max RAM Heap");
		if (this.versionToLaunch.equals("<Latest Release>")) {
			this.versionToLaunch = GetMinecraftVersions.getLatestRelease();
		}
		if (this.versionToLaunch.equals("<Latest Snapshot>")) {
			this.versionToLaunch = GetMinecraftVersions.getLatestSnapshot();
		}
		if (this.RAMinitHeap == null || this.RAMmaxHeap == null) {
			shouldFixProfile = true;
		}

		if (this.RAMinitHeap == null) {
			this.RAMinitHeap = "256M";
			profileJSON.put("Init RAM Heap", this.RAMinitHeap);
		}
		if (this.RAMmaxHeap == null) {
			this.RAMmaxHeap = "1G";
			profileJSON.put("Max RAM Heap", this.RAMmaxHeap);
		}
		if (shouldFixProfile) {
			LauncherConfig.addProfileFromJSON(profileJSON);
		}
		dwl.start();
		/*
		try {
			this.launch();
		} catch (Exception e) {
			new ErrorGUI(e);
			if (PodLauncher.isDevMode()) {
				e.printStackTrace();
			}
		}
		 */

	}

	/**
	 * Logins to Mojang's servers
	 * @param mc The Minecraft instance to launch from
	 * @return An ISession for the specified profile
	 * @throws Exception Thrown in case something doesn't behave
	 */
	public ISession login(IMinecraftInstance mc) throws Exception {
		ISession result = null;
		IProfile profile = new LegacyProfile(this.username, new String(this.password));
		YDLoginService service = new YDLoginService();
		service.load(mc.getLocation());

		result = service.login(profile);
		return result;
	}

	/**
	 * Launch method to launch the entire game
	 * @param ver 
	 * @throws Exception Thrown in case launching doesn't work
	 */
	public void launch(IVersion ver) throws Exception {
		/*
		while (dwl.isAlive() && iversionToLaunch == null) {
			System.out.println("Looking for Version to launch...");
		}
		 */


		MinecraftInstance mc = new MinecraftInstance(this.gameDir);

		ISession session = this.login(mc);
		ver.getInstaller().install(ver, mc, MainGUI.getInstance());
		Process proc = ver.getLauncher().launch(session, mc, null, ver, new ILaunchSettings() {

			@Override
			public List<String> getCommandPrefix() {
				return null;
			}

			@Override
			public Map<String, String> getCustomParameters() {
				return null;
			}

			@Override
			public String getHeap() {
				return RAMmaxHeap;
			}

			@Override
			public String getInitHeap() {
				return RAMinitHeap;
			}

			@Override
			public List<String> getJavaArguments() {
				return null;
			}

			@Override
			public File getJavaLocation() {
				return null;
			}

			@Override
			public boolean isErrorStreamRedirected() {
				return true;
			}

			@Override
			public boolean isModifyAppletOptions() {
				return false;
			}

		});

		BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));  
		while (isProcessAlive(proc)) {  
			String line = br.readLine();  
			if (line != null && line.length() > 0) {
				System.out.println(line);
				if (line.contains("textures-atlas")) {
					br.close();
					System.exit(0);
				}  
			}
		}
	}

	/**
	 * Check if the specified process is still running
	 * @param proc The Process to check on
	 * @return True if the Process is still running, false otherwise
	 */
	private boolean isProcessAlive(Process proc) {
		try {
			proc.exitValue();
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * Gets all available versions to launch
	 */
	@Override
	public void onUpdate(IObservable<IVersion> arg0, IVersion ver) {
		if (ver.getId().equals(this.versionToLaunch)) {
			try {
				this.launch(ver);
			} catch (Exception e) {
				new ErrorGUI(e);
			}
		}
	}
}
