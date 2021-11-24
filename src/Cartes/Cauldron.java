package Cartes;

import CardEffects.AccuserDiscard;
import CardEffects.CardEffect;
import CardEffects.RevealYourIdentity;
import CardEffects.TakeNextTurn;

public class Cauldron extends RumourCard{
	public Cauldron() {
		super("Cauldron",
				new CardEffect[] {new AccuserDiscard(), new TakeNextTurn()},
				new CardEffect[] {new RevealYourIdentity()});
	}
}
