package Cartes;

import Behavior.Behavior;
import CardEffects.CardEffect;
import CardEffects.ChooseNextPlayer;
import CardEffects.TakeNextTurn;
import CardEffects.TakeOthersRevealedCard;
import gamebody.RevealedCardArea;

public class PetNewt extends RumourCard{
	public PetNewt() {
		super("PetNewt",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new TakeOthersRevealedCard(), new ChooseNextPlayer()});
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
