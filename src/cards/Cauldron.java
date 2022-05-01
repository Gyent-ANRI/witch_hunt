package cards;

import behavior.Behavior;
import cardEffects.AccuserDiscard;
import cardEffects.CardEffect;
import cardEffects.RevealYourIdentity;
import cardEffects.TakeNextTurn;
import gamebody.RevealedCardArea;

public class Cauldron extends RumourCard{
	public Cauldron() {
		super("Cauldron",
				new CardEffect[] {new AccuserDiscard(), new TakeNextTurn()},
				new CardEffect[] {new RevealYourIdentity()});
		
		super.setProperty(new String[] {
				"No property",
				"The player who accused you discards a random card from their hand | take next turn",
				"Reveal your identity | Witch: player to your left takes next turn. Villager: choose next player"
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
