package Cartes;

import CardEffects.CardEffect;
import CardEffects.ChooseNextPlayer;
import CardEffects.TakeNextTurn;

public class Wart extends RumourCard{
	public Wart() {
		super("Wart",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new ChooseNextPlayer()});
	}
}