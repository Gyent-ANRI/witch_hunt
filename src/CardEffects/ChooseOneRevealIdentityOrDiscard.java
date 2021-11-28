package CardEffects;

import Behavior.Behavior;

public class ChooseOneRevealIdentityOrDiscard extends CardEffect{
	public ChooseOneRevealIdentityOrDiscard() {};
	
	public void effective(Behavior behavior) {
		CardEffect t = new TakeNextTurn();
		t.effective(behavior);
	}
}
