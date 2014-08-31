package io.github.podshot.Podlauncher.gui;

import io.github.podshot.Podlauncher.PodLauncher;
import io.github.podshot.Podlauncher.extras.DownloadUpdater;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UpdateGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnYes;
	private JButton btnNo;
	private JLabel lblAnUpdatedVersion;

	public UpdateGUI(String newVersion, boolean canidate) {
		this.setType(Type.UTILITY);
		this.setTitle("Update Found");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(450, 230);
		this.setAlwaysOnTop(true);

		if (!(canidate)) {
			lblAnUpdatedVersion = new JLabel("An updated version of PodLauncher has been found!");
		} else {
			lblAnUpdatedVersion = new JLabel("An canidate version of PodLauncher has been found!");
		}
		lblAnUpdatedVersion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnUpdatedVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnUpdatedVersion.setBounds(10, 11, 414, 36);
		getContentPane().add(lblAnUpdatedVersion);

		JLabel lblNewVersion = new JLabel("New Version: " + newVersion);
		lblNewVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewVersion.setBounds(10, 58, 414, 14);
		getContentPane().add(lblNewVersion);

		JLabel lblCurrentVersion = new JLabel("Current Version: " + PodLauncher.getVersion());
		lblCurrentVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentVersion.setBounds(10, 83, 414, 14);
		getContentPane().add(lblCurrentVersion);

		JLabel lblNewLabel = new JLabel("Would you like to update?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 108, 414, 21);
		getContentPane().add(lblNewLabel);

		btnYes = new JButton("Yes");
		btnYes.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnYes.setBounds(90, 140, 89, 23);
		btnYes.addActionListener(this);
		getContentPane().add(btnYes);

		btnNo = new JButton("No");
		btnNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNo.setBounds(238, 140, 89, 23);
		btnNo.addActionListener(this);
		getContentPane().add(btnNo);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.btnYes) {
			DownloadUpdater.start();
		}

		if (event.getSource() == this.btnNo) {
			this.dispose();
		}
	}
}
