package behavior;

import cards.RumourCard;
import players.Charactor;


/**
 * Classes describing the behavior�� use the witch effect of a rumour card
 * @author User
 *
 */
public class RevealRumourCard_Witch extends Behavior{
	private Charactor reasonOne;
	
	public RevealRumourCard_Witch(Charactor re, Charactor actor) {super(actor); reasonOne = re;}
	
	public void behave() {
		RumourCard[] myCards = super.getActor().cardList();
		if(!(myCards.length == 0)) {
			//get name list
			String[] nameOfCards = new String[myCards.length];
			for(int i = 1; i <= myCards.length; i++) {
				nameOfCards[i-1] = myCards[i-1].getName(); 
			}
					
			//ask actor 
			super.getActor().getInteractionWindow().outPut("please chose the card you want to reveal");
			int answer = super.getActor().getInteractionWindow().chooseCardFromHand(nameOfCards);
			myCards[answer-1].witch(this);
		}
		else {
			super.getActor().getInteractionWindow().outPut("You don't have rumour card!");
			super.getActor().takeTurn();
		}

	}
	
	public Charactor getReasonOne() {
		return reasonOne;
	}
}
