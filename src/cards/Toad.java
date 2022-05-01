package cards;

import behavior.Behavior;
import cardEffects.CardEffect;
import cardEffects.RevealYourIdentity;
import cardEffects.TakeNextTurn;
import gamebody.RevealedCardArea;

public class Toad extends RumourCard{
	public Toad() {
		super("Toad",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new RevealYourIdentity()});
		
		super.setProperty(new String[] {
				"No property",
				"Take next turn",
				"Reveal your identity | WITCH: the player to your left take the next turn. VILLAGER: choose next player"
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
