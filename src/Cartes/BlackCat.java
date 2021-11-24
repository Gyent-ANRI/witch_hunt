package Cartes;

import CardEffects.AddDiscardedCard;
import CardEffects.CardEffect;
import CardEffects.TakeNextTurn;

public class BlackCat extends RumourCard{
	public BlackCat() {
		super("BlackCat",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new AddDiscardedCard(), new TakeNextTurn()});
	}
}
