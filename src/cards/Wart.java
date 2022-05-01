package cards;

import behavior.Behavior;
import cardEffects.CardEffect;
import cardEffects.ChooseNextPlayer;
import cardEffects.TakeNextTurn;
import gamebody.RevealedCardArea;

public class Wart extends RumourCard{
	public Wart() {
		super("Wart",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new ChooseNextPlayer()});
		
		super.setProperty(new String[] {
				"While revealed, you cannot choosen by the Ducking Stool",
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