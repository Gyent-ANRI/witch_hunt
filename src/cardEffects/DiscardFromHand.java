package cardEffects;

import behavior.Behavior;
import cards.RumourCard;
import gamebody.DisCardArea;

public class DiscardFromHand extends CardEffect{
	public DiscardFromHand() {};
	
	public void effective(Behavior behavior) {
		RumourCard[] myCards = behavior.getActor().cardList();
		//get name list
		String[] nameOfCards = new String[myCards.length];
		for(int i = 1; i <= myCards.length; i++) {
			nameOfCards[i-1] = myCards[i-1].getName(); 
		}
				
		//ask actor 
		behavior.getActor().getInteractionWindow().outPut("Choose a card to discard");
		int answer = behavior.getActor().getInteractionWindow().chooseCardFromHand(nameOfCards);
		behavior.getActor().reduceCard(myCards[answer-1]);
		DisCardArea.getObject().addCard(myCards[answer-1]);
	}
}
