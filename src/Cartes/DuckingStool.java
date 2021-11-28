package Cartes;

import Behavior.Behavior;
import CardEffects.CardEffect;
import CardEffects.ChooseNextPlayer;
import CardEffects.ChooseOneRevealIdentityOrDiscard;
import gamebody.RevealedCardArea;

public class DuckingStool extends RumourCard{
	public DuckingStool() {
		super("DuckingStool",
				new CardEffect[] {new ChooseNextPlayer()},
				new CardEffect[] {new ChooseOneRevealIdentityOrDiscard()});
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
