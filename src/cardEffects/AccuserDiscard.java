package cardEffects;

import behavior.Behavior;
import behavior.RevealRumourCard_Witch;
import cards.RumourCard;
import gamebody.DisCardArea;
import players.Charactor;

public class AccuserDiscard extends CardEffect{
	public AccuserDiscard() {};
	
	public void effective(Behavior behavior) {
		//get who accuse you
		Charactor reasonOne = ((RevealRumourCard_Witch)behavior).getReasonOne();
		
		//reasonOne discard
		RumourCard[] myCards = reasonOne.cardList();
		int answer = (int)(Math.random()*(myCards.length-0.1));
		reasonOne.reduceCard(myCards[answer]);
		DisCardArea.getObject().addCard(myCards[answer]);
	}

}
