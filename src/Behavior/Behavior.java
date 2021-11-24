package Behavior;

import gamebody.Charactor;

public abstract class Behavior {
	
	private Charactor actor;
	
	public Behavior() {};
	public Behavior(Charactor a) {actor = a;}
	
	public Charactor getActor() {return actor;}
	public void behave() {};
}