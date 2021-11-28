package CardEffects;

import java.util.LinkedList;

import Behavior.Behavior;
import Players.Charactor;
import gamebody.Identity;
import gamebody.RoundController;

public class RevealYourIdentity extends CardEffect{
	public RevealYourIdentity() {};
	
	public void effective(Behavior behavior) {
		Identity id = behavior.getActor().revealIdentity();
		
		switch(id) {
		
		case witch:
			LinkedList<Charactor> playerList = RoundController.getObject().getCharactorList();
			int index = playerList.indexOf(behavior.getActor());
			if(index == 0) {
				playerList.getLast().takeTurn();
			}
			else {
				playerList.get(index-1).takeTurn();
			}
			break;
			
		case villager:
			CardEffect chooseNext = new ChooseNextPlayer();
			chooseNext.effective(behavior);
		}
	}

}
