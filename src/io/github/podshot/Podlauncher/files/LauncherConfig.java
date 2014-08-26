package io.github.podshot.Podlauncher.files;

import io.github.podshot.Podlauncher.extras.Music;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LauncherConfig {

	private static JSONParser parser;

	@SuppressWarnings("unchecked")
	public LauncherConfig() {
		parser = new JSONParser();
		File launcherConfig = new File("PodLauncher" + File.separator + "config.json");
		if (!(launcherConfig.exists())) {
			try {
				launcherConfig.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			JSONObject mainJSON = new JSONObject();
			
			JSONArray profileList = new JSONArray();
			
			JSONObject defaultProfile = new JSONObject();
			defaultProfile.put("Username", "{Default Username}");
			defaultProfile.put("Password", "{Default Password}");
			defaultProfile.put("Game Directory", "{Default Directory}");
			defaultProfile.put("Minecraft Version", "{Default Version}");
			defaultProfile.put("Profile Name", "{Default Profile Name}");
			
			profileList.add(defaultProfile);
			mainJSON.put("Profiles", profileList);
			mainJSON.put("Last Profile", "{Default Profile Name}");
			
			try {
				FileWriter json = new FileWriter(launcherConfig);
				json.write(mainJSON.toJSONString());
				json.flush();
				json.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 
	 * @param name
	 * @param username
	 * @param directory
	 * @param version
	 */
	@SuppressWarnings("unchecked")
	public static void addProfile(String name, String username, String password, String directory, String version)  {
		JSONObject launcherJSON = null;
		
		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			System.out.println("Could not read config.json");
			e.printStackTrace();
		}
		
		JSONArray profileList = (JSONArray) launcherJSON.get("Profiles");
		
		JSONObject newProfile = new JSONObject();
		newProfile.put("Username", username);
		newProfile.put("Password", password);
		newProfile.put("Game Directory", directory);
		newProfile.put("Minecraft Version", version);
		newProfile.put("Profile Name", name);
		
		profileList.add(newProfile);
		launcherJSON.put("Profiles", profileList);
		
		try {
			FileWriter json = new FileWriter("PodLauncher" + File.separator + "config.json");
			json.write(launcherJSON.toJSONString());
			json.flush();
			json.close();
		} catch (IOException ioe) {
			System.out.println("Could not save config.json");
			ioe.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void editLauncherJSON(String keyValue, String value) {
		
		JSONObject launcherJSON = null;
		
		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		if (launcherJSON.containsKey(keyValue)) {
			launcherJSON.remove(keyValue);
		}
		
		launcherJSON.put(keyValue, value);
		
		try {
			FileWriter json = new FileWriter("PodLauncher" + File.separator + "config.json");
			json.write(launcherJSON.toJSONString());
			json.flush();
			json.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	@Music(songArtist = "Monstercat", songName = "Monstercat 018 - Frontier (Horizon Album Mix)", songUrl = "http://youtu.be/of7vnz3YS-k")
	public static String[] getProfiles() {
		ArrayList<String> profileList = new ArrayList<String>();
		
		JSONObject json = null;
		JSONArray profiles = null;
		
		try {
			json = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		String lastProfile = (String) json.get("Last Profile");
		profileList.add(lastProfile);
		
		profiles = (JSONArray) json.get("Profiles");
		
		for (Object profileOBJ : profiles) {
			JSONObject profile = (JSONObject) profileOBJ;
			String profileName = (String) profile.get("Profile Name");
			if (!(profileName.equals(lastProfile))) {
				profileList.add(profileName);
			}
		}
		
		return profileList.toArray(new String[profileList.size()]);
	}
	
	/**
	 * 
	 * @param profileName
	 * @param key
	 * @param value
	 */
	@Music(songArtist = "More Kords ft. Miyoki", songName = "Fragmentize", songUrl = "Currently Unknown")
	@SuppressWarnings("unchecked")
	public static JSONObject getProfile(String profileName) {
		JSONObject launcherJSON = null;
		JSONObject profile2return = null;
		
		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray profiles = (JSONArray) launcherJSON.get("Profiles");
		
		for (Object profileOBJ : profiles) {
			JSONObject profile = (JSONObject) profileOBJ;
			String name = (String) profile.get("Profile Name");
			if (name.equals(profileName)) {
				profile2return = profile;
			}
		}
		profiles.remove(profile2return);
		
		launcherJSON.put("Profiles", profiles);
		
		try {
			FileWriter json = new FileWriter("PodLauncher" + File.separator + "config.json");
			json.write(launcherJSON.toJSONString());
			json.flush();
			json.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return profile2return;
	}
}
