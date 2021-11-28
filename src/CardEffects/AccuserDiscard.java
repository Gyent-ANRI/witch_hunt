package CardEffects;

import Behavior.Behavior;

public class AccuserDiscard extends CardEffect{
	public AccuserDiscard() {};
	
	public void effective(Behavior behavior) {
		CardEffect t = new TakeNextTurn();
		t.effective(behavior);
	}

}
