package mc.podshot.launcher.main;

import java.io.File;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import sk.tomsik68.mclauncher.api.common.ILaunchSettings;
import sk.tomsik68.mclauncher.api.common.IObservable;
import sk.tomsik68.mclauncher.api.common.IObserver;
import sk.tomsik68.mclauncher.api.login.IProfile;
import sk.tomsik68.mclauncher.api.login.ISession;
import sk.tomsik68.mclauncher.api.versions.IVersion;
import sk.tomsik68.mclauncher.impl.common.mc.MinecraftInstance;
import sk.tomsik68.mclauncher.impl.login.legacy.LegacyLoginService;
import sk.tomsik68.mclauncher.impl.login.legacy.LegacyProfile;
import sk.tomsik68.mclauncher.impl.versions.mcassets.MCAssetsVersion;
import sk.tomsik68.mclauncher.impl.versions.mcassets.MCAssetsVersionList;

public class launch {

	public launch(final String username, final String Password) {
		final MinecraftInstance mc = new MinecraftInstance(new File("testmc"));
		MCAssetsVersionList list = new MCAssetsVersionList();
		list.addObserver(new IObserver<IVersion>() {

			@Override
			public void onUpdate(IObservable<IVersion> observable, IVersion changed) {
				if(changed.getId().equalsIgnoreCase("11w47a")){
					IProfile profile = new LegacyProfile(username, Password);
					LegacyLoginService lls = new LegacyLoginService();
					ISession session = null;
					try {
						session = lls.login(profile);
						System.out.println("Legacy Login: " + session.getSessionID());
					} catch (Exception e) {
						e.printStackTrace();
						//fail(e.getMessage());
					}
					System.out.println("Found version: " + changed.getDisplayName());
					try {
						Process proc = changed.getLauncher().launch(session, mc, null, (MCAssetsVersion) changed, new ILaunchSettings() {

							public boolean isModifyAppletOptions() {
								return false;
							}

							public boolean isErrorStreamRedirected() {
								return true;
							}

							public String getWorkingDirectory() {
								return null;
							}

							public String getInitHeap() {
								return "2G";
							}

							public String getHeap() {
								return "3G";
							}

							public Map<String, String> getCustomParameters() {
								return null;
							}

							public List<String> getCommandPrefix() {
								return Collections.emptyList();
							}
						});
						BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
						String line;
						while(isProcessAlive(proc)) {
							line = br.readLine();
							if(line != null && line.length() > 0) {
								System.out.println(line);
							} else {
								break;
							}
						}
					} catch(Exception e) {
						//fail(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		});
		try {
			list.startDownload();
		} catch (Exception e) {
			e.printStackTrace();
			//fail(e.getMessage());
		}
	}

	protected boolean isProcessAlive(Process proc) {
		try {
			proc.exitValue();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}