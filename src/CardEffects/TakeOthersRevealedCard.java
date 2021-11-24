package CardEffects;

import gamebody.Charactor;

public class TakeOthersRevealedCard extends CardEffect{
	public TakeOthersRevealedCard() {};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}
}
