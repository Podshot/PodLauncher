package io.github.podshot.Podlauncher.files;

import io.github.podshot.Podlauncher.extras.Music;
import io.github.podshot.Podlauncher.extras.Utility;
import io.github.podshot.Podlauncher.extras.Utility.UtilityType;
import io.github.podshot.Podlauncher.gui.ErrorGUI;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("unchecked")
public class LauncherConfig {

	private static JSONParser parser = new JSONParser();
	private static boolean fileCreated;

	/**
	 * Checks if config.json is in the PodLauncher folder, if not, it creates a blank config.json
	 */
	public static void checkForFile() {
		File launcherConfig = new File("PodLauncher" + File.separator + "config.json");
		if (!(launcherConfig.exists())) {
			try {
				launcherConfig.createNewFile();
			} catch (IOException e) {
				new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "checkForFile()");
				e.printStackTrace();
			}

			JSONObject mainJSON = new JSONObject();
			JSONArray profileArray = new JSONArray();
			mainJSON.put("Last Profile", "");
			mainJSON.put("Profiles", profileArray);
			mainJSON.put("Use Canidate Builds", "false");

			try {
				writeToFile(mainJSON.toJSONString());
			} catch (IOException e) {
				new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "checkForFile()");
			}

		}
		fileCreated = true;
	}

	/**
	 * Writes the JSON formated string to the config.json
	 * @param json The JSON string to write to the config.json file
	 * @throws IOException Thrown when the config.json cannot be found
	 */
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
	@Deprecated
	public static void addProfile(String name, String username, String password, String directory, String version) {
		if (!(fileCreated)) {
			checkForFile();
		}
		JSONObject launcherJSON = null;

		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			System.out.println("Could not read config.json");
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "addProfile()");
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

	/**
	 * Adds a profile from a JSONObject
	 * @param json The JSONObject that should be added to the profile list in the config.json file
	 */
	public static void addProfileFromJSON(JSONObject json) {
		JSONObject launcherJSON = null;

		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "addProfileFromJSON()");
		}

		JSONArray profileArray = (JSONArray) launcherJSON.get("Profiles");
		if (profileArray.contains(json)) {
			profileArray.remove(json);
		}

		profileArray.add(json);

		launcherJSON.remove("Profiles");
		launcherJSON.put("Profiles", profileArray);
		try {
			writeToFile(launcherJSON.toJSONString());
		} catch (IOException e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "addProfileFromJSON()");
			e.printStackTrace();
		}
	}

	/**
	 * Gets a String List of all the Profile Names in the config.json file
	 * @return
	 */
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
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "getProfiles()");
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
	 * Gets the Profile that has the specified Profile Name
	 * @param profileName The Name of the Profile JSONObject you want to get
	 * @param remove True if you want to remove the Profile from the Profile List
	 * @return The Profile JSONObject
	 */
	@Music(songArtist = "More Kords ft. Miyoki", songName = "Fragmentize", songUrl = "Currently Unknown")
	public static JSONObject getProfile(String profileName, boolean remove) {
		if (!(fileCreated)) {
			checkForFile();
		}

		JSONObject launcherJSON = null;
		JSONObject profile2return = null;

		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "getProfile()");
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
				new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "getProfile()");
			}
		}

		return profile2return;
	}

	/**
	 * Updates the last launched profile
	 * @param profile The Profile Name of the Profile that was last launched
	 */
	public static void updateLastProfile(String profile) {
		JSONObject launcherJSON = null;
		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "updateLastProfile()");
		}

		launcherJSON.remove("Last Profile");
		launcherJSON.put("Last Profile", profile);

		try {
			writeToFile(launcherJSON.toJSONString());
		} catch (IOException e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "updateLastProfile()");
		}

	}

	/**
	 * 
	 * @param profile
	 * @param key
	 * @param value
	 */
	@Utility(UtilityType.QUICK_FIX)
	public static void addJSONEntry(String profile, String key, String value) {
		JSONObject profileJSON = getProfile(profile, true);
		profileJSON.put(key, value);
		addProfileFromJSON(profileJSON);

	}

	/**
	 * Gets if PodLauncher should notify the user of new canidate builds
	 * @return True if PodLauncher should notify on new canidate builds, false otherwise
	 */
	public static boolean shouldUseCanidateBuilds() {
		JSONObject launcherJSON = null;
		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "shouldUseCanidateBuilds()");
		}

		boolean shouldUseCanidate = Boolean.parseBoolean((String) launcherJSON.get("Use Canidate Builds"));
		return shouldUseCanidate;
	}

	/**
	 * Sets if PodLauncher should use canidate builds in the config.json file
	 * @param bool True if user should be notified of canidate builds, false otherwise
	 */
	public static void setUseCanidateBuilds(boolean bool) {
		JSONObject launcherJSON = null;
		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "setUseCanidateBuilds()");
		}

		if (launcherJSON.containsKey("Use Canidate Builds")) {
			launcherJSON.remove("Use Canidate Builds");
		}
		launcherJSON.put("Use Canidate Builds", bool);
		try {
			writeToFile(launcherJSON.toJSONString());
		} catch (IOException e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "setUseCanidateBuilds()");
			e.printStackTrace();
		}
	}

	/**
	 * Gets the Game Type of the specified profile
	 * @param string The profile name to get the Game Type of
	 * @return When Forge compatability is complete, this will either return 'VANILLA' or 'FORGE'
	 */
	@Utility(UtilityType.COMPATIBILITY)
	public static String getGameType(String string) {
		JSONObject profile = getProfile(string, false);
		String gameType = (String) profile.get("Profile Type");
		return gameType;
	}

	/**
	 * Gets the current config version that PodLauncher can read
	 * @return
	 */
	@Utility(UtilityType.COMPATIBILITY)
	public static int getCurrentLauncherConfigVersion() {
		return 2;
	}

	/**
	 * Gets the active config version present in the PodLauncher folder
	 * @return
	 */
	@Utility(UtilityType.COMPATIBILITY)
	public static int getActiveLauncherConfigVersion() {
		JSONObject launcherJSON = null;
		try {
			launcherJSON = (JSONObject) parser.parse(new FileReader("PodLauncher" + File.separator + "config.json"));
		} catch (IOException | ParseException e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "getActiveLauncherConfigVersion()");
		}
		if (launcherJSON.containsKey("Launcher Config Version")) {
			int configVersion = (int) launcherJSON.get("Launcher Config Version");
			return configVersion;
		} else {
			launcherJSON.put("Launcher Config Version", getCurrentLauncherConfigVersion());
			try {
				writeToFile(launcherJSON.toJSONString());
			} catch (IOException e) {
				new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "getActiveLauncherConfigVersion()");
			}
		}
		return getCurrentLauncherConfigVersion();
	}
}