package io.github.podshot.Podlauncher.files;

import io.github.podshot.Podlauncher.PodLauncher;
import io.github.podshot.Podlauncher.gui.ErrorGUI;

import java.io.File;

import javax.swing.JFileChooser;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ProfileZipper {

	public ProfileZipper(String gameDir){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new ZipFileFilter());
		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == 0) {
			try {
				File file = fileChooser.getSelectedFile();
				ZipFile zipFile;
				if (file.getName().contains(".zip")) {
					zipFile = new ZipFile(file);
				} else {
					zipFile = new ZipFile(file + ".zip");
				}
				ZipParameters params = new ZipParameters();
				params.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
				params.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
				if (PodLauncher.inDebugMode()) {
					System.out.println("Zipping....");
				}
				File savesDir = new File(gameDir + File.separator + "saves");
				File resourcePacksDir = new File(gameDir + File.separator + "resourcepacks");
				File serverDat = new File(gameDir + File.separator + "servers.dat");
				File screenshotDir = new File(gameDir + File.separator + "screenshots");
				if (savesDir.exists()) {
					zipFile.addFolder(savesDir, params);
				}
				if (resourcePacksDir.exists()) {
					zipFile.addFolder(resourcePacksDir, params);
				}
				if (serverDat.exists()) {
					zipFile.addFile(serverDat, params);
				}
				if (screenshotDir.exists()) {
					zipFile.addFolder(screenshotDir, params);
				}
			} catch (ZipException e) {
				new ErrorGUI(e);
				if (PodLauncher.isDevMode()) {
					e.printStackTrace();
				}
			}
			if (PodLauncher.inDebugMode()) {
				System.out.println("Finished Zipping");
			}
		}
	}
}
