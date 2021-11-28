package Behavior;

import Cartes.RumourCard;
import Players.Charactor;

public class RevealRumourCard_Hunt extends Behavior{
	
	public RevealRumourCard_Hunt(Charactor actor) {super(actor);}
	
	public void behave() {
		RumourCard[] myCards = super.getActor().cardList();
		
		//check whether actor have card.
		if(!(myCards.length == 0)) {
			//get name list
			String[] nameOfCards = new String[myCards.length];
			for(int i = 1; i <= myCards.length; i++) {
				nameOfCards[i-1] = myCards[i-1].getName(); 
			}
					
			//ask actor 
			super.getActor().getInteractionWindow().outPut("please chose the card you want to reveal");
			int answer = super.getActor().getInteractionWindow().makeChoice(nameOfCards);
			myCards[answer-1].hunt(this);
		}
		else {
			super.getActor().getInteractionWindow().outPut("You don't have rumour card!");
			super.getActor().takeTurn();
		}
	}
}
