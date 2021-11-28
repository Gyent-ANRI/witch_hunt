package CardEffects;

import Behavior.Behavior;

public class AddDiscardedCard extends CardEffect{
	public AddDiscardedCard() {};
	
	public void effective(Behavior behavior) {
		CardEffect t = new TakeNextTurn();
		t.effective(behavior);
	}

}
