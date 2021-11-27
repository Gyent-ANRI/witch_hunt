package Behavior;

import Players.Charactor;
import gamebody.Identity;
import gamebody.RoundController;

public class RevealIdentity extends Behavior{
	
	private Charactor reasonOne;
	
	public RevealIdentity(Charactor re, Charactor actor) {super(actor); reasonOne = re;}
	
	public void behave() {
		Identity identity = super.getActor().revealIdentity();
		switch(identity) {
		case witch:
			RoundController.getObject().outOfRound(super.getActor());
			reasonOne.modifyScore(1);
			RoundController.getObject().startPlay(reasonOne);
			break;
		case villager:
			RoundController.getObject().startPlay(super.getActor());
		}
	}
}
