package io.github.podshot.Podlauncher.files;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ZipFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		
		String ext = Utils.getExtension(f);
		if (ext != null) {
			if (ext.equals("zip")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Zip Files";
	}

}
