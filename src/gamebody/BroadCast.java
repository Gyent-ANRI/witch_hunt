package gamebody;

import Players.InteractionWindow;

public class BroadCast {
	
	private BroadCast() {listBC = new InteractionWindow[0];};
	static private BroadCast myObject = null;
	private InteractionWindow[] listBC;
	
	static public BroadCast getObject() {
		if(myObject == null)
			myObject = new BroadCast();
		return myObject;
	}

	public void broad(String info) {
		for(int i = 0; i < listBC.length; i++) 
			listBC[i].outPut(info);
	}
	
	public void push(InteractionWindow window) {
		InteractionWindow[] oddList = listBC;
		listBC = new InteractionWindow[oddList.length + 1];
		for(int i = 0; i < oddList.length; i++) {
			listBC[i] = oddList[i];
		}
		listBC[oddList.length] = window;
	}
}
