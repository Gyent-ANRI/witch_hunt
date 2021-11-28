package Cartes;

import java.util.Iterator;
import java.util.LinkedList;

import Behavior.Behavior;
import Behavior.RevealRumourCard_Witch;
import CardEffects.CardEffect;
import CardEffects.ChooseNextPlayer;
import CardEffects.TakeNextTurn;
import CardEffects.TakeOwnRevealedCard;
import Players.Charactor;
import gamebody.RevealedCardArea;

public class PointedHat extends RumourCard{
	
	public PointedHat() {
		super("PointedHat",
				new CardEffect[] {new TakeOwnRevealedCard(),new TakeNextTurn()},
				new CardEffect[] {new TakeOwnRevealedCard(),new ChooseNextPlayer()}
				);
	}
	
	private boolean hasRevealedCard(Charactor actor) {
		LinkedList<RumourCard> revealedCard = RevealedCardArea.getObject().getCard();
		Iterator<RumourCard> it = revealedCard.iterator();
		while(it.hasNext()) {
			if(it.next().getOwner().equals(actor))
				return true;
			else ;
		}
		return false;
	}
	
	public void witch(Behavior behavior) {
		if(!hasRevealedCard(behavior.getActor())) {
			behavior.getActor().getInteractionWindow().outPut("Only playable if you have a revealed rumour card");
			behavior.getActor().accused(((RevealRumourCard_Witch)behavior).getReasonOne() );
		}
		else {
			behavior.getActor().reduceCard(this);
			RevealedCardArea.getObject().addCard(this);
			super.witch(behavior);
		}
	}
	
	public void hunt(Behavior behavior) {
		if(hasRevealedCard(behavior.getActor())) {
			behavior.getActor().reduceCard(this);
			RevealedCardArea.getObject().addCard(this);
			super.hunt(behavior);
		}
		else {
			behavior.getActor().getInteractionWindow().outPut("Only playable if you have a revealed rumour card");
			behavior.getActor().takeTurn();
		}
	}
}
