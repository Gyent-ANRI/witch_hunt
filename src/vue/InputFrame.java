package vue;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import players.InputManager;

/**
 * This view displays the question to the player and allows the player to enter a string
 * @author Jiyang QI
 *
 */
public class InputFrame {

	private JFrame frame;
	private String owner;
	private JLabel info;
	private JButton confirm;

	
	public InputFrame(String owner) {
		this.owner = owner;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(owner + "Input Frame");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		info = new JLabel();
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setBounds(85, 43, 237, 34);
		frame.getContentPane().add(info);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(61, 104, 293, 34);
		textArea.setVisible(true);
		frame.getContentPane().add(textArea);
		
		confirm = new JButton("confirm");
		confirm.setBounds(109, 172, 193, 34);
		frame.getContentPane().add(confirm);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputManager.getObject().getGraphMoniteur().setAnswer(textArea.getText());
				InputManager.getObject().getGraphMoniteur().march();
			}
		});
		
		frame.setVisible(false);
	}
	
	public void ask(String info) {
		this.info.setText(info);
		frame.setVisible(true);
		confirm.setEnabled(true);
	}
	
	public void dispose() {
		frame.dispose();
	}

}
