package Cartes;

import CardEffects.CardEffect;
import CardEffects.RevealYourIdentity;
import CardEffects.TakeNextTurn;

public class Toad extends RumourCard{
	public Toad() {
		super("Toad",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new RevealYourIdentity()});
	}

}
