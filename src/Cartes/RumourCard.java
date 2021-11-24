package Cartes;

import CardEffects.CardEffect;
import gamebody.BroadCast;
import gamebody.Charactor;

public abstract class RumourCard {

	private String name;
	private Charactor owner;
	private CardEffect[] witchEffect;
	private CardEffect[] huntEffect;
	
	public RumourCard() {};
	public RumourCard(String n, CardEffect[] witch, CardEffect[] hunt) {
		name = n;
		witchEffect = witch;
		huntEffect = hunt;
	}
	
	public void witch() {
		BroadCast.getObject().broad("The player " + getOwner().getName() + " has used witch of " + getName());
		for(int i = 1; i <= getWitchEffect().length; i++) {
			getWitchEffect()[i-1].effective(getOwner());
		}
	}
	
	public void hunt() {
		BroadCast.getObject().broad("The player " + getOwner().getName() + " has used hunt of " + getName());
		for(int i = 1; i <= getHuntEffect().length; i++) {
			getHuntEffect()[i-1].effective(getOwner());
		}
	}
	
	public CardEffect[] getWitchEffect() {return witchEffect;}
	public CardEffect[] getHuntEffect() {return huntEffect;}
	public void setOwner(Charactor myOwner) {owner = myOwner;}
	public String getName() {return name;}
	public Charactor getOwner() {return owner;}
}
