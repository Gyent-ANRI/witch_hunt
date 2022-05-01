package cards;

import behavior.Behavior;
import cardEffects.CardEffect;
import cardEffects.ChooseNextPlayer;
import cardEffects.TakeNextTurn;
import gamebody.RevealedCardArea;

public class Broomstick extends RumourCard{
	public Broomstick() {
		super("Broomstick",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new ChooseNextPlayer()});
		
		super.setProperty(new String[] {
				"While revealed, you cannot choosen by the Angry Mob",
				"Take next turn",
				"Choose next player"
		});
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
