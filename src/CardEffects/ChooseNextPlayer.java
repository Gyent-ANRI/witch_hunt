package CardEffects;

import gamebody.Charactor;
import gamebody.GameController;
import gamebody.RoundController;

public class ChooseNextPlayer extends CardEffect {
	
	public ChooseNextPlayer() {}
	public void effective(Charactor actor) {
		//get name list
		Charactor[] myplayers = GameController.getObject().getCharactorList();
		String[] nameOfPlayers = new String[myplayers.length];
		for(int i = 1; i <= myplayers.length; i++) {
			nameOfPlayers[i-1] = myplayers[i-1].getName(); 
		}
		
		actor.getInteractionWindow().outPut("Please choose next player ");
		int answer = actor.getInteractionWindow().makeChoice(nameOfPlayers);
		
		RoundController.getObject().startPlay(myplayers[answer - 1]);
	}
	
}
