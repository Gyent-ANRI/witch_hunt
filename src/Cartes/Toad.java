package Cartes;

import Behavior.Behavior;
import CardEffects.CardEffect;
import CardEffects.RevealYourIdentity;
import CardEffects.TakeNextTurn;
import gamebody.RevealedCardArea;

public class Toad extends RumourCard{
	public Toad() {
		super("Toad",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new RevealYourIdentity()});
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
