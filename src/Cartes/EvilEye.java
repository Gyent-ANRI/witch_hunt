package Cartes;

import CardEffects.CardEffect;
import CardEffects.ChooseNewAccuser;

public class EvilEye extends RumourCard{
	public EvilEye() {
		super("EvilEye",
				new CardEffect[] {new ChooseNewAccuser()},
				new CardEffect[] {new ChooseNewAccuser()});
	}
}
