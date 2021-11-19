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
		
		//Ask lancer name and number of player
		String name = lancer.getInteractionWindow().askAndWait("What's your name? ");
		lancer.setName(name);
		String num = lancer.getInteractionWindow().askAndWait("Number of player? ");
		int numPlayer = Integer.valueOf(num).intValue();
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
	
	public void roundOver() {
		System.out.println("The round is over");
	}
	
	public void gameOver() {
		System.out.println("The game is over");
	}
	
	public Charactor[] getCharactorList() {
		return myPlayers;
	}
}
