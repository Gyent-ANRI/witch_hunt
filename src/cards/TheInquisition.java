package cards;

import behavior.Behavior;
import behavior.RevealRumourCard_Witch;
import cardEffects.CardEffect;
import cardEffects.DiscardFromHand;
import cardEffects.SecretlyLookIdentity;
import cardEffects.TakeNextTurn;
import gamebody.Identity;
import gamebody.RevealedCardArea;

public class TheInquisition extends RumourCard{
	public TheInquisition() {
		super("TheInquisition", 
				new CardEffect[] {new DiscardFromHand(),new TakeNextTurn()}, 
				new CardEffect[] {new SecretlyLookIdentity()});
		
		super.setProperty(new String[] {
				"Hunt effect only playable if you have been revealed as a villager",
				"Discard a card from your hand | Take next turn",
				"Choose next player, before their turn, secretly look at their identity"
		});
	}
	
	public void witch(Behavior behavior) {
		if(behavior.getActor().cardList().length == 0) {
			behavior.getActor().getInteractionWindow().outPut("You must have a card to discard");
			behavior.getActor().accused(((RevealRumourCard_Witch)behavior).getReasonOne());
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
