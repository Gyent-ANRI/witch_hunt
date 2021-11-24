package CardEffects;

import gamebody.Charactor;

public class RevealYourIdentity extends CardEffect{
	public RevealYourIdentity() {};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}

}
