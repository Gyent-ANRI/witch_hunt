package cardEffects;

import behavior.Behavior;

/**
 * All classes that describe card effects inherit from this class.
 * @author Jiyang QI & CHAKER Abdessamad
 *
 */
public abstract class CardEffect {
	
	public CardEffect() {}
	
	public void effective(Behavior behavior) {};
}
