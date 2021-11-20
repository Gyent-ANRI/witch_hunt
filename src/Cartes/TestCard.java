package Cartes;

import CardEffects.CardEffect;
import CardEffects.ChooseNextPlayer;
import gamebody.BroadCast;

public class TestCard extends RumourCard{
	
	
	public TestCard() {
		super("TestCard", new CardEffect[] {new ChooseNextPlayer()}, new CardEffect[] {new ChooseNextPlayer()});
		
	}
	/*
	public void witch() {
		BroadCast.getObject().broad("The player " + super.getOwner().getName() + " has used witch of " + super.getName());
		for(int i = 1; i <= super.getWitchEffect().length; i++) {
			super.getWitchEffect()[i-1].effective(super.getOwner());
		}
	}
	
	public void hunt() {
		BroadCast.getObject().broad("The player " + super.getOwner().getName() + " has used hunt of " + super.getName());
		for(int i = 1; i <= super.getHuntEffect().length; i++) {
			super.getHuntEffect()[i-1].effective(super.getOwner());
		}
	}
	*/
}
