package Cartes;

import Behavior.Behavior;
import CardEffects.CardEffect;
import CardEffects.DiscardFromHand;
import CardEffects.SecretlyLookIdentity;
import CardEffects.TakeNextTurn;
import gamebody.Identity;
import gamebody.RevealedCardArea;

public class TheInquisition extends RumourCard{
	public TheInquisition() {
		super("TheInquisition", 
				new CardEffect[] {new DiscardFromHand(),new TakeNextTurn()}, 
				new CardEffect[] {new SecretlyLookIdentity()});
	}
	
	public void witch(Behavior behavior) {
		if(behavior.getActor().cardList().length == 0) {
			behavior.getActor().getInteractionWindow().outPut("You must have a card to discard");
			behavior.getActor().takeTurn();
		}
		else {
			behavior.getActor().reduceCard(this);
			RevealedCardArea.getObject().addCard(this);
			super.witch(behavior);
		}
	}
	
	public void hunt(Behavior behavior) {
		if(behavior.getActor().identityRevealed() == true &&
				behavior.getActor().getIdentity() == Identity.villager) {
			behavior.getActor().reduceCard(this);
			RevealedCardArea.getObject().addCard(this);
			super.hunt(behavior);
		}
		else {
			behavior.getActor().getInteractionWindow().outPut("Only playable if you have been revealed as villager");
			behavior.getActor().takeTurn();
		}
	}
}
