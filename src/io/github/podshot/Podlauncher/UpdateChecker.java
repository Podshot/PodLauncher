package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.extras.Utility;
import io.github.podshot.Podlauncher.extras.Utility.UtilityType;
import io.github.podshot.Podlauncher.files.LauncherConfig;
import io.github.podshot.Podlauncher.gui.UpdateGUI;

import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sk.tomsik68.mclauncher.util.HttpUtils;

public class UpdateChecker {

	private String versionInfoURL = "http://podshot.github.io/PodLauncher/update/update.json";
	private String currentVersion = PodLauncher.getVersion();
	private static boolean canidateUpdate;
	private static URL updaterURL;

	@Utility(UtilityType.DEVELOPMENT)
	public UpdateChecker() throws Exception {
		boolean usingCanidateUpdate = false;

		String versionInfoSTR = HttpUtils.httpGet(versionInfoURL);

		JSONObject versionInfo = (JSONObject) new JSONParser().parse(versionInfoSTR);

		String updateVersion = (String) versionInfo.get("Version");
		updaterURL = new URL((String) versionInfo.get("Updater URL"));
		if (LauncherConfig.shouldUseCanidateBuilds()) {
			JSONObject canidateInfo = (JSONObject) versionInfo.get("Canidate");
			updateVersion = (String) canidateInfo.get("Version");
			updaterURL = new URL((String) canidateInfo.get("Updater URL"));
			usingCanidateUpdate = true;
			canidateUpdate = true;
		}

		if (!(PodLauncher.isDevMode())) {
			if (!(currentVersion.equals(updateVersion))) {
				UpdateGUI upGUI = new UpdateGUI(updateVersion, usingCanidateUpdate);
				upGUI.setVisible(true);
			}
		}
	}

	/**
	 * Gets the URL of the PodLauncher Updater
	 * @return The URL of the Updater
	 */
	public static URL getUpdateURL() {
		return updaterURL;
	}

	/**
	 * Checks if the found update is a Canidate Build
	 * @return True if the update is a canidate build
	 */
	public static boolean isACanidateUpdate() {
		return canidateUpdate;
	}

}
