package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.files.LauncherConfig;
import io.github.podshot.Podlauncher.gui.MainGUI;

import java.io.File;

public class MainLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File launcherFolder = new File("PodLauncher");
		if (!(launcherFolder.exists())) {
			launcherFolder.mkdir();
		}
		new LauncherConfig();
		
		LauncherConfig.addProfile("Profile2", "Username2", "Password2", "Directory2", "Version2");
		
		MainGUI mainGUI = new MainGUI();
		mainGUI.setVisible(true);

	}

}
