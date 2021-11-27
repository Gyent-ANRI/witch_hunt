package CardEffects;

import java.util.Iterator;
import java.util.LinkedList;

import Players.Charactor;
import gamebody.*;

public class RevealAnotherIdentity extends CardEffect{
	
	public RevealAnotherIdentity() {}
	
	public void effective(Charactor actor) {
		//get a copy of name list
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
		
		actor.getInteractionWindow().outPut("Reveal who's identity?");
		int answer=actor.getInteractionWindow().makeChoice(nameOfPlayers);
		
		Identity id = myplayers.get(answer-1).revealIdentity();
		
		switch(id) {
		case witch:
			actor.modifyScore(2);
			RoundController.getObject().startPlay(actor);
			break;
		case villager:
			actor.modifyScore(-2);
			RoundController.getObject().startPlay(myplayers.get(answer-1));
		}
	} 
}
