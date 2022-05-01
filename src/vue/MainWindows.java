package vue;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import cards.RumourCard;
import gamebody.GameController;
import gamebody.Identity;
import gamebody.RoundController;
import players.Charactor;
import players.InputManager;
import players.InteractionWindow;
import threads.GraphMoniteur;

import java.util.Observer;
import java.util.LinkedList;
import java.util.Observable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * The main view used by the player with their cards, scoreboard, game output information 
 * and buttons to open the discard view and revealed card view
 * @author Jiyang QI
 *
 */
public class MainWindows implements Observer {

	 private JFrame fenetre;
	 private RumourCard[] cardsMain;
	 private LinkedList<JButton> cards;
	 private LinkedList<JTextArea> cardProperty;
	 private GraphMoniteur moniteur;
	 private JButton confirmButton;
	 private JLabel identity;
	 
	 private JTextArea outputArea;
	 private JScrollPane scrollPane;
	 private int lineText;
	 
	 private int nowChoosenCard;
	 private String textCaching;
	 
	 private Object[][] tableData;
	 private JTable table;
	 private String[] firstline;
	 
	 public MainWindows(String owner, GraphMoniteur moni) {
		 
		 moniteur = moni;
		 nowChoosenCard = 1;
		 
		 fenetre = new JFrame ("Witch Hunt- " + owner);
		 fenetre.setBounds(200,200,900,600);
		 Container reservoir = fenetre.getContentPane();
		 fenetre.getContentPane().setLayout(null);
		 
		 cards = new LinkedList<JButton>();
		 cardProperty = new LinkedList<JTextArea>();
		 
		 JButton card1 = new JButton("New button");
		 card1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e1) {
		 		cardProperty.get(0).setText(cardsMain[0].getProperty()[0]);
		 		cardProperty.get(1).setText(cardsMain[0].getProperty()[1]);
		 		cardProperty.get(2).setText(cardsMain[0].getProperty()[2]);
		 		nowChoosenCard = 1;
		 	}
		 });
		 card1.setBounds(20, 48, 132, 180);
		 reservoir.add(card1);
		 cards.add(card1);
		 
		 JButton card2 = new JButton("New button");
		 card2.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e2) {
			 		cardProperty.get(0).setText(cardsMain[1].getProperty()[0]);
			 		cardProperty.get(1).setText(cardsMain[1].getProperty()[1]);
			 		cardProperty.get(2).setText(cardsMain[1].getProperty()[2]);
			 		nowChoosenCard = 2;
			 	}
			 });
		 card2.setBounds(164, 48, 132, 180);
		 reservoir.add(card2);
		 cards.add(card2);
		 
		 JButton card3 = new JButton("New button");
		 card3.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e3) {
			 		cardProperty.get(0).setText(cardsMain[2].getProperty()[0]);
			 		cardProperty.get(1).setText(cardsMain[2].getProperty()[1]);
			 		cardProperty.get(2).setText(cardsMain[2].getProperty()[2]);
			 		nowChoosenCard = 3;
			 	}
			 });
		 card3.setBounds(306, 48, 132, 180);
		 reservoir.add(card3);
		 cards.add(card3);
		 
		 JButton card4 = new JButton("New button");
		 card4.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e4) {
			 		cardProperty.get(0).setText(cardsMain[3].getProperty()[0]);
			 		cardProperty.get(1).setText(cardsMain[3].getProperty()[1]);
			 		cardProperty.get(2).setText(cardsMain[3].getProperty()[2]);
			 		nowChoosenCard = 4;
			 	}
			 });
		 card4.setBounds(448, 48, 132, 180);
		 reservoir.add(card4);
		 cards.add(card4);
		 
		 cards.forEach(card->card.setVisible(false));
		 
		 
		 JLabel lblNewLabel = new JLabel("Your Cards");
		 lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		 lblNewLabel.setBounds(20, 23, 241, 15);
		 reservoir.add(lblNewLabel);
		 
		 JLabel lblPro = new JLabel("Property");
		 lblPro.setFont(new Font("Arial", Font.PLAIN, 16));
		 lblPro.setBounds(20, 255, 241, 15);
		 reservoir.add(lblPro);
		 
		 JLabel lblWitchEffect = new JLabel("Witch Effect");
		 lblWitchEffect.setFont(new Font("Arial", Font.PLAIN, 16));
		 lblWitchEffect.setBounds(20, 350, 241, 15);
		 reservoir.add(lblWitchEffect);
		 
		 JLabel lblHuntEffect = new JLabel("Hunt Effect");
		 lblHuntEffect.setFont(new Font("Arial", Font.PLAIN, 16));
		 lblHuntEffect.setBounds(20, 449, 241, 15);
		 reservoir.add(lblHuntEffect);
		 
		 JTextArea prop = new JTextArea();
		 prop.setBounds(20, 280, 550, 56);
		 reservoir.add(prop);
		 cardProperty.add(prop);
		 prop.setLineWrap(true); 
		 prop.setWrapStyleWord(true); 
		 
		 JTextArea witch = new JTextArea();
		 witch.setBounds(20, 375, 550, 56);
		 reservoir.add(witch);
		 cardProperty.add(witch);
		 witch.setLineWrap(true); 
		 witch.setWrapStyleWord(true); 
		 
		 
		 JTextArea hunt = new JTextArea();
		 hunt.setBounds(20, 474, 550, 56);
		 reservoir.add(hunt);
		 cardProperty.add(hunt);
		 hunt.setLineWrap(true); 
		 hunt.setWrapStyleWord(true); 
		 
		 confirmButton = new JButton("Confirm");
		 confirmButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		moniteur.setAnswer(String.valueOf(nowChoosenCard));
		 		moniteur.march();
		 		confirmButton.setEnabled(false);
		 	}
		 });
		 confirmButton.setBounds(670, 510, 97, 23);
		 confirmButton.setEnabled(false);
		 fenetre.getContentPane().add(confirmButton);
		 
		 this.lineText = 1;
		 this.outputArea = new JTextArea("welcome to witch hunt\n",1,10);
		 this.scrollPane = new JScrollPane(outputArea);
		 scrollPane.setBounds(590, 300,270,200);
		 reservoir.add(scrollPane);
		 
		 JButton revealedCardWindow = new JButton("Revealed Card Area");
		 revealedCardWindow.setBounds(650, 178, 200, 50);
		 revealedCardWindow.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		new RevealedCardFrame();
			 	}
		 });
		 reservoir.add(revealedCardWindow);
		 
		 JButton discardedCardWindow = new JButton("Discarded Card Area");
		 discardedCardWindow.setBounds(650, 239, 200, 50);
		 discardedCardWindow.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		new DiscardedCardFrame();
			 	}
		 });
		 reservoir.add(discardedCardWindow);
		 
		 //a table to present: name score identity outRound
		 int line = GameController.getObject().getCharactorList().size();
		 tableData = new Object[line][4];
		 for(int i = 0; i < line; i++) {
			 tableData[i][0] = GameController.getObject().getCharactorList().get(i).getName();
			 tableData[i][1] = "0";
			 tableData[i][2] = "unknown";
			 tableData[i][3] = "false";
		 }
		 firstline = new String[]{"name","score","identity","out round"};
		 table = new JTable(tableData,firstline);
		 table.setFont(new Font("Arial", Font.BOLD, 12));
		 table.setModel(new DefaultTableModel(tableData,firstline));
		 table.setBackground(SystemColor.control);
		 table.setBounds(611, 35, 260, 130);
		 table.getTableHeader().setBounds(611, 25, 260, 10);
		 table.setPreferredScrollableViewportSize(new Dimension(500, 500));
		 table.setVisible(true);
		 
		 TableColumnModel cm = table.getColumnModel();
		 cm.getColumn(1).setMaxWidth(35);
		 cm.getColumn(1).setMinWidth(35);
		 cm.getColumn(0).setMinWidth(100);
		 cm.getColumn(0).setMaxWidth(100);

		 reservoir.add(table.getTableHeader());
		 reservoir.add(table);
		 
		 identity = new JLabel("<Identity>");
		 identity.setFont(new Font("Arial Black", Font.BOLD, 13));
		 identity.setHorizontalAlignment(SwingConstants.CENTER);
		 identity.setBounds(221, 10, 174, 28);
		 fenetre.getContentPane().add(identity);
		 
		 
		 fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 //fenetre.pack();
		 fenetre.setVisible(true);
	 }
	 
	 public void update(Observable o, Object arg) {
		if(arg instanceof RumourCard[]) {
			cardsMain = (RumourCard[])arg;
			cards.forEach(card->card.setVisible(false));
			for(int i = 0; i < cardsMain.length; i++) {
				cards.get(i).setText(cardsMain[i].getName());
				cards.get(i).setVisible(true);
			}
		}
		
		if(arg instanceof String) {
			this.textCaching = (String)arg;

			 if(this.lineText >= 20) {
				 outputArea.setText(" ");
				 lineText = 0;
			 }

			 outputArea.append((String)arg + '\n');
			 lineText += 1;
		}
		
		if(arg instanceof InteractionWindow) {
			updateTable();
		}
		
		if(arg instanceof Identity) {
			switch((Identity)arg) {
			case villager:
				identity.setText("<Villager>");
				break;
			case witch:
				identity.setText("<witch>");
				break;
			default: 
				identity.setText("<Identity>");
			}
		}
	 }
	 
	 private void updateTable() {
		 LinkedList<Charactor> charactorList= GameController.getObject().getCharactorList();

		 for(int i = 0; i < charactorList.size(); i++) {
			 tableData[i][0] = charactorList.get(i).getName();
			 tableData[i][1] = charactorList.get(i).getScore();
			 if(charactorList.get(i).identityRevealed()) 
				 tableData[i][2] = charactorList.get(i).getIdentity();
			 else 
				 tableData[i][2] = "unknown";
			 tableData[i][3] = String.valueOf(
					 !RoundController.getObject().getCharactorList().contains(charactorList.get(i)));
		 }
		 TableModel dataModel = new DefaultTableModel(tableData, firstline);
		 table.setModel(dataModel);
	 }
	 
	 /**
	  * set confrimButton Enabled
	  */
	 public void cofirmButtonEnable() {
		 confirmButton.setEnabled(true);;
	 }
	 
	 /**
	  * Returns the serial number of the currently selected card
	  * @return
	  */
	 public int getChoosenCard() {
		 return nowChoosenCard;
	 }

}
