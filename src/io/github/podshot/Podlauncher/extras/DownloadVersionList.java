package io.github.podshot.Podlauncher.extras;

import io.github.podshot.Podlauncher.gui.ErrorGUI;
import sk.tomsik68.mclauncher.api.common.IObservable;
import sk.tomsik68.mclauncher.api.common.IObserver;
import sk.tomsik68.mclauncher.api.versions.IVersion;
import sk.tomsik68.mclauncher.impl.versions.mcdownload.MCDownloadVersionList;

public class DownloadVersionList extends Thread implements IObservable<IVersion> {
	private final MCDownloadVersionList list = new MCDownloadVersionList();
	
	public DownloadVersionList() {
	}
	
	@Override
	public void run() {
		try {
			list.startDownload();
		} catch (Exception e) {
			new ErrorGUI(e.getMessage(), e.getStackTrace(), e.getCause(), "run()");
			e.printStackTrace();
		}
	}

	@Override
	public void addObserver(IObserver<IVersion> arg0) {
		list.addObserver(arg0);	
	}

	@Override
	public void deleteObserver(IObserver<IVersion> arg0) {
		list.deleteObserver(arg0);
		
	}

	@Override
	public void notifyObservers(IVersion arg0) {
		throw new IllegalStateException("Impossibru!!!");
	}

}
