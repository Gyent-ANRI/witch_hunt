package Behavior;

import java.util.Iterator;
import java.util.LinkedList;

import gamebody.Charactor;
import gamebody.GameController;
import gamebody.RoundController;

public class Accuse extends Behavior{
	
	
	public Accuse(Charactor actor) {super(actor);}
	
	public void behave() {
		
		//get a copy of name list
		LinkedList<Charactor> oldlist = RoundController.getObject().getCharactorList();
		LinkedList<Charactor> myplayers = new LinkedList<Charactor>();
		Iterator<Charactor> it = oldlist.iterator();
		while(it.hasNext()) {
			myplayers.add(it.next());
		}
		myplayers.remove(super.getActor());
		
		String[] nameOfPlayers = new String[myplayers.size()];
		for(int i = 1; i <= myplayers.size(); i++) {
			nameOfPlayers[i-1] = myplayers.get(i-1).getName(); 
		}
		
		//ask actor 
		super.getActor().getInteractionWindow().outPut("please chose the one you want to accuse");
		int answer = super.getActor().getInteractionWindow().makeChoice(nameOfPlayers);
		myplayers.get(answer-1).accused(super.getActor());
	}

}
