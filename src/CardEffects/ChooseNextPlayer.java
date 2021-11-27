package CardEffects;

import java.util.Iterator;
import java.util.LinkedList;

import Players.Charactor;
import gamebody.GameController;
import gamebody.RoundController;

public class ChooseNextPlayer extends CardEffect {
	
	public ChooseNextPlayer() {}
	public void effective(Charactor actor) {
		//get name list
		LinkedList<Charactor> oldlist = RoundController.getObject().getCharactorList();
		LinkedList<Charactor> myplayers = new LinkedList<Charactor>();
		Iterator<Charactor> it = oldlist.iterator();
		while(it.hasNext()) {
			myplayers.add(it.next());
		}
		myplayers.remove(actor);
		
		String[] nameOfPlayers = new String[myplayers.size()];
		for(int i = 1; i <= myplayers.size(); i++) {
			nameOfPlayers[i-1] = myplayers.get(i-1).getName(); 
		}
		
		actor.getInteractionWindow().outPut("Please choose next player ");
		int answer = actor.getInteractionWindow().makeChoice(nameOfPlayers);
		
		RoundController.getObject().startPlay(myplayers.get(answer-1));
	}
	
}
