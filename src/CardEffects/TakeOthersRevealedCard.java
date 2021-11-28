package CardEffects;

import Behavior.Behavior;

public class TakeOthersRevealedCard extends CardEffect{
	public TakeOthersRevealedCard() {};
	
	public void effective(Behavior behavior) {
		CardEffect t = new TakeNextTurn();
		t.effective(behavior);
	}
}
