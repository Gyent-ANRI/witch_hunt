package cards;

import behavior.Behavior;
import cardEffects.CardEffect;
import cardEffects.TakeCardFromAccuse;
import cardEffects.TakeNextTurn;
import cardEffects.TakeRandomCardFromOther;
import gamebody.RevealedCardArea;

public class HookedNose extends RumourCard{
	public HookedNose() {
		super("HookedNose",
				new CardEffect[] {new TakeCardFromAccuse(),new TakeNextTurn()},
				new CardEffect[] {new TakeRandomCardFromOther()}
				);
		
		super.setProperty(new String[] {
				"No property",
				"Take one card from the hand of the player who accused you | take next turn",
				"Choose next player, before their turn, take a random card from their hand and add it to your hand"
		});
	}
	
	public void witch(Behavior behavior) {
		behavior.getActor().reduceCard(this);
		RevealedCardArea.getObject().addCard(this);
		super.witch(behavior);
	}
	
	public void hunt(Behavior behavior) {
		behavior.getActor().reduceCard(this);
		RevealedCardArea.getObject().addCard(this);
		super.hunt(behavior);
	}
}
