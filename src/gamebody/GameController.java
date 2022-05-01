package gamebody;

import java.util.LinkedList;

import players.Charactor;
import players.InputManager;
import players.VirtualPlayer;
import threads.CliMoniteur;
import threads.GraphMoniteur;
import vue.GameStartFrame;


/**
 * Classes that manage the whole game
 * @author Jiyang QI
 *
 */
public class GameController {
	
	//variable
	private int numRound;
	private LinkedList<Charactor> myPlayers;
	private RoundController myRoundController;
	static private GameController myObject = null;
	private CliMoniteur cli;
	private GraphMoniteur gra;
	
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
	
	/**
	 * method to declare game start
	 */
	public void gameStart() {
		
		//create a thread to read command line and a thread to read graphique interface
		cli = new CliMoniteur();
		gra = new GraphMoniteur();
		Thread cliThread = new Thread(cli);
		Thread graThread = new Thread(gra);
		cliThread.start();
		graThread.start();
		InputManager.newObject(cli,gra);
		
		System.out.println("Game starte");
		
		
		gameInitial();
		
		roundStart();
	}
	
	/**
	 * method to start round
	 */
	public void roundStart() {
		while(true) {
			numRound += 1;
			BroadCast.getObject().broad("Round " + numRound + " started");
			myRoundController = RoundController.newObject(myPlayers);
			BroadCast.getObject().scoreModified();////
			myRoundController.distributeCard();
			myRoundController.chooseIdentity();
			myRoundController.decideFirstPlayer();
			myRoundController.startPlay();
			//reset the cards and the status of the players
			for(int i = 1; i <= myPlayers.size(); i++) {
				myPlayers.get(i-1).reset();
			}
		}
	}
	
	/**
	 * method to declare the end of a round
	 * @param survivor
	 */
	public void roundOver(Charactor survivor) {
		BroadCast.getObject().broad("Round " + numRound + " is over, survivor is " + survivor.getName());
		
		switch(survivor.getIdentity()) {
		case witch:
			survivor.modifyScore(2);
			break;
		case villager:
			survivor.modifyScore(1);
		}
		RevealedCardArea.getObject().clear();
		DisCardArea.getObject().clear();
		
	}
	
	/**
	 * method to declare the end of the game
	 * @param winner
	 */
	public void gameOver(Charactor winner){
		BroadCast.getObject().broad("Game over, winner is " + winner.getName());
		BroadCast.getObject().broad("The program will exit in ten seconds");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public LinkedList<Charactor> getCharactorList() {
		return myPlayers;
	}
	
	/**
	 * Initialization before the official start of the game
	 */
	public void gameInitial() {

		GameStartFrame vg = new GameStartFrame();
		int numPlayer;
		int numVirtual;
		
		int answer;
		
		do {
			System.out.println("Press 1 to start game");
			answer = Integer.valueOf(InputManager.getObject().getInput());
		}while(answer != 1);
			vg.gameStarted();
		
		//Ask number of player
		do {
			System.out.println("The number of player?(3-6)");
			vg.setLable("The number of player?(3-6)");
			numPlayer = Integer.valueOf(InputManager.getObject().getInput());
			
			if(!(numPlayer <= 6 && numPlayer >= 3))
				System.out.println("Wrong Input!");
		}while(!(numPlayer <= 6 && numPlayer >= 3));
				
		//Ask number of Virtual player
		do {
			System.out.println("Number of virtual player?(0-" + (numPlayer-1) + ")");
			vg.setLable("Number of virtual player?(0-" + (numPlayer-1) + ")");
			numVirtual = Integer.valueOf(InputManager.getObject().getInput());
			
			if(!(numVirtual <= numPlayer - 1 && numVirtual >= 0))
				System.out.println("Wrong Input!");
		}while(!(numVirtual <= numPlayer - 1 && numVirtual >= 0));
		vg.dispose();
				
		//create players and set name
		for(int i = 0; i < numPlayer-numVirtual; i++) {
			myPlayers.add(new Charactor());
			String playerName = myPlayers.get(i).getInteractionWindow().askAndWait("What's your name? ");					myPlayers.get(i).setName(playerName);
		}
		for(int i = 0; i < numVirtual; i++) {
			myPlayers.add(new VirtualPlayer());
		}
			
		//create windows for every real player
		myPlayers.forEach(player->player.getInteractionWindow().createWindow(player, gra));
	}
}
