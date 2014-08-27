package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.gui.UpdateGUI;

import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sk.tomsik68.mclauncher.util.HttpUtils;

public class UpdateChecker {
	
	private String versionInfoURL;
	private String currentVersion = PodLauncher.getVersion();
	
	public UpdateChecker() throws Exception {
		
		String versionInfoSTR = HttpUtils.httpGet(versionInfoURL);
		
		JSONObject versionInfo = (JSONObject) new JSONParser().parse(versionInfoSTR);
		
		String updateVersion = (String) versionInfo.get("Version");
		String updateStage = (String) versionInfo.get("Development Stage");
		URL updaterURL = new URL((String) versionInfo.get("Updater URL"));
		
		if (!(currentVersion.equals(updateVersion))) {
			if (updateStage.equals("STABLE") && PodLauncher.isDevMode()) {
				UpdateGUI upGUI = new UpdateGUI(updateVersion);
			}
		}
	}

}
