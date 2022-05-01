package behavior;

import java.util.Iterator;
import java.util.LinkedList;

import gamebody.BroadCast;
import gamebody.RoundController;
import players.Charactor;

/**
 * Classes describing the behavior£º accuse
 * @author Jiyang QI
 *
 */
public class Accuse extends Behavior{
	
	
	public Accuse(Charactor actor) {super(actor);}
	
	public void behave() {
		
		LinkedList<Charactor> oldlist = RoundController.getObject().getCharactorList();
		LinkedList<Charactor> myplayers = new LinkedList<Charactor>();
		Iterator<Charactor> it = oldlist.iterator();
		while(it.hasNext()) {
			//reduce those who's identity has been revealed
			Charactor c = it.next();
			if(c.identityRevealed() == false)
				myplayers.add(c);
		}
		myplayers.remove(super.getActor());
		
		String[] nameOfPlayers = new String[myplayers.size()];
		for(int i = 1; i <= myplayers.size(); i++) {
			nameOfPlayers[i-1] = myplayers.get(i-1).getName(); 
		}
		
		//ask actor 
		super.getActor().getInteractionWindow().outPut("please chose the one you want to accuse");
		int answer = super.getActor().getInteractionWindow().makeChoice(nameOfPlayers);
		BroadCast.getObject().broad(myplayers.get(answer-1).getName() + " is accused");
		myplayers.get(answer-1).accused(super.getActor());
	}

}
