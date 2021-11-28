package Cartes;

import Behavior.Behavior;
import CardEffects.AccuserDiscard;
import CardEffects.CardEffect;
import CardEffects.RevealYourIdentity;
import CardEffects.TakeNextTurn;
import gamebody.RevealedCardArea;

public class Cauldron extends RumourCard{
	public Cauldron() {
		super("Cauldron",
				new CardEffect[] {new AccuserDiscard(), new TakeNextTurn()},
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
