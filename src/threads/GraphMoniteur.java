package threads;

import java.util.Observable;

/**
 * A runnable class for monitoring the GUI
 * @author Jiyang QI
 *
 */
public class GraphMoniteur  extends Observable implements Runnable{
	
	private boolean active;
	private boolean confirm;
	private String answer;

	
	public GraphMoniteur(){
		active = false;
		confirm = false;
	}
	
	public void active() {
		active = true;
	}
	
	public void disactive() {
		active = false;
		confirm = false;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(this) {
			while(true) {
				while((!active) || (!confirm)) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				this.setChanged();
				this.notifyObservers(answer);
				this.confirm = false;
			}
		}
	}
	
	public void setAnswer(String ans) {
		answer = ans;
		confirm = true;
	}
	
	/**
	 * notify this thread
	 */
	public void march() {
		synchronized(this) {
			this.notify();
		}
	}

}
