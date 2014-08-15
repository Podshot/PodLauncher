package io.github.podshot.Podlauncher.gui;

import io.github.podshot.Podlauncher.LaunchMinecraft;
import io.github.podshot.Podlauncher.extras.CheckMojangServers;
import io.github.podshot.Podlauncher.files.LauncherConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> profileComboBox;
	private JButton btnCreateANew;
	private JLabel lblminecraft_net;
	private JLabel lbllogin;
	private JLabel lblaccount;
	private JLabel lblauth;
	private JLabel lblskins;
	private ImageIcon ONLINE = new ImageIcon(this.getClass().getResource("/images/redstone_lamp_on.png"));
	private ImageIcon UNSTABLE = new ImageIcon(this.getClass().getResource("/images/redstone_lamp_unstable.png"));
	private ImageIcon OFFLINE = new ImageIcon(this.getClass().getResource("/images/redstone_lamp_off.png"));
	private JButton btnEditProfile;
	private JButton btnLaunchProfile;

	public MainGUI() {
		CheckMojangServers cms = null;
		try {
			cms = new CheckMojangServers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setTitle("PodLauncher");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 614, 420);
		getContentPane().add(panel);
		panel.setLayout(null);

		profileComboBox = new JComboBox<String>();
		profileComboBox.setModel(new DefaultComboBoxModel<String>(LauncherConfig.getProfiles()));
		profileComboBox.setBounds(10, 30, 130, 20);
		panel.add(profileComboBox);

		btnCreateANew = new JButton("Create a new Profile");
		btnCreateANew.setBounds(173, 29, 160, 23);
		btnCreateANew.addActionListener(this);
		panel.add(btnCreateANew);
		
		btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.setBounds(360, 29, 130, 23);
		panel.add(btnEditProfile);
		
		btnLaunchProfile = new JButton("Launch Profile");
		btnLaunchProfile.setBounds(10, 70, 130, 23);
		panel.add(btnLaunchProfile);

		switch (cms.getMinecraft_net()) {
		case ONLINE:
			lblminecraft_net = new JLabel("");
			lblminecraft_net.setToolTipText("Minecraft.net is Online");
			lblminecraft_net.setIcon(this.ONLINE);
			lblminecraft_net.setBounds(30, 160, 32, 32);
			panel.add(lblminecraft_net);
			break;
		case UNSTABLE:
			lblminecraft_net = new JLabel("");
			lblminecraft_net.setToolTipText("Minecraft.net is Unstable");
			lblminecraft_net.setIcon(this.UNSTABLE);
			lblminecraft_net.setBounds(30, 160, 32, 32);
			panel.add(lblminecraft_net);
			break;
		case OFFLINE:
			lblminecraft_net = new JLabel("");
			lblminecraft_net.setToolTipText("Minecraft.net is Offline");
			lblminecraft_net.setIcon(this.OFFLINE);
			lblminecraft_net.setBounds(30, 160, 32, 32);
			panel.add(lblminecraft_net);
			break;
		default:
			break;
		}

		switch (cms.getSessions()) {
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

		switch (cms.getAccounts()) {
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

		switch (cms.getAuth()) {
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

		switch (cms.getSkins()) {
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


	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.btnCreateANew) {
			ProfileGUI profGUI = new ProfileGUI();
			profGUI.setVisible(true);
		}
		
		if (event.getSource() == this.btnEditProfile) {
			String profile2edit = (String) this.profileComboBox.getSelectedItem();
		}
		
		if (event.getSource() == this.btnLaunchProfile) {
			new LaunchMinecraft(this.profileComboBox.getSelectedItem().toString());
		}

	}
}
