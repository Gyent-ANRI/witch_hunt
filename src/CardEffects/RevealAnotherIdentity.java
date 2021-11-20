package CardEffects;

import gamebody.*;

public class RevealAnotherIdentity extends CardEffect{
	
	public RevealAnotherIdentity() {}
	
	public void effective(Charactor actor) {
		Charactor[] myplayers = GameController.getObject().getCharactorList();
		String[] nameOfPlayers = new String[myplayers.length];
		for(int i = 1; i <= myplayers.length; i++) {
			nameOfPlayers[i-1] = myplayers[i-1].getName(); 
		}
		
		actor.getInteractionWindow().outPut("Reveal who's identity?");
		int answer=actor.getInteractionWindow().makeChoice(nameOfPlayers);
		
		Identity id = myplayers[answer-1].revealIdentity();
		
		switch(id) {
		case witch:
			actor.modifyScore(2);
			RoundController.getObject().startPlay(actor);
			break;
		case villager:
			actor.modifyScore(-2);
			RoundController.getObject().startPlay(myplayers[answer-1]);
		}
	} 
}
