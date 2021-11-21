package gamebody;

public class GameController {
	
	//variable
	private int numRound;
	private Charactor[] myPlayers;
	private RoundController myRoundController;
	static private GameController myObject = null;
	
	//methods
	private GameController() {
		numRound = 0;
		myPlayers = null;
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
		myPlayers = new Charactor[numPlayer];
		myPlayers[0] = lancer;

		
		//create players and set name
		for(int i = 1; i < numPlayer; i++) {
			myPlayers[i] = new Charactor();
			String playerName = myPlayers[i].getInteractionWindow().askAndWait("What's your name? ");
			myPlayers[i].setName(playerName);
		}
		
		roundStart();
	}
	
	public void roundStart() {
		numRound += 1;
		BroadCast.getObject().broad("Round " + numRound + " started");
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
	
	public Charactor[] getCharactorList() {
		return myPlayers;
	}
}
