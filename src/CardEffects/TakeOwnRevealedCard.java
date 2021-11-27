package CardEffects;

import Players.Charactor;

public class TakeOwnRevealedCard extends CardEffect{
	public TakeOwnRevealedCard() {};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}
}
