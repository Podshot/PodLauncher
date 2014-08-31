package io.github.podshot.Podlauncher.extras;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sk.tomsik68.mclauncher.util.HttpUtils;

public class CheckMojangServers {
	
	private static JSONParser parser = new JSONParser();
	
	private static ServerStatus convertStatusToENUM(String stat) {
		if (stat.equals("green")) {
			return ServerStatus.ONLINE;
		}
		
		if (stat.equals("yellow")) {
			return ServerStatus.UNSTABLE;
		}
		return ServerStatus.OFFLINE;
	}
	
	private static JSONArray getStatusJSON() {
		JSONArray status = null;
		try {
			status = (JSONArray) parser.parse(HttpUtils.httpGet("http://status.mojang.com/check"));
		} catch (Exception e) {
			System.out.println("Could not parse Minecraft Server Status");
			e.printStackTrace();
		}
		return status;
		
	}

	public static ServerStatus getMinecraft_net() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(0)).get("minecraft.net");
		return convertStatusToENUM(statusCode);
	}
	
	public static ServerStatus getSessions() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(1)).get("session.minecraft.net");
		return CheckMojangServers.convertStatusToENUM(statusCode);
	}
	
	public static ServerStatus getAccounts() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(2)).get("account.mojang.com");
		return CheckMojangServers.convertStatusToENUM(statusCode);
	}
	
	public static ServerStatus getAuth() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(3)).get("auth.mojang.com");
		return CheckMojangServers.convertStatusToENUM(statusCode);
	}
	
	public static ServerStatus getSkins() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(4)).get("skins.minecraft.net");
		return CheckMojangServers.convertStatusToENUM(statusCode);
	}
	
	public enum ServerStatus {
		ONLINE, UNSTABLE, OFFLINE;
	}
}
