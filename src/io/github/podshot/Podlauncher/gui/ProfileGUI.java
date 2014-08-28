package io.github.podshot.Podlauncher.gui;

import io.github.podshot.Podlauncher.GetMinecraftVersions;
import io.github.podshot.Podlauncher.files.LauncherConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ProfileGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldProfileName;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private JButton btnCreateProfile;
	private JComboBox<String> comboBox;
	private JTextField textFieldGameDir;
	
	public ProfileGUI() {
		this.setTitle("Create a new Profile");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(500, 300);
		
		JLabel lblProfileName = new JLabel("Profile Name");
		lblProfileName.setBounds(10, 10, 79, 14);
		getContentPane().add(lblProfileName);
		
		textFieldProfileName = new JTextField();
		lblProfileName.setLabelFor(textFieldProfileName);
		textFieldProfileName.setBounds(99, 7, 136, 20);
		getContentPane().add(textFieldProfileName);
		textFieldProfileName.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setLabelFor(textFieldUsername);
		lblUsername.setBounds(10, 50, 79, 14);
		getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(99, 47, 136, 20);
		getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 90, 79, 14);
		getContentPane().add(lblPassword);
		
		passwordFieldPassword = new JPasswordField();
		lblPassword.setLabelFor(passwordFieldPassword);
		passwordFieldPassword.setBounds(99, 87, 136, 20);
		getContentPane().add(passwordFieldPassword);
		
		JLabel lblMinecraftVersion = new JLabel("Minecraft Version");
		lblMinecraftVersion.setBounds(10, 130, 120, 14);
		getContentPane().add(lblMinecraftVersion);
		
		GetMinecraftVersions.setup();
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(GetMinecraftVersions.getStrings()));
		comboBox.setBounds(140, 127, 136, 20);
		getContentPane().add(comboBox);
		
		btnCreateProfile = new JButton("Create Profile");
		btnCreateProfile.setBounds(10, 227, 120, 23);
		btnCreateProfile.addActionListener(this);
		getContentPane().add(btnCreateProfile);
		
		JLabel lblGameDirectory = new JLabel("Game Directory");
		lblGameDirectory.setBounds(10, 170, 120, 14);
		getContentPane().add(lblGameDirectory);
		
		textFieldGameDir = new JTextField();
		lblGameDirectory.setLabelFor(textFieldGameDir);
		textFieldGameDir.setBounds(115, 167, 200, 20);
		getContentPane().add(textFieldGameDir);
		textFieldGameDir.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.btnCreateProfile) {
			LauncherConfig.addProfile(this.textFieldProfileName.getText(), this.textFieldUsername.getText(), new String(this.passwordFieldPassword.getPassword()), this.textFieldGameDir.getText(), GetMinecraftVersions.stripVersionPrefix(this.comboBox.getSelectedItem().toString()));
			this.dispose();
		}

	}
}
