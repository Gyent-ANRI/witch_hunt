package CardEffects;

import Players.Charactor;

public class AddDiscardedCard extends CardEffect{
	public AddDiscardedCard() {};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}

}
