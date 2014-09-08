package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.extras.DownloadUpdater;
import io.github.podshot.Podlauncher.files.LauncherConfig;
import io.github.podshot.Podlauncher.gui.ErrorGUI;
import io.github.podshot.Podlauncher.gui.MainGUI;

import java.io.File;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PodLauncher {

	private static Logger logger;

	/**
	 * Method for setting up PodLauncher
	 * @param args Arguments for PodLauncher, '-updated' is currently the only one supported
	 * @throws Exception
	 */
	public static void main(String[] args) {
		logger = Logger.getLogger("PodLauncher");
		// Sets the UI Look and Feel from Java's default layout to the OS specific one
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "main()");
		}
		// Loops through the specified arguments
		for (String argument : args) {
			// If an argument equals '-updated' cleanup the files left over from the update process
			if (argument.equalsIgnoreCase("-updated")) {
				DownloadUpdater.cleanup();
			}
		}
		// Checks for an update
		logger.info("Checking for Updates");
		try {
			new UpdateChecker();
		} catch (Exception e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "main()");
		}
		// Checks to see if the folder 'PodLauncher' exists in the current working directory
		// If not create the folder
		File launcherFolder = new File("PodLauncher");
		if (!(launcherFolder.exists())) {
			launcherFolder.mkdir();
		}
		logger.info("Checking for PodLauncher's config file");
		// Checks if the config.json file is present
		LauncherConfig.checkForFile();

		logger.info("Setting up Main GUI");
		// Sets up the MainGUI and makes it visible
		MainGUI mainGUI = new MainGUI();
		mainGUI.setVisible(true);

	}

	/**
	 * Gets the PodLauncher Version number
	 * @return The Version number
	 */
	public static String getVersion() {
		return "0.0.8.1";
	}

	/**
	 * Gets the PodLauncher Development Stage
	 * @return The stage PodLauncher is in
	 */
	public static String getDevelopmentStage() {
		return "alpha";
	}

	/**
	 * Gets if PodLauncher should print debug messages (Best if true when ran from source)
	 * @return True if PodLauncher is in Debug mode, false otherwise
	 */
	public static boolean inDebugMode() {
		return false;
	}

	/**
	 * Gets if PodLauncher should cancel update messages
	 * @return True if PodLauncher is in Development mode, false otherwise
	 */
	public static boolean isDevMode() {
		return false;
	}
	
	/**
	 * Gets the PodLauncher Logger
	 * @return PodLauncher's Logger
	 */
	public static Logger getLogger() {
		return logger;
	}
}
