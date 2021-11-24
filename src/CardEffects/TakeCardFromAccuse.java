package CardEffects;

import gamebody.Charactor;

public class TakeCardFromAccuse extends CardEffect{
	public TakeCardFromAccuse() {};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}

}
