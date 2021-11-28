package Cartes;

import Behavior.Behavior;
import CardEffects.CardEffect;
import CardEffects.ChooseNextPlayer;
import CardEffects.TakeNextTurn;
import gamebody.RevealedCardArea;

public class Broomstick extends RumourCard{
	public Broomstick() {
		super("Broomstick",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new ChooseNextPlayer()});
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
