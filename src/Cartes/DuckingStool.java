package Cartes;

import CardEffects.CardEffect;
import CardEffects.ChooseNextPlayer;
import CardEffects.ChooseOneRevealIdentityOrDiscard;

public class DuckingStool extends RumourCard{
	public DuckingStool() {
		super("DuckingStool",
				new CardEffect[] {new ChooseNextPlayer()},
				new CardEffect[] {new ChooseOneRevealIdentityOrDiscard()});
	}
}
