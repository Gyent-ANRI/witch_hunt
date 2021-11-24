package Cartes;

import CardEffects.CardEffect;
import CardEffects.ChooseNextPlayer;
import CardEffects.TakeNextTurn;
import CardEffects.TakeOwnRevealedCard;

public class PointedHat extends RumourCard{
	
	public PointedHat() {
		super("PointedHat",
				new CardEffect[] {new TakeOwnRevealedCard(),new TakeNextTurn()},
				new CardEffect[] {new TakeOwnRevealedCard(),new ChooseNextPlayer()}
				);
	}
}
