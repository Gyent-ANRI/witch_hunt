package CardEffects;

import gamebody.Charactor;

public class AccuserDiscard extends CardEffect{
	public AccuserDiscard() {};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}

}