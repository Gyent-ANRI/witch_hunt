package gamebody;

import players.InteractionWindow;

/**
 * Class singleton for sending messages to all player windows
 * @author Jiyang QI
 *
 */
public class BroadCast {
	
	private BroadCast() {listBC = new InteractionWindow[0];};
	static private BroadCast myObject = null;
	private InteractionWindow[] listBC;
	
	static public BroadCast getObject() {
		if(myObject == null)
			myObject = new BroadCast();
		return myObject;
	}

	/**
	 * send message to all the windows
	 * @param info
	 */
	public void broad(String info) {
		for(int i = 0; i < listBC.length; i++) 
			listBC[i].outPut(info);
	}
	
	/**
	 * add new windows
	 * @param window
	 */
	public void push(InteractionWindow window) {
		InteractionWindow[] oddList = listBC;
		listBC = new InteractionWindow[oddList.length + 1];
		for(int i = 0; i < oddList.length; i++) {
			listBC[i] = oddList[i];
		}
		listBC[oddList.length] = window;
	}
	
	/**
	 * declare to all windows that the score is modified
	 */
	public void scoreModified() {
		for(InteractionWindow iw:listBC) {
			iw.scoreModified();
		}
	}
}
