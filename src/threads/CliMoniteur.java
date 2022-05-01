package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;


/**
 * A runnable class for monitoring the command line
 * @author Jiyang QI
 *
 */
public class CliMoniteur extends Observable implements Runnable{

	private boolean active;
	
	public CliMoniteur(){
		active = false;
	}
	
	public void active() {
		active = true;
	}
	
	public void disactive() {
		active = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(this) {
			while(true) {
				while(!active) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					readCli();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * use buffered reader to determine if there is an input
	 * @throws IOException
	 */
	public void readCli() throws IOException {
		while(active) {
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			if(!in.ready()) {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else {
				this.setChanged();
				this.notifyObservers(in.readLine());
			}
		}
	}
	
	
}
