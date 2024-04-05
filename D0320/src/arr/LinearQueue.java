package arr;

//션형 Queue 분석

public class LinearQueue {

	int[] data;
	int front;
	int rear;

	public LinearQueue() {
		data = new int[5];
		front = rear = 0;
	}

	public void enqueue(int num) {

		if (front != 0 && rear == 5)
			shift();

		data[rear++] = num;

	}

	public void dequeue() {
		data[front++] = 0;

	}

	public void shift() {
		
		for(int i=front; i<=4; i++ ) {
			data[i-front] = data[i];
			data[i]=0;
		} 
		rear = rear-front;
		front=0; 
// 프론트값 만큼 땡기고 프론트를 0으로 만들어주는 아이디어로 접근
		
	}

	public void disp() {

		for (int i : data) {
			System.out.print(i + " , ");	
		}
		System.out.print("    front : " + front+"   " + "rear : " + rear);
		
		System.out.println();
	}

	public static void main(String[] args) {

		LinearQueue qe = new LinearQueue();

		qe.enqueue(1);
		qe.disp();
		qe.enqueue(2);
		qe.disp();
		qe.enqueue(3);
		qe.disp();
		qe.enqueue(4);
		qe.disp();

		qe.dequeue();
		qe.disp();
		qe.dequeue();
		qe.disp();

		qe.enqueue(5);
		qe.disp();
		// shift
		qe.enqueue(6); qe.disp(); // 3,4,5,6
	}

}
