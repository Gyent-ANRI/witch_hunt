package CardEffects;

import Players.Charactor;
import gamebody.GameController;
import gamebody.RoundController;

public class TakeNextTurn extends CardEffect{
	public TakeNextTurn() {}
	public void effective(Charactor actor) {
		RoundController.getObject().startPlay(actor);
	}
}
