package mc.podshot.launcher.main;

import sk.tomsik68.mclauncher.api.login.IProfile;
import sk.tomsik68.mclauncher.api.login.ISession;
import sk.tomsik68.mclauncher.impl.common.Platform;
import sk.tomsik68.mclauncher.impl.login.yggdrasil.YDLoginService;

public class login {
	
	@SuppressWarnings("unused")
	public login(IProfile profile) throws Exception {
		YDLoginService loginService = new YDLoginService();
		loginService.load(Platform.getCurrentPlatform().getWorkingDirectory());
		ISession session = loginService.login(profile);
	}
}
