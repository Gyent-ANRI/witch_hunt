package Players;

public class VirtualInteractionWindow extends InteractionWindow{
	
	public VirtualInteractionWindow() {};
	
	public void outPut(String info) {};
	
	public String askAndWait(String info) {return null;}
	
	public int makeChoice(String[] info) {
		return (1 + (int)(Math.random()*(info.length-0.1)));
	}
}
