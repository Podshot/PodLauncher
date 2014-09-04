package io.github.podshot.Podlauncher.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

public class AboutGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnCloseThisWindow;
	
	public AboutGUI() {
		this.setTitle("About PodLauncher");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		btnCloseThisWindow = new JButton("Close this Window");
		btnCloseThisWindow.setBounds(10, 11, 130, 23);
		getContentPane().add(btnCloseThisWindow);
		
		JTextPane txtpnPodlauncherIsA = new JTextPane();
		txtpnPodlauncherIsA.setContentType("text/html");
		txtpnPodlauncherIsA.setText("<div align=\"center\">\r\n<h4>About PodLauncher</h4>\r\nPodLauncher is a Open Source launcher for Minecraft. The source code for this launcher is available on its <a href=\"http://github.com/Podshot/PodLauncher\">Github Repository</a>. Anyone is welcome to add ideas, code, or support. PodLauncher's goal is to create an easy to use, yet very customizable launcher,\r\n</div>");
		txtpnPodlauncherIsA.setBackground(new Color(240, 240, 240));
		txtpnPodlauncherIsA.setEditable(false);
		txtpnPodlauncherIsA.setBounds(10, 45, 414, 205);
		getContentPane().add(txtpnPodlauncherIsA);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == this.btnCloseThisWindow) {
			this.dispose();
		}
	}
}
