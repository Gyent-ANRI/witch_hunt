package CardEffects;

import Behavior.Behavior;
import Behavior.RevealRumourCard_Witch;
import Cartes.RumourCard;
import Players.Charactor;
import gamebody.DisCardArea;

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
