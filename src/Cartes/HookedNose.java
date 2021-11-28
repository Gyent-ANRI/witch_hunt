package Cartes;

import Behavior.Behavior;
import CardEffects.CardEffect;
import CardEffects.TakeCardFromAccuse;
import CardEffects.TakeNextTurn;
import CardEffects.TakeRandomCardFromOther;
import gamebody.RevealedCardArea;

public class HookedNose extends RumourCard{
	public HookedNose() {
		super("HookedNose",
				new CardEffect[] {new TakeCardFromAccuse(),new TakeNextTurn()},
				new CardEffect[] {new TakeRandomCardFromOther()}
				);
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
