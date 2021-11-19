package Behavior;

import Cartes.RumourCard;
import gamebody.Charactor;

public class RevealRumourCard_Witch extends Behavior{
	private Charactor reasonOne;
	
	public RevealRumourCard_Witch(Charactor re, Charactor actor) {super(actor); reasonOne = re;}
	
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
		myCards[answer-1].witch();
	}
}
