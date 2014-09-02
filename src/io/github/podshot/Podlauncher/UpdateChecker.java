package io.github.podshot.Podlauncher;

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
	
	public UpdateChecker() throws Exception {
		
		String versionInfoSTR = HttpUtils.httpGet(versionInfoURL);
		
		JSONObject versionInfo = (JSONObject) new JSONParser().parse(versionInfoSTR);
		
		String updateVersion = (String) versionInfo.get("Version");
		String updateStage = (String) versionInfo.get("Development Stage");
		updaterURL = new URL((String) versionInfo.get("Updater URL"));
		
		if (!(currentVersion.equals(updateVersion))) {
			if (updateStage.equalsIgnoreCase("stable")) {
				UpdateGUI upGUI = new UpdateGUI(updateVersion, false);
				upGUI.setVisible(true);
			}
			if (updateStage.equalsIgnoreCase("CANIDATE") && LauncherConfig.shouldUseCanidateBuilds()) {
				canidateUpdate = true;
				UpdateGUI upGUI = new UpdateGUI(updateVersion, true);
				upGUI.setVisible(true);
			}
		}
	}

	public static URL getUpdateURL() {
		return updaterURL;
	}
	
	public static boolean isACanidateUpdate() {
		return canidateUpdate;
	}

}
