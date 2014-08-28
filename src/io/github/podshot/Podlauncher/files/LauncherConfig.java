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

	private static JSONParser parser = new JSONParser();
	private static boolean fileCreated;

	@SuppressWarnings("unchecked")
	public static void checkForFile() {
		File launcherConfig = new File("PodLauncher" + File.separator + "config.json");
		if (!(launcherConfig.exists())) {
			try {
				launcherConfig.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			JSONObject mainJSON = new JSONObject();
			JSONArray profileArray = new JSONArray();
			mainJSON.put("Last Profile", "");
			mainJSON.put("Profiles", profileArray);

			try {
				writeToFile(mainJSON.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		fileCreated = true;
	}

	private static void writeToFile(String json) throws IOException {
		File launcherConfig = new File("PodLauncher" + File.separator + "config.json");

		FileWriter jsonFile = new FileWriter(launcherConfig);
		jsonFile.write(json);
		jsonFile.flush();
		jsonFile.close();
	}

	/**
	 * 
	 * @param name
	 * @param username
	 * @param directory
	 * @param version
	 */
	@SuppressWarnings("unchecked")
	public static void addProfile(String name, String username, String password, String directory, String version) {
		if (!(fileCreated)) {
			checkForFile();
		}
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
			writeToFile(launcherJSON.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Music(songArtist = "Monstercat", songName = "Monstercat 018 - Frontier (Horizon Album Mix)", songUrl = "http://youtu.be/of7vnz3YS-k")
	public static String[] getProfiles() {

		if (!(fileCreated)) {
			checkForFile();
		}

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
	 * @param remove
	 * @return
	 */
	@Music(songArtist = "More Kords ft. Miyoki", songName = "Fragmentize", songUrl = "Currently Unknown")
	@SuppressWarnings("unchecked")
	public static JSONObject getProfile(String profileName, boolean remove) {
		if (!(fileCreated)) {
			checkForFile();
		}

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
		if (remove) {
			profiles.remove(profile2return);

			launcherJSON.put("Profiles", profiles);
			try {
				writeToFile(launcherJSON.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return profile2return;
	}
	
	/**
	 * 
	 * @param profile
	 */
	@SuppressWarnings("unchecked")
	public static void updateLastProfile(String profile) {
		JSONObject launcherJSON = null;
		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		launcherJSON.remove("Last Profile");
		launcherJSON.put("Last Profile", profile);
		
		try {
			writeToFile(launcherJSON.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
