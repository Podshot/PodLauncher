package io.github.podshot.Podlauncher.gui;

import io.github.podshot.Podlauncher.LaunchMinecraft;
import io.github.podshot.Podlauncher.PodLauncher;
import io.github.podshot.Podlauncher.extras.CheckMojangServers;
import io.github.podshot.Podlauncher.files.LauncherConfig;
import io.github.podshot.Podlauncher.files.ProfileZipper;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;

import sk.tomsik68.mclauncher.api.ui.IProgressMonitor;

public class MainGUI extends JFrame implements ActionListener, IProgressMonitor, ItemListener {

	private static final long serialVersionUID = 1L;
	private static MainGUI instance;
	private JComboBox<String> profileComboBox;
	private JButton btnCreateANew;
	private JLabel lblminecraft_net;
	private JLabel lbllogin;
	private JLabel lblaccount;
	private JLabel lblauth;
	private JLabel lblskins;
	private ImageIcon ONLINE = new ImageIcon(this.getClass().getResource("/images/online-32x32.png"));
	private ImageIcon UNSTABLE = new ImageIcon(this.getClass().getResource("/images/unstable-32x32.png"));
	private ImageIcon OFFLINE = new ImageIcon(this.getClass().getResource("/images/offline-32x32.png"));
	private JButton btnEditProfile;
	private JButton btnLaunchProfile;
	private JProgressBar progressBar;
	private JPanel panel;
	private int timesLaunched = 0;
	private JCheckBox chckbxUseCanidateBuilds;
	private JButton btnRemoveProfile;
	private JButton btnAboutPodlauncher;
	private JButton btnZipProfile;

