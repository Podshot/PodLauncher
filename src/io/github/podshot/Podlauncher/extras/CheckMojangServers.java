package io.github.podshot.Podlauncher.extras;

import io.github.podshot.Podlauncher.extras.Utility.UtilityType;
import io.github.podshot.Podlauncher.gui.ErrorGUI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sk.tomsik68.mclauncher.util.HttpUtils;

public class CheckMojangServers {
	
	private static JSONParser parser = new JSONParser();
	
	/**
	 * Converts a status string into a ServerStatuc ENUM, for easier status handling in MainGUI
	 * @param stat String to convert to ENUM, suitable strings are 'green', 'yellow' or 'red'
	 * @return A ServerStatus ENUM value
	 */
	@Utility(UtilityType.OTHER)
	private static ServerStatus convertStatusToENUM(String stat) {
		if (stat.equals("green")) {
			return ServerStatus.ONLINE;
		}
		
		if (stat.equals("yellow")) {
			return ServerStatus.UNSTABLE;
		}
		return ServerStatus.OFFLINE;
	}
	
	/**
	 * Gets the Mojang JSON Server Status JSON from status.mojang.com/check
	 * @return A JSONArray of the server statuses
	 */
	private static JSONArray getStatusJSON() {
		JSONArray status = null;
		try {
			status = (JSONArray) parser.parse(HttpUtils.httpGet("http://status.mojang.com/check"));
		} catch (Exception e) {
			System.out.println("Could not parse Minecraft Server Status");
			new ErrorGUI(e);
		}
		return status;
		
	}

	/**
	 * Gets Minecraft.net's status
	 * @return A ServerStatus ENUM value
	 */
	public static ServerStatus getMinecraft_net() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(0)).get("minecraft.net");
		return convertStatusToENUM(statusCode);
	}
	
	/**
	 * Gets Mojang's Session servers status
	 * @return A ServerStatus ENUM value
	 */
	public static ServerStatus getSessions() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(1)).get("session.minecraft.net");
		return CheckMojangServers.convertStatusToENUM(statusCode);
	}
	
	/**
	 * Gets Mojang's Account servers status
	 * @return A ServerStatus ENUM value
	 */
	public static ServerStatus getAccounts() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(2)).get("account.mojang.com");
		return CheckMojangServers.convertStatusToENUM(statusCode);
	}
	
	/**
	 * Gets Mojang's Authentication servers status
	 * @return A ServerStatus ENUM value
	 */
	public static ServerStatus getAuth() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(3)).get("auth.mojang.com");
		return CheckMojangServers.convertStatusToENUM(statusCode);
	}
	
	/**
	 * Gets Mojang's Skin servers status
	 * @return A ServerStatus ENUM value
	 */
	public static ServerStatus getSkins() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(4)).get("skins.minecraft.net");
		return CheckMojangServers.convertStatusToENUM(statusCode);
	}
	
	/**
	 * Server Status ENUM, for easier handling in MainGUI
	 */
	public enum ServerStatus {
		ONLINE, UNSTABLE, OFFLINE;
	}
}
