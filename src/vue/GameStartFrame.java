package vue;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import players.InputManager;

import javax.swing.JTextArea;

/**
 * this view is use for the start of the game
 * @author Jiyang QI
 *
 */
public class GameStartFrame {

	private JFrame frame;
	private JLabel info;
	private JButton confirm;
	private JLabel picture;
	private JButton startButton;
	private JTextArea textArea;

	/**
	 * Create the application.
	 */
	public GameStartFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 347, 237);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		picture = new JLabel(new ImageIcon(GameStartFrame.class.getResource("/images/image.JPG")));
		picture.setBounds(0, 0, 330, 201);
		frame.getContentPane().add(picture);
		
		startButton = new JButton("Game Start");
		startButton.setBounds(107, 150, 97, 23);
		frame.getContentPane().add(startButton);
		
		info = new JLabel("");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setBounds(46, 25, 237, 34);
		info.setVisible(false);
		frame.getContentPane().add(info);
		
		textArea = new JTextArea();
		textArea.setBounds(46, 69, 237, 34);
		textArea.setVisible(false);
		frame.getContentPane().add(textArea);
		
		confirm = new JButton("confirm");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputManager.getObject().getGraphMoniteur().setAnswer(textArea.getText());
				textArea.setText(null);
				InputManager.getObject().getGraphMoniteur().march();
				confirm.setEnabled(false);
			}
		});
		confirm.setBounds(107, 132, 97, 23);
		confirm.setVisible(false);
		frame.getContentPane().add(confirm);
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picture.setVisible(false);
				startButton.setVisible(false);
				info.setVisible(true);
				textArea.setVisible(true);
				confirm.setVisible(true);
				InputManager.getObject().getGraphMoniteur().setAnswer("1");
				InputManager.getObject().getGraphMoniteur().march();
			}
		});
		
		frame.setVisible(true);
	}
	
	public void setLable(String arg) {
		info.setText(arg);
		confirm.setEnabled(true);
	}
	
	public void gameStarted() {
		picture.setVisible(false);
		startButton.setVisible(false);
		info.setVisible(true);
		textArea.setVisible(true);
		confirm.setVisible(true);
	}
	
	public void dispose() {
		frame.dispose();
	}
}
