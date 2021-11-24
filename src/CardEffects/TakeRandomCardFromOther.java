package CardEffects;

import gamebody.Charactor;

public class TakeRandomCardFromOther extends CardEffect{
	public TakeRandomCardFromOther() {};
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}

}
