package CardEffects;

import java.util.Iterator;
import java.util.LinkedList;

import Behavior.Behavior;
import Cartes.RumourCard;
import Cartes.Wart;
import Players.Charactor;
import gamebody.*;

public class ChooseOneRevealIdentityOrDiscard extends CardEffect{
	public ChooseOneRevealIdentityOrDiscard() {};
	
	public void effective(Behavior behavior) {
		//get players list
		LinkedList<Charactor> myplayers = RoundController.getObject().getCharactorList();
		myplayers.remove(behavior.getActor());
		
		//check if there is wart revealed
		LinkedList<RumourCard> cardlist = RevealedCardArea.getObject().getCard();
		Iterator<RumourCard> it2 = cardlist.iterator();
		while(it2.hasNext()) {
			RumourCard card = it2.next();
			if(card instanceof Wart) {
				myplayers.remove(card.getOwner());
			}
		}
		//get name list
		String[] nameOfPlayers = new String[myplayers.size()];
		for(int i = 1; i <= myplayers.size(); i++) {
			nameOfPlayers[i-1] = myplayers.get(i-1).getName(); 
		}
		//ask actor
		behavior.getActor().getInteractionWindow().outPut("Please choose one to hunt: ");
		int answer = behavior.getActor().getInteractionWindow().makeChoice(nameOfPlayers);
		Charactor choosenOne = myplayers.get(answer-1);
		
		//ask choosen one what to do
		choosenOne.getInteractionWindow().outPut("You are pointed by Ducking Stool, "
				+ "choose to reveal identity or discard");
		int choice = choosenOne.getInteractionWindow().makeChoice(new String[] {"Reveal identity", "Discard"});
		switch(choice) {
		case 1:
			//reveal identity
			
			Identity id = choosenOne.revealIdentity();
			switch(id) {
			case witch:
				behavior.getActor().modifyScore(1);
				behavior.getActor().takeTurn();
				break;
			case villager:
				behavior.getActor().modifyScore(-1);
				choosenOne.takeTurn();
			}
			break;
		case 2:
			//discard
			
			RumourCard[] myCards = choosenOne.cardList();
			//get name list
			String[] nameOfCards = new String[myCards.length];
			for(int i = 1; i <= myCards.length; i++) {
				nameOfCards[i-1] = myCards[i-1].getName(); 
			}
					
			//ask actor 
			choosenOne.getInteractionWindow().outPut("Choose a card to discard");
			int discard = behavior.getActor().getInteractionWindow().makeChoice(nameOfCards);
			choosenOne.reduceCard(myCards[discard-1]);
			DisCardArea.getObject().addCard(myCards[discard-1]);
			choosenOne.takeTurn();
		}
	}
}
