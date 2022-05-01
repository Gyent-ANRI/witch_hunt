package cards;

import behavior.Behavior;
import cardEffects.AddDiscardedCard;
import cardEffects.CardEffect;
import cardEffects.TakeNextTurn;
import gamebody.DisCardArea;
import gamebody.RevealedCardArea;

public class BlackCat extends RumourCard{
	public BlackCat() {
		super("BlackCat",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new AddDiscardedCard(), new TakeNextTurn()});
		
		super.setProperty(new String[] {
				"No property",
				"Take next turn",
				"Add one discarded card to your hand, and then discard this card | Take next turn"
		});
	}
	
	public void witch(Behavior behavior) {
		behavior.getActor().reduceCard(this);
		RevealedCardArea.getObject().addCard(this);
		super.witch(behavior);
	}
	
	public void hunt(Behavior behavior) {
		if(DisCardArea.getObject().getCard().isEmpty()) {
			behavior.getActor().reduceCard(this);
			DisCardArea.getObject().addCard(this);
		}
		else {
			super.hunt(behavior);
		}
	}
}
