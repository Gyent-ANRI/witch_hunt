package players;

import java.util.Observable;
import java.util.Observer;

import threads.CliMoniteur;
import threads.GraphMoniteur;
import vue.MainWindows;

/**
 * Class for receiving input, or buffer for input
 * observe two thread(one reading the command line and one reading the GUI)
 * @author Jiyang QI
 *
 */
public class InputManager implements Observer{
	
	private String input;
	private boolean available;
	private CliMoniteur cliMoniteur;
	private GraphMoniteur graMoniteur;
	private static InputManager myObject = null;
	
	private InputManager(CliMoniteur cli, GraphMoniteur gra) {
		input = null;
		available = false;
		cliMoniteur = cli;
		cliMoniteur.addObserver(this);
		graMoniteur = gra;
		graMoniteur.addObserver(this);
	};
	
	public static InputManager newObject(CliMoniteur cli, GraphMoniteur gra) {
		if(myObject == null) {
			myObject = new InputManager(cli,gra);
		}
		return myObject;
	}
	
	public static InputManager getObject() {
		return myObject;
	}

	/**
	 * When any thread gets input, the input will be stored in the unique object of this class, 
	 * and then all threads will be closed
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		cliMoniteur.disactive();
		graMoniteur.disactive();
		input = (String)arg;
		available = true;
		
	}
	
	/**
	 * Wake up two threads and prepare to accept input
	 */
	private void needInput() {
		input = null;
		available = false;
		cliMoniteur.active();
		graMoniteur.active();
		synchronized(cliMoniteur) {
			cliMoniteur.notify();
		}
	}
	
	/**
	 * Determine if there is an accepted input every 250ms, 
	 * and return the input value if there is.
	 * @return input
	 */
	public String getInput() {
		needInput();
		while(!available) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return input;
	}
	
	public boolean available() {
		return available;
	}
	
	public GraphMoniteur getGraphMoniteur() {
		return this.graMoniteur;
	}

}
