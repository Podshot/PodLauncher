package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.files.LauncherConfig;
import io.github.podshot.Podlauncher.gui.MainGUI;

import java.io.File;

public class PodLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
