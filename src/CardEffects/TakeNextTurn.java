package CardEffects;

import Behavior.Behavior;
import gamebody.RoundController;

public class TakeNextTurn extends CardEffect{
	public TakeNextTurn() {}
	public void effective(Behavior behavior) {
		RoundController.getObject().setNext(behavior.getActor());
	}
}
