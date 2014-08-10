package io.github.podshot.Podlauncher.extras;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import sk.tomsik68.mclauncher.util.HttpUtils;

@SuppressWarnings("unused")
public class CheckMojangServers {
	
	private String minecraft_net;
	private String sessions;
	private String accounts;
	private String auth;
	private String skins;
	private String authserver;
	private String sessionserver;
	private String api;
	private String textures;

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
	
	private boolean convertStatusToGreen(String stat) {
		if (stat.equals("green")) {
			return true;
		}
		return false;
	}

	public boolean getMinecraft_net() {
		return this.convertStatusToGreen(this.minecraft_net);
	}
	
	public boolean getSessions() {
		return this.convertStatusToGreen(this.sessions);
	}
	
	public boolean getAccounts() {
		return this.convertStatusToGreen(this.accounts);
	}
	
	public boolean getAuth() {
		return this.convertStatusToGreen(this.auth);
	}
	
	public boolean getSkins() {
		return this.convertStatusToGreen(this.skins);
	}
}
