package cardEffects;

import java.util.LinkedList;

import behavior.Behavior;
import gamebody.Identity;
import gamebody.RoundController;
import players.Charactor;

public class SecretlyLookIdentity extends CardEffect{
	public SecretlyLookIdentity() {};
	
	public void effective(Behavior behavior) {
		//choose a player
		LinkedList<Charactor> myplayers = RoundController.getObject().getCharactorList();
		myplayers.remove(behavior.getActor());
		
		String[] nameOfPlayers = new String[myplayers.size()];
		for(int i = 1; i <= myplayers.size(); i++) {
			nameOfPlayers[i-1] = myplayers.get(i-1).getName(); 
		}
		
		behavior.getActor().getInteractionWindow().outPut("Please choose next player ");
		int answer = behavior.getActor().getInteractionWindow().makeChoice(nameOfPlayers);
		
		//get the name and the identity of choosen one
		String name  = nameOfPlayers[answer - 1];
		Identity id = myplayers.get(answer-1).getIdentity();
		
		behavior.getActor().getInteractionWindow().outPut(name + "'s identity is " + id.toString());
		
		RoundController.getObject().setNext(myplayers.get(answer-1));
	}
}
