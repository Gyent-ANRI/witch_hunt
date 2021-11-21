package Behavior;

import gamebody.Charactor;
import gamebody.GameController;
import gamebody.RoundController;

public class Accuse extends Behavior{
	
	
	public Accuse(Charactor actor) {super(actor);}
	
	public void behave() {
		
		//get name list
		Charactor[] myplayers = RoundController.getObject().getCharactorList();
		String[] nameOfPlayers = new String[myplayers.length];
		for(int i = 1; i <= myplayers.length; i++) {
			nameOfPlayers[i-1] = myplayers[i-1].getName(); 
		}
		
		//ask actor 
		super.getActor().getInteractionWindow().outPut("please chose the one you want to accuse");
		int answer = super.getActor().getInteractionWindow().makeChoice(nameOfPlayers);
		myplayers[answer - 1].accused(super.getActor());
	}

}
