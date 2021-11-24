package Cartes;

import CardEffects.CardEffect;
import CardEffects.ChooseNextPlayer;
import CardEffects.TakeNextTurn;
import CardEffects.TakeOthersRevealedCard;

public class PetNewt extends RumourCard{
	public PetNewt() {
		super("PetNewt",
				new CardEffect[] {new TakeNextTurn()},
				new CardEffect[] {new TakeOthersRevealedCard(), new ChooseNextPlayer()});
	}

}
