package cards;

import java.util.Iterator;
import java.util.LinkedList;

import behavior.Behavior;
import behavior.RevealRumourCard_Witch;
import cardEffects.CardEffect;
import cardEffects.ChooseNextPlayer;
import cardEffects.TakeNextTurn;
import cardEffects.TakeOwnRevealedCard;
import gamebody.RevealedCardArea;
import players.Charactor;

public class PointedHat extends RumourCard{
	
	public PointedHat() {
		super("PointedHat",
				new CardEffect[] {new TakeOwnRevealedCard(),new TakeNextTurn()},
				new CardEffect[] {new TakeOwnRevealedCard(),new ChooseNextPlayer()}
				);
		
		super.setProperty(new String[] {
				"Only playable if you have a revealed Rumour card",
				"Take one of your own revealed Rumour card into your hand | Take next turn",
				"Take one of your own revealed Rumour card into your hand | Choose next player"
		});
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
