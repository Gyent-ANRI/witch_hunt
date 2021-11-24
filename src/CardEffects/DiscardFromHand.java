package CardEffects;

import gamebody.Charactor;

public class DiscardFromHand extends CardEffect{
	public DiscardFromHand() {};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}
}
