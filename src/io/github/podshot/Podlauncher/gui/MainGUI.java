package io.github.podshot.Podlauncher.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class MainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	private JButton btnCreateANew;

	public MainGUI() {
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
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.btnCreateANew) {
			new ProfileGUI();
		}

	}
}