	/**
	 * MainGUI constructor
	 */
	public MainGUI() {
		// Binds the instance to the static field 'instance'
		instance = this;
		this.setTitle("PodLauncher");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(614, 420);

		panel = new JPanel();
		panel.setBounds(0, 0, 614, 420);
		getContentPane().add(panel);
		panel.setLayout(null);

		// Sets up the profile list
		profileComboBox = new JComboBox<String>();
		profileComboBox.setModel(new DefaultComboBoxModel<String>(LauncherConfig.getProfiles()));
		profileComboBox.setBounds(10, 30, 230, 20);
		panel.add(profileComboBox);

		// Sets up the Create profile button
		btnCreateANew = new JButton("Create a new Profile");
		btnCreateANew.setBounds(260, 29, 160, 23);
		btnCreateANew.addActionListener(this);
		panel.add(btnCreateANew);

		btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.setBounds(430, 29, 130, 23);
		btnEditProfile.addActionListener(this);
		panel.add(btnEditProfile);

		btnLaunchProfile = new JButton("Launch Profile");
		btnLaunchProfile.setBounds(10, 70, 130, 23);
		btnLaunchProfile.addActionListener(this);
		panel.add(btnLaunchProfile);

		JLabel lblPodlauncherVersion = new JLabel("PodLauncher Version: " + PodLauncher.getDevelopmentStage() + "-" + PodLauncher.getVersion());
		lblPodlauncherVersion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPodlauncherVersion.setBounds(10, 350, 230, 14);
		panel.add(lblPodlauncherVersion);

		// Launching progressbar, not visible until a profile is launched
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(73, 130, 380, 20);
		progressBar.setVisible(false);
		panel.add(progressBar);

		
		chckbxUseCanidateBuilds = new JCheckBox("Use Canidate Builds? (May contain bugs!)");
		chckbxUseCanidateBuilds.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxUseCanidateBuilds.addItemListener(this);
		chckbxUseCanidateBuilds.setSelected(LauncherConfig.shouldUseCanidateBuilds());
		chckbxUseCanidateBuilds.setBounds(260, 346, 230, 23);
		panel.add(chckbxUseCanidateBuilds);
		

		btnRemoveProfile = new JButton("Remove Profile");
		btnRemoveProfile.setBounds(260, 70, 130, 23);
		btnRemoveProfile.addActionListener(this);
		panel.add(btnRemoveProfile);
		
		btnAboutPodlauncher = new JButton("About PodLauncher");
		btnAboutPodlauncher.setBounds(400, 70, 160, 23);
		btnAboutPodlauncher.addActionListener(this);
		panel.add(btnAboutPodlauncher);
		
		btnZipProfile = new JButton("Zip Profile");
		btnZipProfile.setBounds(151, 70, 89, 23);
		btnZipProfile.addActionListener(this);
		panel.add(btnZipProfile);

		// Switch statement for Mojang Server Status indicators
		// This is snippet is repeated multiple times
		switch (CheckMojangServers.getMinecraft_net()) {
		case ONLINE:
			// If the ServerStatus ENUM value is ONLINE, show a full lit redstone lamp
			lblminecraft_net = new JLabel("");
			lblminecraft_net.setToolTipText("Minecraft.net is Online");
			lblminecraft_net.setIcon(this.ONLINE);
			lblminecraft_net.setBounds(30, 160, 32, 32);
			panel.add(lblminecraft_net);
			break;
		case UNSTABLE:
			// If the ServerStatus ENUM value is UNSTABLE, show a half lit redstone lamp
			lblminecraft_net = new JLabel("");
			lblminecraft_net.setToolTipText("Minecraft.net is Unstable");
			lblminecraft_net.setIcon(this.UNSTABLE);
			lblminecraft_net.setBounds(30, 160, 32, 32);
			panel.add(lblminecraft_net);
			break;
		case OFFLINE:
			// If the ServerStatus ENUM value is OFFLINE, show a unlit redstone lamp
			lblminecraft_net = new JLabel("");
			lblminecraft_net.setToolTipText("Minecraft.net is Offline");
			lblminecraft_net.setIcon(this.OFFLINE);
			lblminecraft_net.setBounds(30, 160, 32, 32);
			panel.add(lblminecraft_net);
			break;
		default:
			break;
		}

		switch (CheckMojangServers.getSessions()) {
		case ONLINE:
			lbllogin = new JLabel("");
			lbllogin.setToolTipText("Minecraft Session Servers are Online");
			lbllogin.setIcon(this.ONLINE);
			lbllogin.setBounds(70, 160, 32, 32);
			panel.add(lbllogin);
			break;
		case UNSTABLE:
			lbllogin = new JLabel("");
			lbllogin.setToolTipText("Minecraft Session Servers are Unstable");
			lbllogin.setIcon(this.UNSTABLE);
			lbllogin.setBounds(70, 160, 32, 32);
			panel.add(lbllogin);
			break;
		case OFFLINE:
			lbllogin = new JLabel("");
			lbllogin.setToolTipText("Minecraft Session Servers are Offline");
			lbllogin.setIcon(this.OFFLINE);
			lbllogin.setBounds(70, 160, 32, 32);
			panel.add(lbllogin);
			break;
		default:
			break;
		}

		switch (CheckMojangServers.getAccounts()) {
		case ONLINE:
			lblaccount = new JLabel("");
			lblaccount.setToolTipText("Mojang Account Servers are Online");
			lblaccount.setIcon(this.ONLINE);
			lblaccount.setBounds(110, 160, 32, 32);
			panel.add(lblaccount);
			break;
		case UNSTABLE:
			lblaccount = new JLabel("");
			lblaccount.setToolTipText("Mojang Account Servers are Unstable");
			lblaccount.setIcon(this.UNSTABLE);
			lblaccount.setBounds(110, 160, 32, 32);
			panel.add(lblaccount);
			break;
		case OFFLINE:
			lblaccount = new JLabel("");
			lblaccount.setToolTipText("Mojang Account Servers are Offline");
			lblaccount.setIcon(this.OFFLINE);
			lblaccount.setBounds(110, 160, 32, 32);
			panel.add(lblaccount);
			break;
		default:
			break;
		}

		switch (CheckMojangServers.getAuth()) {
		case ONLINE:
			lblauth = new JLabel("");
			lblauth.setToolTipText("Minecraft Login Servers are Online");
			lblauth.setIcon(this.ONLINE);
			lblauth.setBounds(150, 160, 32, 32);
			panel.add(lblauth);
			break;
		case UNSTABLE:
			lblauth = new JLabel("");
			lblauth.setToolTipText("Minecraft Login Servers are Unstable");
			lblauth.setIcon(this.UNSTABLE);
			lblauth.setBounds(150, 160, 32, 32);
			panel.add(lblauth);
			break;
		case OFFLINE:
			lblauth = new JLabel("");
			lblauth.setToolTipText("Minecraft Login Servers are Offline");
			lblauth.setIcon(this.OFFLINE);
			lblauth.setBounds(150, 160, 32, 32);
			panel.add(lblauth);
			break;
		default:
			break;
		}

		switch (CheckMojangServers.getSkins()) {
		case ONLINE:
			lblskins = new JLabel("");
			lblskins.setToolTipText("Minecraft Skins are Online");
			lblskins.setIcon(this.ONLINE);
			lblskins.setBounds(190, 160, 32, 32);
			panel.add(lblskins);
			break;
		case UNSTABLE:
			lblskins = new JLabel("");
			lblskins.setToolTipText("Minecraft Skins are Unstable");
			lblskins.setIcon(this.UNSTABLE);
			lblskins.setBounds(190, 160, 32, 32);
			panel.add(lblskins);
			break;
		case OFFLINE:
			lblskins = new JLabel("");
			lblskins.setToolTipText("Minecraft Skins are Offline");
			lblskins.setIcon(this.OFFLINE);
			lblskins.setBounds(190, 160, 32, 32);
			panel.add(lblskins);
			break;
		default:
			break;
		}
	}


