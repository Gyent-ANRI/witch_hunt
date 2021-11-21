package Behavior;

import Cartes.RumourCard;
import gamebody.Charactor;

public class RevealRumourCard_Hunt extends Behavior{
	
	public RevealRumourCard_Hunt(Charactor actor) {super(actor);}
	
	public void behave() {
		//get name list
		RumourCard[] myCards = super.getActor().cardList();
		String[] nameOfCards = new String[myCards.length];
		for(int i = 1; i <= myCards.length; i++) {
			nameOfCards[i-1] = myCards[i-1].getName(); 
		}
				
		//ask actor 
		super.getActor().getInteractionWindow().outPut("please chose the card you want to reveal");
		int answer = super.getActor().getInteractionWindow().makeChoice(nameOfCards);
		//super.getActor().disCard(myCards[answer-1]);
		myCards[answer-1].hunt();
		
	}
}
