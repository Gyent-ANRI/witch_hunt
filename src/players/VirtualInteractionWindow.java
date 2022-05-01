package players;

import threads.GraphMoniteur;

/**
 * Virtual player holds a window that closes off unwanted methods 
 * and return a random values when asked to make a selection
 * @author Jiyang QI
 *
 */
public class VirtualInteractionWindow extends InteractionWindow{
	
	public VirtualInteractionWindow(VirtualPlayer owner) {super(owner);}
	
	public void outPut(String info) {};
	
	public void createWindow(Charactor owner, GraphMoniteur gra) {
	}
	
	public String askAndWait(String info) {return null;}
	
	public int makeChoice(String[] info) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (1 + (int)(Math.random()*(info.length-0.1)));
	}
	
	public int chooseCardFromHand(String[] info) {
		return makeChoice(info);
	}
}
