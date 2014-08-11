package io.github.podshot.Podlauncher;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sk.tomsik68.mclauncher.util.HttpUtils;

public class GetMinecraftVersions {
	
	private static ArrayList<String> allVersions = new ArrayList<String>();

	public static String[] getStrings() {
		return allVersions.toArray(new String[allVersions.size()]);
	}

	public static void setup() {
		JSONObject versionsJSON = null;
		JSONParser parser = new JSONParser();
		try {
			versionsJSON = (JSONObject) parser.parse(HttpUtils.httpGet("http://s3.amazonaws.com/Minecraft.Download/versions/versions.json"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONArray versions = (JSONArray) versionsJSON.get("versions");
		
		@SuppressWarnings("unchecked")
		Iterator<JSONObject> iter = versions.iterator();
		while (iter.hasNext()) {
			JSONObject version = iter.next();
			
			String sVersion = (String) version.get("type");
			sVersion = sVersion + " " + (String) version.get("id");
			
			allVersions.add(sVersion);
			
			
		}
		
	}

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
		System.out.println("Version Without Prefix: " + newSTR);
		return newSTR;
	}

}
