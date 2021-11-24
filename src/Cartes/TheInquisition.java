package Cartes;

import CardEffects.CardEffect;
import CardEffects.DiscardFromHand;
import CardEffects.SecretlyLookIdentity;
import CardEffects.TakeNextTurn;

public class TheInquisition extends RumourCard{
	public TheInquisition() {
		super("TheInquisition", 
				new CardEffect[] {new DiscardFromHand(),new TakeNextTurn()}, 
				new CardEffect[] {new SecretlyLookIdentity()});
	}
}
