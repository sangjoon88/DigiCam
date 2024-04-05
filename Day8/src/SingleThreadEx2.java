
public class SingleThreadEx2 implements Runnable {
	private int[] temp;

	public SingleThreadEx2() {
	//	super(threadname);
		temp = new int[10];
		for (int start = 0; start < temp.length; start++) {
			temp[start] = start;
		}
	}

	public void run() {
		for (int start : temp) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
				System.out.printf("현재 쓰레드 이름 : %s,", Thread.currentThread().getName());
				System.out.printf("temp value : %d %n", start);
			}
		}

	

	public static void main(String[] args) {
		SingleThreadEx2 st = new SingleThreadEx2();
	//	Thread th = new Thread("superman");
	//	Thread th = new Thread(st);
	//	th.setName("superman");
		
		Thread th = new Thread(st,"suerman");
		
		th.start();
	}
}