package CardEffects;

import java.util.LinkedList;

import Behavior.Behavior;
import Cartes.RumourCard;
import Players.Charactor;
import gamebody.BroadCast;
import gamebody.RoundController;

public class TakeRandomCardFromOther extends CardEffect{
	public TakeRandomCardFromOther() {};
	public void effective(Behavior behavior) {
		//get player list
		LinkedList<Charactor> myplayers = RoundController.getObject().getCharactorList();
		myplayers.remove(behavior.getActor());
		//get name list
		String[] nameOfPlayers = new String[myplayers.size()];
		for(int i = 1; i <= myplayers.size(); i++) {
			nameOfPlayers[i-1] = myplayers.get(i-1).getName(); 
		}
		//ask actor 
		behavior.getActor().getInteractionWindow().outPut("please chose next player and get random card from him.");
		int answer = behavior.getActor().getInteractionWindow().makeChoice(nameOfPlayers);
		BroadCast.getObject().broad(myplayers.get(answer-1).getName() + " is pointed by HookedNose");
		
		//check whether the choosen one has card
		RumourCard[] myCards = myplayers.get(answer-1).cardList();
		if(myCards.length == 0) {
			behavior.getActor().getInteractionWindow().outPut("The one you choose has no card");
		}
		else {
			int randomCode = (int)(Math.random()*(myCards.length-0.1));
			behavior.getActor().getCard(myCards[randomCode]);
			myCards[randomCode].setOwner(behavior.getActor());
			myplayers.get(answer-1).reduceCard(myCards[randomCode]);
		}
		RoundController.getObject().setNext(myplayers.get(answer-1));
	}

}
