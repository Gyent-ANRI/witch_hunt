package CardEffects;

import Players.Charactor;

public class TakeRandomCardFromOther extends CardEffect{
	public TakeRandomCardFromOther() {};
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}

}
