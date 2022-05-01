package cardEffects;

import java.util.LinkedList;

import behavior.Behavior;
import gamebody.BroadCast;
import gamebody.RoundController;
import players.Charactor;

public class ChooseNewAccuser extends CardEffect{
	public ChooseNewAccuser(){};
	
	public void effective(Behavior behavior) {
		LinkedList<Charactor> myplayers = RoundController.getObject().getCharactorList();
		myplayers.remove(behavior.getActor());
		
		String[] nameOfPlayers = new String[myplayers.size()];
		for(int i = 1; i <= myplayers.size(); i++) {
			nameOfPlayers[i-1] = myplayers.get(i-1).getName(); 
		}
		
		behavior.getActor().getInteractionWindow().outPut("Please choose a accuser ");
		int answer = behavior.getActor().getInteractionWindow().makeChoice(nameOfPlayers);
		Charactor newAccuser = myplayers.get(answer-1);
		BroadCast.getObject().broad(newAccuser.getName() + " is choosen by Evil Eye");
		
		// get a list for new accuser
		myplayers.remove(newAccuser);
		if(myplayers.isEmpty()) {
			BroadCast.getObject().broad(newAccuser.getName() +  " can't choose anyone other than " + behavior.getActor().getName()
					+ ", " + newAccuser.getName() + " will just take his turn");
			newAccuser.takeTurn();
		}
		else {
			nameOfPlayers = new String[myplayers.size()];
			for(int i = 1; i <= myplayers.size(); i++) {
				nameOfPlayers[i-1] = myplayers.get(i-1).getName(); 
			}
			
			newAccuser.getInteractionWindow().outPut("please choose one to accuse");
			answer = newAccuser.getInteractionWindow().makeChoice(nameOfPlayers);
			BroadCast.getObject().broad(nameOfPlayers[answer-1] + " is accused");
			myplayers.get(answer-1).accused(newAccuser);
		}
		
	}
}
