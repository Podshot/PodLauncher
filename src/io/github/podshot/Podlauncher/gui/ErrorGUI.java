package io.github.podshot.Podlauncher.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ErrorGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/**
	 * A GUI used to notify the user that an Exception occurred
	 * @param e The Exception that was thrown
	 */
	public ErrorGUI(Exception e) {
		// Gets the current date and time, so I can format the log file name
		Calendar cal = Calendar.getInstance();
		java.util.Date now = cal.getTime();
		Timestamp current = new Timestamp(now.getTime());
		File errorFolder = new File("PodLauncher" + File.separator + "errors");
		if (!(errorFolder.exists())) {
			errorFolder.mkdir();
		}
		File log = new File("PodLauncher" + File.separator + "errors" + File.separator + "error_" + current.toString().replace(" ", "_").replace(":", "-") + ".log");
		try {
			log.createNewFile();
		} catch (IOException e2) {
			System.out.println("Could not create file");
		}
		try {
			PrintWriter pw = new PrintWriter(log);
			e.printStackTrace(pw);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Could not find file");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		this.setTitle("An Error Occured");
		getContentPane().setLayout(null);
		
		JLabel lblAnErrorHas = new JLabel("An Error has occured while PodLauncher was running.");
		lblAnErrorHas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnErrorHas.setBounds(10, 11, 375, 14);
		getContentPane().add(lblAnErrorHas);
		
		JLabel lblPleaseSeeThe = new JLabel("Please see " + log.getName() + " in the PodLauncher error");
		lblPleaseSeeThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSeeThe.setBounds(10, 36, 375, 14);
		getContentPane().add(lblPleaseSeeThe);
		
		JLabel lblMoreDetails = new JLabel("folder for more details");
		lblMoreDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoreDetails.setBounds(10, 61, 375, 14);
		getContentPane().add(lblMoreDetails);
		this.setSize(411, 141);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}
