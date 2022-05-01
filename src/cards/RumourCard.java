package cards;

import behavior.Behavior;
import cardEffects.CardEffect;
import gamebody.BroadCast;
import players.Charactor;

/**
 * All classes that describe a rumour card inherit from this class.
 * @author Jiyang QI & CHAKER Abdessamad
 *
 */
public abstract class RumourCard {

	private String name;
	private Charactor owner;
	private CardEffect[] witchEffect;
	private CardEffect[] huntEffect;
	/**
	 * Attribute used to store the description of the property of the card
	 */
	private String[] property;
	
	public RumourCard() {};
	public RumourCard(String n, CardEffect[] witch, CardEffect[] hunt) {
		name = n;
		witchEffect = witch;
		huntEffect = hunt;
	}
	
	public void setProperty(String[] pro) {
		property = pro;
	}
	
	public String[] getProperty() {
		return property;
	}
	
	/**
	 * use all my witch effect in order
	 * @param behavior
	 */
	public void witch(Behavior behavior) {
		BroadCast.getObject().broad("The player " + getOwner().getName() + " has used witch of " + getName());
		for(int i = 1; i <= getWitchEffect().length; i++) {
			getWitchEffect()[i-1].effective(behavior);
		}
	}
	
	/**
	 * use all my hunt effect in order
	 * @param behavior
	 */
	public void hunt(Behavior behavior) {
		BroadCast.getObject().broad("The player " + getOwner().getName() + " has used hunt of " + getName());
		for(int i = 1; i <= getHuntEffect().length; i++) {
			getHuntEffect()[i-1].effective(behavior);
		}
	}
	
	public CardEffect[] getWitchEffect() {return witchEffect;}
	public CardEffect[] getHuntEffect() {return huntEffect;}
	public void setOwner(Charactor myOwner) {owner = myOwner;}
	public String getName() {return name;}
	public Charactor getOwner() {return owner;}
}

