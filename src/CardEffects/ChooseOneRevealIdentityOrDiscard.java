package CardEffects;

import gamebody.Charactor;

public class ChooseOneRevealIdentityOrDiscard extends CardEffect{
	public ChooseOneRevealIdentityOrDiscard() {};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}
}
