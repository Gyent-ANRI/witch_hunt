package cards;

import behavior.Behavior;
import cardEffects.*;
import gamebody.Identity;
import gamebody.RevealedCardArea;


public class AngryMob extends RumourCard{

	
	public AngryMob() {
		super("AngryMob", 
				new CardEffect[] {new TakeNextTurn()}, 
				new CardEffect[] {new RevealAnotherIdentity()});
		
		super.setProperty(new String[] {
				"Hunt effect only playable if you have been revealed as a Villager",
				"Take next turn",
				"Reveal another player's identity | Witch:you gain 2pts, you take next turn. Hunt:you lose 2pts, they take next turn."
		});

	}
	
	public void witch(Behavior behavior) {
		behavior.getActor().reduceCard(this);
		RevealedCardArea.getObject().addCard(this);
		super.witch(behavior);
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
