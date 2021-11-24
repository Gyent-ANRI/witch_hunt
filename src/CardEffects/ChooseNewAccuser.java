package CardEffects;

import gamebody.Charactor;

public class ChooseNewAccuser extends CardEffect{
	public ChooseNewAccuser(){};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}
}
