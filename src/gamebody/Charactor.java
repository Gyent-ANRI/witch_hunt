package gamebody;

import Behavior.Accuse;
import Behavior.Behavior;
import Behavior.RevealIdentity;
import Behavior.RevealRumourCard_Hunt;
import Behavior.RevealRumourCard_Witch;
import Cartes.RumourCard;


public class Charactor {
	
	private String name;
	private int score;
	private Identity identity;
	private RumourCard[] myCard;
	private Behavior myBehavior;
	private InteractionWindow myInteractionWindow;
	private boolean identityRevealed;
	private boolean outOfRound;
	
	public Charactor() {
		score = 0;
		myInteractionWindow = new InteractionWindow();
		myCard = new RumourCard[0];
		identityRevealed = false;
		outOfRound = false;
	}
	
	public InteractionWindow getInteractionWindow() {return myInteractionWindow;}
	
	public void setName(String n) {name = n;}
	
	public String getName() {return name;}
	
	public void setIdentity(Identity id) {identity = id;}
	
	public RumourCard[] cardList(){return myCard;}
	
	public void outOfRound() {outOfRound = true;}
	
	public void getCard(RumourCard card) {
		RumourCard[] oddCard = myCard;
		myCard = new RumourCard[oddCard.length + 1];
		for (int i = 0; i < oddCard.length; i++) {
			myCard[i] = oddCard[i];
		}
		myCard[oddCard.length] = card;
	}
	
	public void disCard(String cardName) {
		RumourCard[] oddCard = myCard;
		myCard = new RumourCard[myCard.length - 1];
		for(int i = 0; i < oddCard.length; i++) {
			int j = 0;
			if(oddCard[i].getName() == cardName);
			else {
				myCard[j] = oddCard[i];
				j++;
			}
		}
	}
	
	public Identity revealIdentity() {
		identityRevealed = true;
		switch(identity) {
		case witch:
			BroadCast.getObject().broad(name + "'s identity is witch!");
			break;
		case villager:
			BroadCast.getObject().broad(name + "'s identity is villager!");
		}
		return identity;
	}
	
	public void setBehavior(Behavior mybehave) {
		myBehavior = mybehave;
	}
	
	public void takeTurn() {
		myInteractionWindow.outPut("Your turn ! What do you want to do? ");
		int answer = myInteractionWindow.makeChoice(new String[] {"Accuse", "Use Rumour Card"});
		switch(answer) {
		case 1:
			setBehavior(new Accuse(this));
			break;
		case 2:
			setBehavior(new RevealRumourCard_Hunt(this));
		}
		myBehavior.behave();
	}
	
	public void accused(Charactor actor) {
		BroadCast.getObject().broad(name + " is accused");
		myInteractionWindow.outPut("Your are accused ! What do you want to do? ");
		int answer = myInteractionWindow.makeChoice(new String[] {"Use Rumour Card", "Reveal Identity"});
		switch(answer) {
		case 1:
			setBehavior(new RevealRumourCard_Witch(actor, this));
			break;
		case 2:
			setBehavior(new RevealIdentity(actor, this));
		}
		myBehavior.behave();
		
	}
	
	public void modifyScore(int s) {
		
		BroadCast.getObject().broad(name + " has " + s + " score");
		score += s;
		
		if(score >= 5) {
			GameController.getObject().gameOver();
		}
	}
}
