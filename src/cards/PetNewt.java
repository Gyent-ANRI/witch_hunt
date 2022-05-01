package cards;

import java.util.Iterator;
import java.util.LinkedList;

import behavior.Behavior;
import cardEffects.CardEffect;
import cardEffects.ChooseNextPlayer;
import cardEffects.TakeNextTurn;
import cardEffects.TakeOthersRevealedCard;
import gamebody.RevealedCardArea;
import players.Charactor;

public class PetNewt extends RumourCard{
	public PetNewt() {
		super("PetNewt",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new TakeOthersRevealedCard(), new ChooseNextPlayer()});
		
		super.setProperty(new String[] {
				"No property",
				"Take next turn",
				"Take a revealed Rumour card from any other player into your hand | Choose next player"
		});
	}
	
	public void witch(Behavior behavior) {
		behavior.getActor().reduceCard(this);
		RevealedCardArea.getObject().addCard(this);
		super.witch(behavior);
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
	
	private boolean hasRevealedCard(Charactor actor) {
		LinkedList<RumourCard> revealedCard = RevealedCardArea.getObject().getCard();
		Iterator<RumourCard> it = revealedCard.iterator();
		while(it.hasNext()) {
			if(!it.next().getOwner().equals(actor))
				return true;
			else ;
		}
		return false;
	}

}
