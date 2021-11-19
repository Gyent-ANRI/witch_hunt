package Behavior;

import gamebody.Charactor;
import gamebody.Identity;
import gamebody.RoundController;

public class RevealIdentity extends Behavior{
	
	private Charactor reasonOne;
	
	public RevealIdentity(Charactor re, Charactor actor) {super(actor); reasonOne = re;}
	
	public void behave() {
		Identity identity = super.getActor().revealIdentity();
		switch(identity) {
		case witch:
			super.getActor().outOfRound();
			RoundController.getObject().startPlay(reasonOne);
			break;
		case villager:
			RoundController.getObject().startPlay(super.getActor());
		}
	}
}
