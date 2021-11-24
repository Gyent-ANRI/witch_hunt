package Cartes;

import CardEffects.CardEffect;
import CardEffects.TakeCardFromAccuse;
import CardEffects.TakeNextTurn;
import CardEffects.TakeRandomCardFromOther;

public class HookedNose extends RumourCard{
	public HookedNose() {
		super("HookedNose",
				new CardEffect[] {new TakeCardFromAccuse(),new TakeNextTurn()},
				new CardEffect[] {new TakeRandomCardFromOther()}
				);
	}
}
