package CardEffects;

import gamebody.Charactor;

public class TakeOwnRevealedCard extends CardEffect{
	public TakeOwnRevealedCard() {};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}
}
