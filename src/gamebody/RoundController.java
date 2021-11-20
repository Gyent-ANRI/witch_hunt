package gamebody;

import Cartes.*;

public class RoundController {
	
	private Charactor[] listPlayers;
	private int numRound;
	static RoundController myObject = null;
	
	private RoundController() {
	}
	
	static public RoundController newObject(int num, Charactor[] list) {
		
		myObject = new RoundController();
		myObject.numRound = num;
		myObject.listPlayers = list;

		return myObject;
	}
	
	static public RoundController getObject() {
		return myObject;
	}
	
	//now just give everyone TestCards
	public void distributeCard() {
		int num = listPlayers.length;
		int cardNumber = 2;
		switch(num) {
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
		for(int i = 1; i < num; i++) {
			for(int j = 1; j <= cardNumber; j++) {
				RumourCard newCard = new AngryMob();
				listPlayers[i-1].getCard(newCard);
				newCard.setOwner(listPlayers[i-1]);
			}
		}
	}
	
	public Charactor decideFirstPlayer() {
		return listPlayers[0];
	}
	
	public void chooseIdentity() {
		for(int i = 1; i <= listPlayers.length; i++) {
			listPlayers[i-1].getInteractionWindow().outPut("Choose Your Identity");
			
			int answer = listPlayers[i-1].getInteractionWindow().makeChoice(new String[] {"Witch", "Villager"});
			switch(answer) {
			case 1:
				listPlayers[i-1].setIdentity(Identity.witch);
				break;
			case 2:
				listPlayers[i-1].setIdentity(Identity.villager);
				break;
			}
		}
	}
	
	public void startPlay(Charactor player) {
		BroadCast.getObject().broad(player.getName() + "'s turn");
		player.takeTurn();
	}
}
