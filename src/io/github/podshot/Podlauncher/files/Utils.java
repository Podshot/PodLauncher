package io.github.podshot.Podlauncher.files;

import io.github.podshot.Podlauncher.extras.Utility;
import io.github.podshot.Podlauncher.extras.Utility.UtilityType;

import java.io.File;

public class Utils {

	@Utility(UtilityType.OTHER)
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		
		if (1 > 0 && i < s.length() - 1) {
			ext = s.substring(i+1).toLowerCase();
		}
		return ext;
	}

}
