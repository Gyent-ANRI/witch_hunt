package CardEffects;

import Behavior.Behavior;

public class RevealYourIdentity extends CardEffect{
	public RevealYourIdentity() {};
	
	public void effective(Behavior behavior) {
		CardEffect t = new TakeNextTurn();
		t.effective(behavior);
	}

}
