package players;


import gamebody.BroadCast;
import threads.GraphMoniteur;
import vue.ChooseWindow;
import vue.MainWindows;
import vue.InputFrame;

import java.util.Observable;
import java.util.Scanner;

import cards.RumourCard;

/**
 * Each player's input and output is done through the interaction window he has
 * @author Jiyang QI
 *
 */
public class InteractionWindow extends Observable{
	
	private int refreshCount;
	static private int windowNumber = 1;
	private int windowCode;
	private MainWindows myVue;
	private Charactor owner;
	private RumourCard[] cardMain;
	private String textCaching;
	
	private void enServiceInfo() {
		System.out.println("**********************************************************");
		System.out.println("The InteractionWindow n. " + windowCode + " is en service");
		System.out.println("*****      *****      *****     *****     *****      *****");
	};
	
	
	public InteractionWindow(Charactor owner) {
		refreshCount = 0;
		windowCode = windowNumber;
		windowNumber ++;
		BroadCast.getObject().push(this);
		this.owner = owner;
	}
	
	public void createWindow(Charactor owner,GraphMoniteur gra) {
		myVue = new MainWindows(owner.getName(),gra);
		this.addObserver(myVue);
	}

	public void outPut(String info) {
		textCaching = info;
		enServiceInfo();
		System.out.println(info);
		this.setChanged();
		this.notifyObservers(info);
	}
	
	public String askAndWait(String info) {
		enServiceInfo();
		System.out.println(info);
		InputFrame vt = new InputFrame("Player "+windowCode);
		vt.ask(info);
		String answer = InputManager.getObject().getInput();
		vt.dispose();
		return answer;
	}
	
	public int makeChoice(String[] info) {
		String answer = null;
		boolean loop = true;
		ChooseWindow cw;
		while(loop) {
			System.out.println("Please make Choice(enter with number) :");
			for(int i = 1; i <= info.length; i++) {
				System.out.println(i + "." + info[i-1]);
			}
			cw = new ChooseWindow(owner.getName(),textCaching,info);
			answer = InputManager.getObject().getInput();
			if(Integer.valueOf(answer).intValue() <= 0 || Integer.valueOf(answer).intValue() > info.length) {
				outPut("wrong input!");
			}
			else loop = false;
			cw.dispose();
		}
		return Integer.valueOf(answer).intValue();
	}
	
	
	
	public int chooseCardFromHand(String[] info) {
		String answer = null;
		boolean loop = true;
		while(loop) {
			System.out.println("Please make Choice(enter with number) :");
			for(int i = 1; i <= info.length; i++) {
				System.out.println(i + "." + info[i-1]);
			}
			if(myVue != null)
				myVue.cofirmButtonEnable();
			answer = InputManager.getObject().getInput();
			if(Integer.valueOf(answer).intValue() <= 0 || Integer.valueOf(answer).intValue() > info.length) {
				outPut("wrong input!");
			}
			else loop = false;
		}
		return Integer.valueOf(answer).intValue();
	}
	
	/**
	 * Remind the view that card has been changed
	 * @param newCardMain
	 */
	public void cardChanged(RumourCard[] newCardMain) {
		this.cardMain = newCardMain;
		this.setChanged();
		this.notifyObservers(cardMain);
	}
	
	/**
	 * Remind the view that score has been changed
	 */
	public void scoreModified() {
		refreshCount += 1;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * Remind the view that identity has been changed
	 */
	public void refreshIdentity() {
		this.setChanged();
		this.notifyObservers(owner.getIdentity());
	}
}
