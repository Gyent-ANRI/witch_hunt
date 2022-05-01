package behavior;

import gamebody.Identity;
import gamebody.RoundController;
import players.Charactor;

/**
 * Classes describing the behavior£º reveal identity
 * @author User
 *
 */
public class RevealIdentity extends Behavior{
	
	private Charactor reasonOne;
	
	public RevealIdentity(Charactor re, Charactor actor) {super(actor); reasonOne = re;}
	
	public void behave() {
		Identity identity = super.getActor().revealIdentity();
		switch(identity) {
		case witch:
			RoundController.getObject().outOfRound(super.getActor());
			reasonOne.modifyScore(1);
			RoundController.getObject().setNext(reasonOne);
			break;
		case villager:
			RoundController.getObject().setNext(super.getActor());
		}
	}
}
