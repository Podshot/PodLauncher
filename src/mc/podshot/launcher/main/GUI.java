package mc.podshot.launcher.main;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSlider;

public class GUI extends JPanel implements ActionListener, PropertyChangeListener {
	// Main GUI Class, I may split up into multiple classes later
	private static final long serialVersionUID = 1L;
	private Task task;
	private JProgressBar progressBar;
	private JButton startButton;
	private JTextField textField;
	private final static String newline = "\n";
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	private JTextArea taskOutput;
	private JPasswordField passwordfield;
	private JSlider slider;
	
    
    class Task extends SwingWorker<Void, Void> {

		@Override
		protected void doInBackground() throws Exception {
			Random random = new Random();
			int progress = 0;
			setProgress(0);
			while (progress < 100) {
				try {
					Thread.sleep(random.nextInt(1000));
				} catch (InterruptedException ignore) {}
				progress += random.nextInt(10);
				setProgress(Math.min(progress, 100));
			}
			return null;
		}
    	
    }
    
    public void done() {
    	Toolkit.getDefaultToolkit().beep();
    	startButton.setEnabled(true);
    	setCursor(null);
    	taskOutput.append("Done!\n");
    }
    
    public GUI() {
    	
    	
    	textField = new JTextField(20);
    	textField.addActionListener(this);
    	
    	passwordfield = new JPasswordField(20);
        passwordfield.addActionListener(this);
        
        JLabel label = new JLabel("Enter your password: ");
        label.setLabelFor(passwordfield);
        JLabel userLabel = new JLabel("Enter your username: ");
        userLabel.setLabelFor(textField);
    	
    	taskOutput = new JTextArea(5, 25);
    	taskOutput.setMargin(new Insets(5,5,5,5));
    	taskOutput.setEditable(false);
    	
    	FlowLayout fl_textPane = new FlowLayout(FlowLayout.TRAILING);
    	fl_textPane.setAlignOnBaseline(true);
    	JPanel textPane = new JPanel(fl_textPane);
    	textPane.add(userLabel);
        textPane.add(textField);
    	textPane.add(label);
        textPane.add(passwordfield);
    	JScrollPane scrollPane = new JScrollPane(taskOutput);
    	setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    	
    	startButton = new JButton("Launch");
    	startButton.setActionCommand("start");
    	startButton.addActionListener(this);
    	
    	progressBar = new JProgressBar(0, 100);
    	progressBar.setValue(0);
    	progressBar.setStringPainted(true);
    	
    	JPanel panel = new JPanel(new GridBagLayout());
    	
        //panel.add(label);
        //panel.add(passwordfield);
    	panel.add(startButton);
    	panel.add(progressBar);
    	
    	JPanel optionPanel = new JPanel();
    	GroupLayout groupLayout = new GroupLayout(this);
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.TRAILING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addContainerGap()
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addGroup(groupLayout.createSequentialGroup()
    						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
    						.addPreferredGap(ComponentPlacement.RELATED)
    						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    					.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    					.addGroup(groupLayout.createSequentialGroup()
    						.addComponent(optionPanel, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
    						.addContainerGap())))
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addGroup(groupLayout.createSequentialGroup()
    						.addGap(10)
    						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    					.addGroup(groupLayout.createSequentialGroup()
    						.addPreferredGap(ComponentPlacement.RELATED)
    						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
    				.addPreferredGap(ComponentPlacement.UNRELATED)
    				.addComponent(optionPanel, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
    				.addContainerGap())
    	);
    	
    	JLabel lblRam = new JLabel("RAM");
    	optionPanel.add(lblRam);
    	
    	JSlider slider = new JSlider();
    	lblRam.setLabelFor(slider);
    	slider.setToolTipText("RAM Amount (In Gigabytes)");
    	slider.setMajorTickSpacing(1);
    	slider.setValue(1);
    	slider.setPaintLabels(true);
    	slider.setMinimum(1);
    	slider.setMaximum(10);
    	optionPanel.add(slider);
    	setLayout(groupLayout);
    }
    
    @Override
	public void actionPerformed(ActionEvent evt) {
    	startButton.setEnabled(false);
    	String user = textField.getText();
    	char[] charedpass = passwordfield.getPassword();
    	String pass = String.valueOf(charedpass);
    	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    	task = new Task();
    	taskOutput.append(user + newline);
    	taskOutput.append(pass + newline);
    	//launch doLaunch = new launch(user, pass);
    	task.addPropertyChangeListener(this);
    	task.execute();
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
			taskOutput.append(String.format("Completed %d%% of task.\n", task.getProgress()));
		}
		
	}
	
	private static void createAndShowGUI() {
		
		JFrame frame = new JFrame("Launcher Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent newContentPane = new GUI();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
