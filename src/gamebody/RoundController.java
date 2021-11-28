package gamebody;

import java.util.Iterator;
import java.util.LinkedList;

import Cartes.*;
import Players.Charactor;

public class RoundController {
	
	private LinkedList<Charactor> listPlayers;
	static RoundController myObject = null;
	
	private RoundController() {
	}
	
	static public RoundController newObject(LinkedList<Charactor> list) {
		
		myObject = new RoundController();
		myObject.listPlayers = new LinkedList<Charactor>();
		Iterator<Charactor> it = list.iterator();
		while(it.hasNext()) {
			myObject.listPlayers.add(it.next());
		}

		return myObject;
	}
	
	static public RoundController getObject() {
		return myObject;
	}
	
	//now just give everyone TestCards
	public void distributeCard() {
		//decide number of Card for each player
		int numplayer = listPlayers.size();
		int cardNumber = 0;
		switch(numplayer) {
		case 3:
			cardNumber = 4;
			break;
		case 4:
			cardNumber = 3;
			break;
		case 5:
			cardNumber = 2;
			break;
		case 6:
			cardNumber = 2;
		}
		
		//Get a card array
		RumourCard[] myCardList = new RumourCard[] {
				new AngryMob(),
				new BlackCat(),
				new Broomstick(),
				new Cauldron(),
				new DuckingStool(),
				new EvilEye(),
				new HookedNose(),
				new PetNewt(),
				new PointedHat(),
				new TheInquisition(),
				new Toad(),
				new Wart(),
		};
		//get a random double array
		double[] poid = new double[] {Math.random(),Math.random(),Math.random(),Math.random(),
				Math.random(),Math.random(),Math.random(),Math.random(),
				Math.random(),Math.random(),Math.random(),Math.random()};
		
		//sort the double array and the card
		boolean loop = true;
		while(loop) {
			int numChange = 0;
			for(int i = 1; i <= 11; i++) {
				if(poid[i] < poid[i-1]) {
					double temp = poid[i];
					poid[i] = poid[i-1];
					poid[i-1] = temp;
					
					RumourCard tempCard = myCardList[i];
					myCardList[i] = myCardList[i-1];
					myCardList[i-1] = tempCard;
					
					numChange += 1;
				}
			}
			if(numChange == 0) {
				loop = false;
			}
		}
		
		// distribute the j-th cards of the i-th player
		for(int i = 1; i <= numplayer; i++) {
			for(int j = 1; j <= cardNumber; j++) {
				listPlayers.get(i-1).getCard(myCardList[(i-1)*cardNumber+j-1]);
				myCardList[(i-1)*cardNumber+j-1].setOwner(listPlayers.get(i-1));
			}
		}
	}
	
	public Charactor decideFirstPlayer() {
		int num = (int)(Math.random()*(listPlayers.size() - 0.1));
		
		return listPlayers.get(num);
	}
	
	public void chooseIdentity() {
		for(int i = 1; i <= listPlayers.size(); i++) {
			listPlayers.get(i-1).getInteractionWindow().outPut("Choose Your Identity");
			
			int answer = listPlayers.get(i-1).getInteractionWindow().makeChoice(new String[] {"Witch", "Villager"});
			switch(answer) {
			case 1:
				listPlayers.get(i-1).setIdentity(Identity.witch);
				break;
			case 2:
				listPlayers.get(i-1).setIdentity(Identity.villager);
				break;
			}
		}
	}
	
	public void startPlay(Charactor player) {
		
		//if there is only one player with identity no revealed, we call roundOver().
		int numOfSurvivor = 0;
		Charactor survivor = null;
		for(int i = 1; i <= listPlayers.size(); i++) {
			if(!listPlayers.get(i-1).identityRevealed()) {
				numOfSurvivor += 1;
				survivor = listPlayers.get(i-1);
			}
		}
		if(numOfSurvivor == 1) {
			GameController.getObject().roundOver(survivor);
		}
		
		
		BroadCast.getObject().broad(player.getName() + "'s turn");
		player.takeTurn();
	}
	
	public void outOfRound(Charactor outer) {
		listPlayers.remove(outer);
		BroadCast.getObject().broad(outer.getName() +" is out of round");
	}
	
	//return a copy of listPlayers and make sure that only outOfRound() is able to change listPlayers
	public LinkedList<Charactor> getCharactorList() {
		LinkedList<Charactor> copy = new LinkedList<Charactor>();
		copy.addAll(listPlayers);
		return copy;
	}
}
