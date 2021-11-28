package Cartes;

import Behavior.Behavior;
import CardEffects.AddDiscardedCard;
import CardEffects.CardEffect;
import CardEffects.TakeNextTurn;
import gamebody.RevealedCardArea;

public class BlackCat extends RumourCard{
	public BlackCat() {
		super("BlackCat",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new AddDiscardedCard(), new TakeNextTurn()});
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
