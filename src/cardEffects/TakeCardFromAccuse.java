package cardEffects;

import behavior.Behavior;
import behavior.RevealRumourCard_Witch;
import cards.RumourCard;

public class TakeCardFromAccuse extends CardEffect{
	public TakeCardFromAccuse() {};
	
	public void effective(Behavior behavior) {
		RumourCard[] myCards = ((RevealRumourCard_Witch)behavior).getReasonOne().cardList();
		
		//check whether reasonOne have card.
			if(!(myCards.length == 0)) {
				//get name list
				String[] nameOfCards = new String[myCards.length];
				for(int i = 1; i <= myCards.length; i++) {
					nameOfCards[i-1] = "unknowen card"; 
				}
							
				//ask actor 
				behavior.getActor().getInteractionWindow().outPut("please chose the card you want to get");
				int answer = behavior.getActor().getInteractionWindow().makeChoice(nameOfCards);
				
				//actor get card and reason one loose card
				myCards[answer-1].setOwner(behavior.getActor());
				behavior.getActor().getCard(myCards[answer-1]);
				((RevealRumourCard_Witch)behavior).getReasonOne().reduceCard(myCards[answer-1]);
			}
			else {
				behavior.getActor().getInteractionWindow().outPut("The player who accuse you has no card");
			}
		
	}

}
