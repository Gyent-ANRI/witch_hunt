package vue;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;

import javax.swing.JTextField;

import players.InputManager;

import javax.swing.JButton;

/**
 * view to make selection
 * @author Jiyang QI
 *
 */
public class ChooseWindow {
	//property of the buttons
	private final static int length = 150;
	private final static int width = 30;
	private final static int iniPositionX = 40;
	private final static int iniPositionY = 50;
	private final static int gapX = 200;
	private final static int gapY = 40;

	private JFrame frame;
	private JTextField textField;
	private LinkedList<JButton> choices;

	public ChooseWindow(String owner, String message,String[] args) {
		initialize(owner);
		choices = new LinkedList<JButton>();
		for(int line = 0; line < 5; line++) {
			for(int colomn = 1; colomn <= 2; colomn++) {
				JButton newButton = new JButton();
				newButton.setBounds(iniPositionX + (colomn-1)*gapX,
						iniPositionY + line*gapY,length, width);
				newButton.setVisible(false);
				choices.add(newButton);
				frame.getContentPane().add(newButton);
			}
		}
		
		this.textField.setText(message);
		
		for(int i = 0; i < args.length; i++) {
			JButton button = choices.get(i);
			button.setText(args[i]);
			button.setVisible(true);
			button.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		InputManager.getObject().getGraphMoniteur().setAnswer(
			 				String.valueOf(choices.indexOf(button)+1));
			 		InputManager.getObject().getGraphMoniteur().march();
			 	}
			 });
		}
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String owner) {
		frame = new JFrame("Choose Window -- " + owner);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		textField = new JTextField();
		textField.setBounds(89, 22, 248, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEnabled(false);
		
	}
	
	/**
	 * dispose this view
	 */
	public void dispose() {
		frame.dispose();
	}
}
