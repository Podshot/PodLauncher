package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.gui.ErrorGUI;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sk.tomsik68.mclauncher.util.HttpUtils;

public class GetMinecraftVersions {

	private static ArrayList<String> allVersions = new ArrayList<String>();

	/**
	 * Gets all of the Names of Profiles in the PodLauncher config.json
	 * @return An array of profile name strings
	 */
	public static String[] getStrings() {
		// Converts a String array into a String List
		return allVersions.toArray(new String[allVersions.size()]);
	}
	
	public static String getLatestRelease() {
		JSONParser parser = new JSONParser();
		JSONObject versionJSON = null;
		
		try {
			versionJSON = (JSONObject) parser.parse(HttpUtils.httpGet("http://s3.amazonaws.com/Minecraft.Download/versions/versions.json"));
		} catch (Exception e) {
			new ErrorGUI(e);
		}
		JSONObject latestJSONOBJ = (JSONObject) versionJSON.get("latest");
		
		return (String) latestJSONOBJ.get("release");
	}
	
	public static String getLatestSnapshot() {
		JSONParser parser = new JSONParser();
		JSONObject versionJSON = null;
		
		try {
			versionJSON = (JSONObject) parser.parse(HttpUtils.httpGet("http://s3.amazonaws.com/Minecraft.Download/versions/versions.json"));
		} catch (Exception e) {
			new ErrorGUI(e);
		}
		JSONObject latestJSONOBJ = (JSONObject) versionJSON.get("latest");
		
		return (String) latestJSONOBJ.get("snapshot");
	}

	/**
	 * Setups the version list from Mojang
	 */
	public static void setup() {
		JSONObject versionsJSON = null;
		JSONParser parser = new JSONParser();
		try {
			versionsJSON = (JSONObject) parser.parse(HttpUtils.httpGet("http://s3.amazonaws.com/Minecraft.Download/versions/versions.json"));
		} catch (Exception e) {
			new ErrorGUI(e);
		}

		JSONArray versions = (JSONArray) versionsJSON.get("versions");
		allVersions.add("<Latest Snapshot>");
		allVersions.add("<Latest Release>");

		@SuppressWarnings("unchecked")
		Iterator<JSONObject> iter = versions.iterator();
		while (iter.hasNext()) {
			JSONObject version = iter.next();

			String sVersion = (String) version.get("type");
			sVersion = sVersion + " " + (String) version.get("id");

			allVersions.add(sVersion);


		}

	}

	/**
	 * Remove the version prefix from the selected version string
	 * @param string The string to strip the version prefix from. 
	 * Example: from 'release 1.7.10' to '1.7.10'
	 * @return The version string
	 */
	public static String stripVersionPrefix(String string) {
		String newSTR = null;
		if (string.contains("snapshot")) {
			newSTR = string.replace("snapshot ", "");
		}

		if (string.contains("release")) {
			newSTR = string.replace("release ", "");
		}

		if (string.contains("old_beta")) {
			newSTR = string.replace("old_beta ", "");
		}

		if (string.contains("old_alpha")) {
			newSTR = string.replace("old_alpha ", "");
		}
		if (PodLauncher.inDebugMode()) {
			System.out.println("Version Without Prefix: " + newSTR);
		}
		return newSTR;
	}

}
