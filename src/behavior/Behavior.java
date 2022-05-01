package behavior;

import players.Charactor;

/**
 * Class abstract, The classes that describe the player's behavior all inherit from this class
 * @author Jiyang QI
 *
 */
public abstract class Behavior {
	
	private Charactor actor;
	
	public Behavior(Charactor a) {actor = a;}
	
	public Charactor getActor() {return actor;}
	public void behave() {};
}
