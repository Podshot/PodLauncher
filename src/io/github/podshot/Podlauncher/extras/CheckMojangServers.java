package io.github.podshot.Podlauncher.extras;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import sk.tomsik68.mclauncher.util.HttpUtils;

@SuppressWarnings("unused")
public class CheckMojangServers {
	
	private static JSONParser parser = new JSONParser();

	private String minecraft_net;
	private String sessions;
	private String accounts;
	private String auth;
	private String skins;
	private String authserver;
	private String sessionserver;
	private String api;
	private String textures;

	@Deprecated
	public CheckMojangServers() throws Exception {
		String status = HttpUtils.httpGet("http://status.mojang.com/check");
		JSONArray array = (JSONArray) JSONValue.parse(status);
		System.out.println(array.toJSONString());
		this.minecraft_net = (String) ((JSONObject) array.get(0)).get("minecraft.net");
		this.sessions = (String) ((JSONObject) array.get(1)).get("session.minecraft.net");
		this.accounts = (String) ((JSONObject) array.get(2)).get("account.mojang.com");
		this.auth = (String) ((JSONObject) array.get(3)).get("auth.mojang.com");
		this.skins = (String) ((JSONObject) array.get(4)).get("skins.minecraft.net");
		this.authserver = (String) ((JSONObject) array.get(5)).get("authserver.mojang.com");
		this.sessionserver = (String) ((JSONObject) array.get(6)).get("sessionserver.mojang.com");
		this.api = (String) ((JSONObject) array.get(7)).get("api.mojang.com");
		this.textures = (String) ((JSONObject) array.get(8)).get("textures.minecraft.net");
		
	}
	
	private static ServerStatus convertStatusToGreen(String stat) {
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
		return convertStatusToGreen(statusCode);
	}
	
	public static ServerStatus getSessions() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(1)).get("session.minecraft.net");
		return CheckMojangServers.convertStatusToGreen(statusCode);
	}
	
	public static ServerStatus getAccounts() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(2)).get("account.mojang.com");
		return CheckMojangServers.convertStatusToGreen(statusCode);
	}
	
	public static ServerStatus getAuth() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(3)).get("auth.mojang.com");
		return CheckMojangServers.convertStatusToGreen(statusCode);
	}
	
	public static ServerStatus getSkins() {
		JSONArray status = getStatusJSON();
		String statusCode = (String) ((JSONObject) status.get(4)).get("skins.minecraft.net");
		return CheckMojangServers.convertStatusToGreen(statusCode);
	}
	
	public enum ServerStatus {
		ONLINE, UNSTABLE, OFFLINE;
	}
}
