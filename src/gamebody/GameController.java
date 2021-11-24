package gamebody;

import java.util.LinkedList;

public class GameController {
	
	//variable
	private int numRound;
	private LinkedList<Charactor> myPlayers;
	private RoundController myRoundController;
	static private GameController myObject = null;
	
	//methods
	private GameController() {
		numRound = 0;
		myPlayers = new LinkedList<Charactor>();
	}
	
	static public GameController getObject() {
		if( myObject == null ) 
			myObject = new GameController();
		return myObject;
	}
	
	public void gameStart() {
		
		System.out.println("The game is started");
		
		//Create a game lancer or player1
		Charactor lancer = new Charactor();
		int numPlayer = 0;
		
		//Ask lancer name and number of player
		String name = lancer.getInteractionWindow().askAndWait("What's your name? ");
		lancer.setName(name);
		while(!(numPlayer <= 6 && numPlayer >= 3)) {
			String num = lancer.getInteractionWindow().askAndWait("Number of player?(3-6) ");
			numPlayer = Integer.valueOf(num).intValue();
			if(!(numPlayer <= 6 && numPlayer >= 3))
				lancer.getInteractionWindow().outPut("Wrong Input!");
		}
		myPlayers.add(lancer);

		
		//create players and set name
		for(int i = 1; i < numPlayer; i++) {
			myPlayers.add(new Charactor());
			String playerName = myPlayers.get(i).getInteractionWindow().askAndWait("What's your name? ");
			myPlayers.get(i).setName(playerName);
		}
		
		roundStart();
	}
	
	public void roundStart() {
		numRound += 1;
		BroadCast.getObject().broad("Round " + numRound + " started");
		//reset the cards and the status of the players
		for(int i = 1; i <= myPlayers.size(); i++) {
			myPlayers.get(i-1).reset();
		}
		myRoundController = RoundController.newObject(numRound, myPlayers);
		myRoundController.distributeCard();
		myRoundController.chooseIdentity();
		myRoundController.startPlay(myRoundController.decideFirstPlayer());
	}
	
	public void roundOver(Charactor survivor) {
		BroadCast.getObject().broad("Round " + numRound + " is over, survivor is " + survivor.getName());
		
		switch(survivor.getIdentity()) {
		case witch:
			survivor.modifyScore(2);
			break;
		case villager:
			survivor.modifyScore(1);
		}
		
		roundStart();
	}
	
	public void gameOver(Charactor winner){
		BroadCast.getObject().broad("Game over, winner is " + winner.getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public LinkedList<Charactor> getCharactorList() {
		return myPlayers;
	}
}