package CardEffects;

import java.util.LinkedList;

import Behavior.Behavior;
import Players.Charactor;
import gamebody.RoundController;

public class ChooseNextPlayer extends CardEffect {
	
	public ChooseNextPlayer() {}
	public void effective(Behavior behavior) {
		//get name list
		LinkedList<Charactor> myplayers = RoundController.getObject().getCharactorList();
		myplayers.remove(behavior.getActor());
		
		String[] nameOfPlayers = new String[myplayers.size()];
		for(int i = 1; i <= myplayers.size(); i++) {
			nameOfPlayers[i-1] = myplayers.get(i-1).getName(); 
		}
		
		behavior.getActor().getInteractionWindow().outPut("Please choose next player ");
		int answer = behavior.getActor().getInteractionWindow().makeChoice(nameOfPlayers);
		
		RoundController.getObject().startPlay(myplayers.get(answer-1));
	}
	
}
