package cards;

import java.util.Iterator;
import java.util.LinkedList;

import behavior.Behavior;
import cardEffects.CardEffect;
import cardEffects.ChooseNextPlayer;
import cardEffects.ChooseOneRevealIdentityOrDiscard;
import gamebody.RevealedCardArea;
import gamebody.RoundController;
import players.Charactor;

public class DuckingStool extends RumourCard{
	public DuckingStool() {
		super("DuckingStool",
				new CardEffect[] {new ChooseNextPlayer()},
				new CardEffect[] {new ChooseOneRevealIdentityOrDiscard()});
		
		super.setProperty(new String[] {
				"No property",
				"Choose next player",
				"Choose a player. They must reveal their identity or discard a card from Their hand | WITCH:"
				+ " you gain one pt, you take next turn. VILLAGER: they take next turn. IF THEY DISCARD: They take"
				+ " next turn."
		});
	}
	public void witch(Behavior behavior) {
		behavior.getActor().reduceCard(this);
		RevealedCardArea.getObject().addCard(this);
		super.witch(behavior);
	}
	
	public void hunt(Behavior behavior) {
		//get players list
		LinkedList<Charactor> myplayers = RoundController.getObject().getCharactorList();
		myplayers.remove(behavior.getActor());
				
		//check if there is wart revealed
		LinkedList<RumourCard> cardlist = RevealedCardArea.getObject().getCard();
		Iterator<RumourCard> it2 = cardlist.iterator();
		while(it2.hasNext()) {
			RumourCard card = it2.next();
			if(card instanceof Wart) {
				myplayers.remove(card.getOwner());
			}
		}
		
		if(myplayers.isEmpty()) {
			behavior.getActor().getInteractionWindow().outPut("You can't choose anyone");
			behavior.getActor().takeTurn();
		}
		else {
			behavior.getActor().reduceCard(this);
			RevealedCardArea.getObject().addCard(this);
			super.hunt(behavior);
		}
	}
}
