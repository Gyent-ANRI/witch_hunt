package cards;

import behavior.Behavior;
import cardEffects.CardEffect;
import cardEffects.ChooseNewAccuser;
import gamebody.RevealedCardArea;

public class EvilEye extends RumourCard{
	public EvilEye() {
		super("EvilEye",
				new CardEffect[] {new ChooseNewAccuser()},
				new CardEffect[] {new ChooseNewAccuser()});
		
		super.setProperty(new String[] {
				"No property",
				"Choose next player, on their turn they must accuse a player other than you, if possible",
				"Choose next player, on their turn they must accuse a player other than you, if possible"
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
