package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.extras.DownloadUpdater;
import io.github.podshot.Podlauncher.files.LauncherConfig;
import io.github.podshot.Podlauncher.gui.MainGUI;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class PodLauncher {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("-updated")) {
				DownloadUpdater.cleanup();
			}
		}
		DownloadUpdater.start(new URL("https://dl.dropbox.com/s/ephf07kxtohp6yw/Test-Updater.jar"));
		File launcherFolder = new File("PodLauncher");
		if (!(launcherFolder.exists())) {
			launcherFolder.mkdir();
		}
		new LauncherConfig();

		MainGUI mainGUI = new MainGUI();
		mainGUI.setVisible(true);

	}

	public static String getVersion() {
		return "0.0.1";
	}

	public static String getDevelopmentStage() {
		return "alpha";
	}

	public static boolean inDebugMode() {
		return true;
	}

	public static boolean isDevMode() {
		return true;
	}
}
