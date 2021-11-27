package Cartes;

import CardEffects.*;


public class AngryMob extends RumourCard{

	
	public AngryMob() {
		super("AngryMob", 
				new CardEffect[] {new TakeNextTurn()}, 
				new CardEffect[] {new RevealAnotherIdentity()});

	}
}
