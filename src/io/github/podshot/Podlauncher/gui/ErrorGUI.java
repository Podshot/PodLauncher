package io.github.podshot.Podlauncher.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ErrorGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public ErrorGUI(String msg, StackTraceElement[] stackTrace, Throwable throwableCause, String caughtBy) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		this.setTitle("An Error Occured");
		getContentPane().setLayout(null);
		this.setSize(500, 280);
		this.setAlwaysOnTop(true);

		JLabel lblAnErrorOccured = new JLabel("An Error occured while PodLauncher was running:");
		lblAnErrorOccured.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnErrorOccured.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnErrorOccured.setBounds(51, 11, 349, 33);
		getContentPane().add(lblAnErrorOccured);

		JLabel lblErrorName = new JLabel("Error Name: ");
		lblErrorName.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErrorName.setBounds(10, 55, 83, 14);
		getContentPane().add(lblErrorName);

		JLabel labelErrorName = new JLabel(msg);
		labelErrorName.setBounds(90, 55, 384, 14);
		labelErrorName.setToolTipText(msg);
		getContentPane().add(labelErrorName);

		JLabel lblCaughtBy = new JLabel("Caught By: ");
		lblCaughtBy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCaughtBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaughtBy.setBounds(10, 80, 83, 14);
		getContentPane().add(lblCaughtBy);

		JLabel labelCaughtBy = new JLabel(caughtBy);
		labelCaughtBy.setBounds(90, 80, 290, 14);
		getContentPane().add(labelCaughtBy);

		JTextPane textArea = new JTextPane();
		textArea.setEditable(false);
		textArea.setAutoscrolls(true);
		for (StackTraceElement trace : stackTrace) {
			textArea.setText(textArea.getText() + trace.getClassName() + ":" + trace.getFileName() + ":" + trace.getMethodName() + ":" + trace.getLineNumber() + "\n");
		}
		textArea.setBounds(10, 105, 464, 125);
		getContentPane().add(textArea);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}
