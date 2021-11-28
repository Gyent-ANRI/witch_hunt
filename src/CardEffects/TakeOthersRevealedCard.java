package CardEffects;

import java.util.Iterator;
import java.util.LinkedList;

import Behavior.Behavior;
import Cartes.RumourCard;
import gamebody.RevealedCardArea;

public class TakeOthersRevealedCard extends CardEffect{
	public TakeOthersRevealedCard() {};
	
	public void effective(Behavior behavior) {
		
		//get list of actor's revealed card
		LinkedList<RumourCard> otherRevealedCard = new LinkedList<RumourCard>();
		LinkedList<RumourCard> allRevealedCard = RevealedCardArea.getObject().getCard();
		Iterator<RumourCard> it = allRevealedCard.iterator();
		while(it.hasNext()) {
			RumourCard card = it.next();
			if(card.getOwner() != behavior.getActor())
				otherRevealedCard.add(card);
		}
				
		//get name list
		String[] namelist = new String[otherRevealedCard.size()];
		it = otherRevealedCard.iterator();
		int i = 0;
		while(it.hasNext()) {
			namelist[i] = it.next().getName();
			i++;
		}
				
		//ask actor to choose one
		behavior.getActor().getInteractionWindow().outPut("Choose one card to get: ");
		int answer = behavior.getActor().getInteractionWindow().makeChoice(namelist);
				
		//actor get choosen card and revealed-card-area reduce choosen card
		RumourCard choosenCard = otherRevealedCard.get(answer-1);
		RevealedCardArea.getObject().reduceCard(choosenCard);
		behavior.getActor().getCard(choosenCard);
	}
}
