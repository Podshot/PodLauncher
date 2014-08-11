package io.github.podshot.Podlauncher.gui;

import io.github.podshot.Podlauncher.extras.CheckMojangServers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	private JButton btnCreateANew;
	private JLabel lblminecraft_net;
	private JLabel lbllogin;

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

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Test", "Test2", "Test3", "Test4"}));
		comboBox.setBounds(10, 30, 130, 20);
		panel.add(comboBox);

		btnCreateANew = new JButton("Create a new Profile");
		btnCreateANew.setBounds(173, 29, 160, 23);
		btnCreateANew.addActionListener(this);
		panel.add(btnCreateANew);

		//switch (cms.getMinecraft_net()) {
		switch (cms.getMinecraft_net()) {
		case ONLINE:
			lblminecraft_net = new JLabel("");
			lblminecraft_net.setToolTipText("Minecraft.net is Online");
			lblminecraft_net.setIcon(new ImageIcon("C:\\Users\\Ben\\Java\\Projects\\Launcher\\PodLauncher\\src\\io\\github\\podshot\\Podlauncher\\resources\\images\\redstone_lamp_on.png"));
			lblminecraft_net.setBounds(30, 160, 32, 32);
			panel.add(lblminecraft_net);
			break;
		case UNSTABLE:
			lblminecraft_net = new JLabel("");
			lblminecraft_net.setToolTipText("Minecraft.net is Unstable");
			lblminecraft_net.setIcon(new ImageIcon("C:\\Users\\Ben\\Java\\Projects\\Launcher\\PodLauncher\\src\\io\\github\\podshot\\Podlauncher\\resources\\images\\redstone_lamp_unstable.png"));
			lblminecraft_net.setBounds(30, 160, 32, 32);
			panel.add(lblminecraft_net);
			break;
		case OFFLINE:
			lblminecraft_net = new JLabel("");
			lblminecraft_net.setToolTipText("Minecraft.net is Offline");
			lblminecraft_net.setIcon(new ImageIcon("C:\\Users\\Ben\\Java\\Projects\\Launcher\\PodLauncher\\src\\io\\github\\podshot\\Podlauncher\\resources\\images\\redstone_lamp_off.png"));
			lblminecraft_net.setBounds(30, 160, 32, 32);
			panel.add(lblminecraft_net);
			break;
		default:
			break;
		}

		switch (CheckMojangServers.ServerStatus.UNSTABLE) {
		case ONLINE:
			lbllogin = new JLabel("");
			lbllogin.setToolTipText("Minecraft Session Servers are Online");
			lbllogin.setIcon(new ImageIcon("C:\\Users\\Ben\\Java\\Projects\\Launcher\\PodLauncher\\src\\io\\github\\podshot\\Podlauncher\\resources\\images\\redstone_lamp_on.png"));
			lbllogin.setBounds(65, 160, 32, 32);
			panel.add(lbllogin);
			break;
		case UNSTABLE:
			lbllogin = new JLabel("");
			lbllogin.setToolTipText("Minecraft Session Servers are Unstable");
			lbllogin.setIcon(new ImageIcon("C:\\Users\\Ben\\Java\\Projects\\Launcher\\PodLauncher\\src\\io\\github\\podshot\\Podlauncher\\resources\\images\\redstone_lamp_unstable.png"));
			lbllogin.setBounds(65, 160, 32, 32);
			panel.add(lbllogin);
			break;
		default:
			break;
		}
		
			lbllogin = new JLabel("");
			lbllogin.setToolTipText("Minecraft Session Servers are Offline");
			lbllogin.setIcon(new ImageIcon("C:\\Users\\Ben\\Java\\Projects\\Launcher\\PodLauncher\\src\\io\\github\\podshot\\Podlauncher\\resources\\images\\redstone_lamp_off.png"));
			lbllogin.setBounds(65, 160, 32, 32);
			panel.add(lbllogin);
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.btnCreateANew) {
			ProfileGUI profGUI = new ProfileGUI();
			profGUI.setVisible(true);
		}

	}
}
