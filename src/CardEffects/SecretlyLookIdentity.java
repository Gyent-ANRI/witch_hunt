package CardEffects;

import Players.Charactor;

public class SecretlyLookIdentity extends CardEffect{
	public SecretlyLookIdentity() {};
	
	public void effective(Charactor owner) {
		CardEffect t = new TakeNextTurn();
		t.effective(owner);
	}
}
