package CardEffects;

import Behavior.Behavior;

public class ChooseNewAccuser extends CardEffect{
	public ChooseNewAccuser(){};
	
	public void effective(Behavior behavior) {
		CardEffect t = new TakeNextTurn();
		t.effective(behavior);
	}
}
