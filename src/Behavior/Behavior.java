package Behavior;

import Players.Charactor;

public abstract class Behavior {
	
	private Charactor actor;
	
	public Behavior(Charactor a) {actor = a;}
	
	public Charactor getActor() {return actor;}
	public void behave() {};
}
