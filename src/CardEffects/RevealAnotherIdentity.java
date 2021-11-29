package CardEffects;

import java.util.Iterator;
import java.util.LinkedList;

import Cartes.Broomstick;
import Cartes.RumourCard;
import Players.Charactor;
import gamebody.*;

public class RevealAnotherIdentity extends CardEffect{
	
	public RevealAnotherIdentity() {}
	
	public void effective(Charactor actor) {
		//get list of players
		LinkedList<Charactor> myplayers = RoundController.getObject().getCharactorList();
		myplayers.remove(actor);
		
		//remove those who's identity is revealed.
		Iterator<Charactor> it = myplayers.iterator();
		while(it.hasNext()) {
			Charactor c = it.next();
			if(c.identityRevealed()) {
				myplayers.remove(c);
			}
		}
		
		//check whether someone has revealed Broomstick
		LinkedList<RumourCard> cardlist = RevealedCardArea.getObject().getCard();
		Iterator<RumourCard> it2 = cardlist.iterator();
		while(it2.hasNext()) {
			RumourCard card = it2.next();
			if(card instanceof Broomstick) {
				myplayers.remove(card.getOwner());
			}
		}
		
		
		//get name list.
		String[] nameOfPlayers = new String[myplayers.size()];
		for(int i = 1; i <= myplayers.size(); i++) {
			nameOfPlayers[i-1] = myplayers.get(i-1).getName(); 
		}
		
		//ask actor
		actor.getInteractionWindow().outPut("Reveal who's identity?");
		int answer=actor.getInteractionWindow().makeChoice(nameOfPlayers);
		
		Identity id = myplayers.get(answer-1).revealIdentity();
		
		switch(id) {
		case witch:
			actor.modifyScore(2);
			RoundController.getObject().setNext(actor);
			break;
		case villager:
			actor.modifyScore(-2);
			RoundController.getObject().setNext(myplayers.get(answer-1));
		}
	} 
}
