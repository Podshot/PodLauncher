package io.github.podshot.Podlauncher.gui;

import io.github.podshot.Podlauncher.GetMinecraftVersions;
import io.github.podshot.Podlauncher.files.LauncherConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

public class EditProfileGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUsername;
	private JTextField textFieldProfileName;
	private JPasswordField passwordFieldPassword;
	private JComboBox<String> comboBox;
	private JButton btnCreateProfile;
	private JTextField textFieldGameDir;

	public EditProfileGUI(String profile_to_edit) {
		//this.addWindowListener(new ExitListener());
		WindowListener exitListener = new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent evt) {
				saveProfile();
			}
		};
		JSONObject profile = LauncherConfig.getProfile(profile_to_edit, true);
		this.setTitle("Edit Profile");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(500, 300);
		this.addWindowListener(exitListener);

		JLabel lblProfileName = new JLabel("Profile Name");
		lblProfileName.setBounds(10, 10, 79, 14);
		getContentPane().add(lblProfileName);

		textFieldProfileName = new JTextField();
		textFieldProfileName.setText((String) profile.get("Profile Name"));
		lblProfileName.setLabelFor(textFieldProfileName);
		textFieldProfileName.setBounds(99, 7, 136, 20);
		getContentPane().add(textFieldProfileName);
		textFieldProfileName.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setLabelFor(textFieldUsername);
		lblUsername.setBounds(10, 50, 79, 14);
		getContentPane().add(lblUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setText((String) profile.get("Username"));
		textFieldUsername.setBounds(99, 47, 136, 20);
		getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 90, 79, 14);
		getContentPane().add(lblPassword);

		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setText((String) profile.get("Password"));
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

		btnCreateProfile = new JButton("Save Profile");
		btnCreateProfile.setBounds(10, 227, 120, 23);
		btnCreateProfile.addActionListener(this);
		getContentPane().add(btnCreateProfile);

		JLabel lblGameDirectory = new JLabel("Game Directory");
		lblGameDirectory.setBounds(10, 170, 120, 14);
		getContentPane().add(lblGameDirectory);

		textFieldGameDir = new JTextField();
		textFieldGameDir.setText((String) profile.get("Game Directory"));
		lblGameDirectory.setLabelFor(textFieldGameDir);
		textFieldGameDir.setBounds(115, 167, 200, 20);
		getContentPane().add(textFieldGameDir);
		textFieldGameDir.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnCreateProfile) {
			this.saveProfile();
			this.dispose();
		}
	}
	
	private void saveProfile() {
		String profileName = this.textFieldProfileName.getText();
		String profileUsername = this.textFieldUsername.getText();
		String profilePassword = new String(this.passwordFieldPassword.getPassword());
		String profileVersion = GetMinecraftVersions.stripVersionPrefix(this.comboBox.getSelectedItem().toString());
		String profileDir = this.textFieldGameDir.getText();
		LauncherConfig.addProfile(profileName, profileUsername, profilePassword, profileDir, profileVersion);
	}

}
