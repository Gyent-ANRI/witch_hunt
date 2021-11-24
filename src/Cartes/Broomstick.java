package Cartes;

import CardEffects.CardEffect;
import CardEffects.ChooseNextPlayer;
import CardEffects.TakeNextTurn;

public class Broomstick extends RumourCard{
	public Broomstick() {
		super("Broomstick",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new ChooseNextPlayer()});
	}
}
