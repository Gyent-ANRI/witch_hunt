package cardEffects;

import java.util.Iterator;
import java.util.LinkedList;

import behavior.Behavior;
import cards.RumourCard;
import gamebody.DisCardArea;

public class AddDiscardedCard extends CardEffect{
	public AddDiscardedCard() {};
	
	public void effective(Behavior behavior) {
		LinkedList<RumourCard> myCard = DisCardArea.getObject().getCard();

		//get name list
		String[] namelist = new String[myCard.size()];
		Iterator<RumourCard> it = myCard.iterator();
		int i = 0;
		while(it.hasNext()) {
			namelist[i] = it.next().getName();
			i++;
		}
		
		//ask actor to choose one
		behavior.getActor().getInteractionWindow().outPut("Choose one card to get: ");
		int answer = behavior.getActor().getInteractionWindow().makeChoice(namelist);
				
		//actor get choosen card and revealed-card-area reduce choosen card
		RumourCard choosenCard = myCard.get(answer-1);
		DisCardArea.getObject().reduceCard(choosenCard);
		behavior.getActor().getCard(choosenCard);
	}

}
