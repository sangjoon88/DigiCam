package linkNode;

import java.util.Scanner;

public class LinkedExam {
	
	static LinkNode head;
	static LinkNode cur;
	static LinkNode newNode;
	static LinkNode del; 
	
	public static void main(String[] args) {
		
		head = cur = newNode = new LinkNode();
		newNode.next = null;
		
		newNode.setData(1);
		//newNode.next = null;
		
		//newNode = new LinkNode();
		newNode.next = new LinkNode();
		newNode.next.setData(2);
		newNode.next.next = null;
		
		
		newNode.next.next = new LinkNode();
		newNode.next.next.setData(3);
		newNode.next.next.next = null;
		
		newNode.next.next.next = new LinkNode();
		newNode.next.next.next.setData(4);
		newNode.next.next.next.next = null;
		
		newNode = new LinkNode();
		newNode.next = head;
		head =newNode;
		
		newNode.setData(5);
		
		cur = head; 
		//출력
		while(cur != null) {
			System.out.print(cur.getData()+",");
			cur = cur.next;			
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("몇번째 삽입? ");
		int num  = sc.nextInt();
		// 몇번째 추가할거야?
		// 데이터삽입.		
		newNode = new LinkNode();
		newNode.setData(6);
		
		if(num == 1) {
			newNode.next = head;
			head =newNode;
		}else {
			cur = head;
			
			for(int i=0;i<num-2;i++) {
				cur = cur.next;
			}
			newNode.next = cur.next;
			cur.next = newNode;
		}
		
		cur = head; 
		//출력
		while(cur != null) {
			System.out.print(cur.getData()+",");
			cur = cur.next;			
		}
		System.out.println();
		System.out.print("몇번째 삭제? ");
		num  = sc.nextInt();
		// 삭제
		
		
		if(num == 1) {
			del = head;
			head = head.next;
		}else {
			cur = head;
			
			for(int i=0;i<num-2;i++) {
				cur = cur.next;
			}
			
			del = cur.next;
			cur.next = del.next;			
		}
		del.next = null;
		del = null; 
		
		cur = head; 
		//출력
		while(cur != null) {
			System.out.print(cur.getData()+",");
			cur = cur.next;			
		}		
	}
}