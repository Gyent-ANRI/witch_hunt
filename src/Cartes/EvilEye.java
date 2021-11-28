package Cartes;

import Behavior.Behavior;
import CardEffects.CardEffect;
import CardEffects.ChooseNewAccuser;
import gamebody.RevealedCardArea;

public class EvilEye extends RumourCard{
	public EvilEye() {
		super("EvilEye",
				new CardEffect[] {new ChooseNewAccuser()},
				new CardEffect[] {new ChooseNewAccuser()});
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
