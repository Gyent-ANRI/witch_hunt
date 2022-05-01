package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

import gamebody.DisCardArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cards.RumourCard;

/**
 * view to show discarded card
 * @author Jiyang QI
 *
 */
public class DiscardedCardFrame implements Observer{
	
	private final static int length = 150;
	private final static int width = 50;
	private final static int iniPositionX = 40;
	private final static int iniPositionY = 50;
	private final static int gapX = 160;
	private final static int gapY = 60;

	private JFrame frame;
	private LinkedList<JButton> cardsButton;
	private JTextField owner;
	private JTextField prop;
	private JTextField witch;
	private JTextField hunt;
	/**
	 * Create the application.
	 */
	public DiscardedCardFrame() {
		initialize();
		DisCardArea.getObject().addObserver(this);
		cardsButton = new LinkedList<JButton>();
		for(int line = 0; line < 2; line++) {
			for(int colomn = 1; colomn <= 5; colomn++) {
				JButton newButton = new JButton();
				newButton.setBounds(iniPositionX + (colomn-1)*gapX,
						iniPositionY + line*gapY,length, width);
				newButton.setVisible(false);
				cardsButton.add(newButton);
				frame.getContentPane().add(newButton);
			}
		}
		
		frameUpdate();
		
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Discarded Card");
		frame.setBounds(200,200,900,600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("owner");
		lblNewLabel.setBounds(38, 234, 126, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblProp = new JLabel("property");
		lblProp.setBounds(38, 294, 126, 25);
		frame.getContentPane().add(lblProp);
		
		JLabel lblWitchEffect = new JLabel("witch effect");
		lblWitchEffect.setBounds(38, 355, 126, 25);
		frame.getContentPane().add(lblWitchEffect);
		
		JLabel lblHuntEffect = new JLabel("hunt effect");
		lblHuntEffect.setBounds(38, 423, 126, 25);
		frame.getContentPane().add(lblHuntEffect);
		
		owner = new JTextField();
		owner.setBounds(27, 258, 621, 31);
		frame.getContentPane().add(owner);
		owner.setColumns(10);
		
		prop = new JTextField();
		prop.setColumns(10);
		prop.setBounds(27, 324, 621, 31);
		frame.getContentPane().add(prop);
		
		witch = new JTextField();
		witch.setColumns(10);
		witch.setBounds(27, 392, 621, 31);
		frame.getContentPane().add(witch);
		
		hunt = new JTextField();
		hunt.setColumns(10);
		hunt.setBounds(27, 458, 621, 31);
		frame.getContentPane().add(hunt);
	}
	
	/**
	 * When called, it will read the list of discarded card from the DisCardedArea
	 * and then set the buttons
	 */
	private void frameUpdate() {
		LinkedList<RumourCard> cardList = DisCardArea.getObject().getCard();
		
		for(int i = 0; i < cardList.size(); i++) {
			JButton button = cardsButton.get(i);
			button.setText(cardList.get(i).getName());
			button.setVisible(true);
			button.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		owner.setText(cardList.get(cardsButton.indexOf(button)).getOwner().getName());
			 		prop.setText(cardList.get(cardsButton.indexOf(button)).getProperty()[0]);
			 		witch.setText(cardList.get(cardsButton.indexOf(button)).getProperty()[1]);
			 		hunt.setText(cardList.get(cardsButton.indexOf(button)).getProperty()[2]);
			 	}
			 });
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		frameUpdate();
	}
}

