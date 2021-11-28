package CardEffects;

import java.util.Iterator;
import java.util.LinkedList;

import Behavior.Behavior;
import Cartes.PointedHat;
import Cartes.RumourCard;
import gamebody.RevealedCardArea;

public class TakeOwnRevealedCard extends CardEffect{
	public TakeOwnRevealedCard() {};
	
	public void effective(Behavior behavior) {
		
		//get list of actor's revealed card
		LinkedList<RumourCard> myRevealedCard = new LinkedList<RumourCard>();
		LinkedList<RumourCard> allRevealedCard = RevealedCardArea.getObject().getCard();
		Iterator<RumourCard> it = allRevealedCard.iterator();
		while(it.hasNext()) {
			RumourCard card = it.next();
			if(card.getOwner() == behavior.getActor() && !(card instanceof PointedHat))
				myRevealedCard.add(card);
		}
		
		//get name list
		String[] namelist = new String[myRevealedCard.size()];
		it = myRevealedCard.iterator();
		int i = 0;
		while(it.hasNext()) {
			namelist[i] = it.next().getName();
			i++;
		}
		
		//ask actor to choose one
		behavior.getActor().getInteractionWindow().outPut("Choose one card to get: ");
		int answer = behavior.getActor().getInteractionWindow().makeChoice(namelist);
		
		//actor get choosen card and revealed-card-area reduce choosen card
		RumourCard choosenCard = myRevealedCard.get(answer-1);
		RevealedCardArea.getObject().reduceCard(choosenCard);
		behavior.getActor().getCard(choosenCard);
		
	}
}
