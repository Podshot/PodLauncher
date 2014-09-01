package io.github.podshot.Podlauncher.gui;

import io.github.podshot.Podlauncher.GetMinecraftVersions;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;

public class ProfileGUI extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldProfileName;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private JButton btnCreateProfile;
	private JComboBox<String> comboBox;
	private JTextField textFieldGameDir;
	private ButtonGroup initRamBtnGrp;
	private ButtonGroup maxRamBtnGrp;
	private JTextField initCustomRam;
	private JTextField maxCustomRam;
	private JRadioButton rdbtnInit256Megabytes;
	private JRadioButton rdbtnInit512Megabytes;
	private JRadioButton rdbtnInit1Gigabyte;
	private JRadioButton rdbtnInit1_5Gigabytes;
	private JRadioButton rdbtnInit2Gigabytes;
	private JRadioButton rdbtnInitCustom;
	private JRadioButton rdbtnMax256Megabytes;
	private JRadioButton rdbtnMax512Megabytes;
	private JRadioButton rdbtnMax1Gigabyte;
	private JRadioButton rdbtnMax1_5Gigabytes;
	private JRadioButton rdbtnMax2Gigabytes;
	private JRadioButton rdbtnMaxCustom;

	public ProfileGUI() {
		this.setTitle("Create a new Profile");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(650, 300);

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
		textFieldGameDir.setBounds(115, 167, 175, 20);
		getContentPane().add(textFieldGameDir);
		textFieldGameDir.setColumns(10);

		JLabel lblRamOptions = new JLabel("RAM Init Heap Options");
		lblRamOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblRamOptions.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRamOptions.setBounds(314, 10, 136, 14);
		getContentPane().add(lblRamOptions);

		initRamBtnGrp = new ButtonGroup();
		maxRamBtnGrp = new ButtonGroup();


		rdbtnInit256Megabytes = new JRadioButton("256 Megabytes");
		rdbtnInit256Megabytes.setBounds(324, 31, 109, 23);
		rdbtnInit256Megabytes.setActionCommand("256M");
		rdbtnInit256Megabytes.addItemListener(this);
		initRamBtnGrp.add(rdbtnInit256Megabytes);
		getContentPane().add(rdbtnInit256Megabytes);

		rdbtnInit512Megabytes = new JRadioButton("512 Megabytes");
		rdbtnInit512Megabytes.setBounds(324, 57, 109, 23);
		rdbtnInit512Megabytes.setActionCommand("512M");
		rdbtnInit512Megabytes.addItemListener(this);
		initRamBtnGrp.add(rdbtnInit512Megabytes);
		rdbtnInit512Megabytes.setSelected(true);
		getContentPane().add(rdbtnInit512Megabytes);

		rdbtnInit1Gigabyte = new JRadioButton("1 Gigabyte");
		rdbtnInit1Gigabyte.setBounds(324, 86, 109, 23);
		rdbtnInit1Gigabyte.setActionCommand("1G");
		initRamBtnGrp.add(rdbtnInit1Gigabyte);
		getContentPane().add(rdbtnInit1Gigabyte);

		rdbtnInit1_5Gigabytes = new JRadioButton("1.5 Gigabytes");
		rdbtnInit1_5Gigabytes.setBounds(324, 112, 109, 23);
		rdbtnInit1_5Gigabytes.setActionCommand("1536M");
		initRamBtnGrp.add(rdbtnInit1_5Gigabytes);
		getContentPane().add(rdbtnInit1_5Gigabytes);

		rdbtnInit2Gigabytes = new JRadioButton("2 Gigabytes");
		rdbtnInit2Gigabytes.setBounds(324, 138, 109, 23);
		rdbtnInit2Gigabytes.setActionCommand("2G");
		initRamBtnGrp.add(rdbtnInit2Gigabytes);
		getContentPane().add(rdbtnInit2Gigabytes);

		rdbtnInitCustom = new JRadioButton("Custom");
		rdbtnInitCustom.setBounds(324, 161, 70, 23);
		rdbtnInitCustom.setActionCommand("Custom");
		initRamBtnGrp.add(rdbtnInitCustom);
		getContentPane().add(rdbtnInitCustom);

		initCustomRam = new JTextField();
		initCustomRam.setText("3G");
		initCustomRam.setBounds(400, 162, 50, 20);
		getContentPane().add(initCustomRam);
		initCustomRam.setColumns(10);

		maxCustomRam = new JTextField();
		maxCustomRam.setText("3G");
		maxCustomRam.setColumns(10);
		maxCustomRam.setBounds(566, 167, 50, 20);
		getContentPane().add(maxCustomRam);

		JLabel lblCustomRamFormat = new JLabel("Custom RAM Format");
		lblCustomRamFormat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCustomRamFormat.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomRamFormat.setBounds(324, 191, 126, 14);
		getContentPane().add(lblCustomRamFormat);

		JTextArea txtrMustBeA = new JTextArea();
		txtrMustBeA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrMustBeA.setLineWrap(true);
		txtrMustBeA.setText("Must be a valid number and must end with either a \"M\" or a \"G\"");
		txtrMustBeA.setBounds(324, 216, 183, 34);
		getContentPane().add(txtrMustBeA);

		rdbtnMax256Megabytes = new JRadioButton("256 Megabytes");
		rdbtnMax256Megabytes.setBounds(490, 31, 109, 23);
		rdbtnMax256Megabytes.setActionCommand("256M");
		maxRamBtnGrp.add(rdbtnMax256Megabytes);
		getContentPane().add(rdbtnMax256Megabytes);

		rdbtnMax512Megabytes = new JRadioButton("512 Megabytes");
		rdbtnMax512Megabytes.setBounds(490, 57, 109, 23);
		rdbtnMax512Megabytes.setActionCommand("512M");
		maxRamBtnGrp.add(rdbtnMax512Megabytes);
		getContentPane().add(rdbtnMax512Megabytes);

		rdbtnMax1Gigabyte = new JRadioButton("1 Gigabyte");
		rdbtnMax1Gigabyte.setBounds(490, 86, 109, 23);
		rdbtnMax1Gigabyte.setActionCommand("1G");
		rdbtnMax1Gigabyte.setSelected(true);
		maxRamBtnGrp.add(rdbtnMax1Gigabyte);
		getContentPane().add(rdbtnMax1Gigabyte);

		rdbtnMax1_5Gigabytes = new JRadioButton("1.5 Gigabytes");
		rdbtnMax1_5Gigabytes.setBounds(490, 112, 109, 23);
		rdbtnMax1_5Gigabytes.setActionCommand("1536M");
		maxRamBtnGrp.add(rdbtnMax1_5Gigabytes);
		getContentPane().add(rdbtnMax1_5Gigabytes);

		rdbtnMax2Gigabytes = new JRadioButton("2 Gigabytes");
		rdbtnMax2Gigabytes.setBounds(490, 138, 109, 23);
		rdbtnMax2Gigabytes.setActionCommand("2G");
		maxRamBtnGrp.add(rdbtnMax2Gigabytes);
		getContentPane().add(rdbtnMax2Gigabytes);

		rdbtnMaxCustom = new JRadioButton("Custom");
		rdbtnMaxCustom.setBounds(490, 166, 70, 23);
		rdbtnMaxCustom.setActionCommand("Custom");
		maxRamBtnGrp.add(rdbtnMaxCustom);
		getContentPane().add(rdbtnMaxCustom);

		JLabel lblRamMaxHeap = new JLabel("RAM Max Heap Options");
		lblRamMaxHeap.setHorizontalAlignment(SwingConstants.CENTER);
		lblRamMaxHeap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRamMaxHeap.setBounds(490, 10, 134, 14);
		getContentPane().add(lblRamMaxHeap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.btnCreateProfile) {
			//LauncherConfig.addProfile(this.textFieldProfileName.getText(), this.textFieldUsername.getText(), new String(this.passwordFieldPassword.getPassword()), this.textFieldGameDir.getText(), GetMinecraftVersions.stripVersionPrefix(this.comboBox.getSelectedItem().toString()));
			JSONObject profile = new JSONObject();
			profile.put("Profile Name", this.textFieldProfileName.getText());
			profile.put("Username", this.textFieldUsername.getText());
			profile.put("Password", new String(this.passwordFieldPassword.getPassword()));
			profile.put("Game Directory", this.textFieldGameDir.getText());
			profile.put("Minecraft Version", GetMinecraftVersions.stripVersionPrefix(this.comboBox.getSelectedItem().toString()));
			switch (initRamBtnGrp.getSelection().getActionCommand()) {
			default:
				break;
			case "256M":
				profile.put("Init RAM Heap", "256M");
				break;
			case "512M":
				profile.put("Init RAM Heap", "512M");
				break;
			case "1G":
				profile.put("Init RAM Heap", "1G");
				break;
			case "1536M":
				profile.put("Init RAM Heap", "1536M");
				break;
			case "2G":
				profile.put("Init RAM Heap", "2G");
				break;
			case "Custom":
				if (this.initCustomRam.getText().endsWith("G") || this.initCustomRam.getText().endsWith("M")) {
					profile.put("Init RAM Heap", this.initCustomRam.getText());
				}
				break;
			}
			switch (maxRamBtnGrp.getSelection().getActionCommand()) {
			default:
				break;
			case "256M":
				profile.put("Max RAM Heap", "256M");
				break;
			case "512M":
				profile.put("Max RAM Heap", "512M");
				break;
			case "1G":
				profile.put("Max RAM Heap", "1G");
				break;
			case "1536M":
				profile.put("Max RAM Heap", "1536M");
				break;
			case "2G":
				profile.put("Max RAM Heap", "2G");
				break;
			case "Custom":
				if (this.maxCustomRam.getText().endsWith("G") || this.maxCustomRam.getText().endsWith("M")) {
					profile.put("Max RAM Heap", this.initCustomRam.getText());
				}
				break;
			}
			this.dispose();
		}
		if (initRamBtnGrp.getSelection().getActionCommand().equals("Custom")) {
			System.out.println("Custom RAM Option: " + initCustomRam.getText());
		}
		System.out.println(initRamBtnGrp.getSelection().getActionCommand());

	}

	@Override
	public void itemStateChanged(ItemEvent itemEVT) {
		if (itemEVT.getStateChange() == ItemEvent.SELECTED) {
			System.out.println("Item Selected");
		}
		if (itemEVT.getStateChange() == ItemEvent.DESELECTED) {
			System.out.println("Item Deselected");
		}

	}
}
