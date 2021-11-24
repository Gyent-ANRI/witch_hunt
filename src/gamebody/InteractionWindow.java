package gamebody;

import java.util.Scanner;

public class InteractionWindow {
	
	static private int windowNumber = 1;
	private int windowCode;
	
	private void enServiceInfo() {
		System.out.println("**********************************************************");
		System.out.println("The InteractionWindow n'" + windowCode + " is en service");
		System.out.println("*****      *****      *****     *****     *****      *****");
	};
	
	public InteractionWindow() {
		windowCode = windowNumber;
		windowNumber ++;
		BroadCast.getObject().push(this);
	}

	public void outPut(String info) {
		enServiceInfo();
		System.out.println(info);
	}
	
	public String askAndWait(String info) {
		enServiceInfo();
		System.out.println(info);
		Scanner scan = new Scanner(System.in);
		String answer = scan.nextLine();
		return answer;
	}
	
	public int makeChoice(String[] info) {
		String answer = null;
		boolean loop = true;
		while(loop) {
			System.out.println("Please make Choice(enter with number) :");
			for(int i = 1; i <= info.length; i++) {
				System.out.println(i + "." + info[i-1]);
			}
			Scanner scan = new Scanner(System.in);
			answer = scan.nextLine();
			if(Integer.valueOf(answer).intValue() <= 0 || Integer.valueOf(answer).intValue() > info.length) {
				System.out.println("wrong input!");
			}
			else loop = false;
		}
		return Integer.valueOf(answer).intValue();
	}
}