	/**
	 * Action Event listening method
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.btnCreateANew) {
			CreateProfileGUI profGUI = new CreateProfileGUI();
			profGUI.setVisible(true);
		}
		
		if (event.getSource() == this.btnAboutPodlauncher) {
			AboutGUI aGUI = new AboutGUI();
			aGUI.setVisible(true);
		}

		if (event.getSource() == this.btnEditProfile) {
			String profile2edit = (String) this.profileComboBox.getSelectedItem();
			EditProfileGUI epgui = new EditProfileGUI(profile2edit);
			epgui.setVisible(true);
		}

		if (event.getSource() == this.btnLaunchProfile) {
			if (PodLauncher.inDebugMode()) {
				this.timesLaunched = this.timesLaunched  + 1;
				System.out.println("Time the Launched button has fired: " + this.timesLaunched);
			}
			if (this.timesLaunched > 1) {
				return;
			}
			//String gameType = LauncherConfig.getGameType(this.profileComboBox.getSelectedItem().toString());
			this.btnLaunchProfile.removeActionListener(this);
			progressBar.setVisible(true);
			this.btnLaunchProfile.setEnabled(false);
			this.panel.repaint();
			new LaunchMinecraft(this.profileComboBox.getSelectedItem().toString());
			/*
			if (gameType.equals("forge")) {
				//TODO Forge Launching
			} else {
			}
			*/
		}

		if (event.getSource() == this.btnRemoveProfile) {
			LauncherConfig.getProfile(this.profileComboBox.getSelectedItem().toString(), true);
			LauncherConfig.updateLastProfile(LauncherConfig.getProfiles()[0]);
			this.refreshProfileList();
		}
		
		if (event.getSource() == this.btnZipProfile) {
			JSONObject profile = LauncherConfig.getProfile(this.profileComboBox.getSelectedItem().toString(), false);
			new ProfileZipper((String) profile.get("Game Directory"));
		}

	}


	/**
	 * Gets the current MainGUI instance
	 * @return The current instance of MainGUI
	 */
	public static MainGUI getInstance() {
		return instance;
	}


	
	@Override
	public void finish() {
		this.progressBar.setValue(this.progressBar.getMaximum());
	}


	@Override
	public void incrementProgress(int arg0) {
		this.progressBar.setValue(arg0);
	}


	@Override
	public void setMax(int arg0) {
		this.progressBar.setMaximum(arg0);		
	}


	@Override
	public void setProgress(int arg0) {
		this.progressBar.setValue(arg0);
	}

	public void refreshProfileList() {
		this.profileComboBox.setModel(new DefaultComboBoxModel<String>(LauncherConfig.getProfiles()));
		this.panel.repaint();
	}


	/**
	 * Item State Change Event listener, used for the Canidate Build checkbox
	 */
	@Override
	public void itemStateChanged(ItemEvent evt) {
		if (evt.getItem() == this.chckbxUseCanidateBuilds) {
			LauncherConfig.setUseCanidateBuilds(this.chckbxUseCanidateBuilds.isSelected());
		}
	}
}
