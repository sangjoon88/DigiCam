/* 반복문
 * : 조건
 * 
 * for문다운로드
 * while 문
 * 
 * do ~ while 문 / 한번은 수행한 뒤에 체크한다
 * 
 * foreach문 : 배열을 사용할 때 사용.
 * 
 * 		for (초기문; 조건문; 증감문) {
 * } 조건이 T면 블록 수행, 아니면 탈출, 그 뒤에 증감문 수행해서 초기문으로 들어감
 * 
 * 
 * 
 */

//import java.lang.*;

import java.util.Scanner;

public class banbok {

	public static void main(String []args) { 
	
		
		/*
		 *  사각형 갯수, 접은 함수
		 *  
		 *  1,0
		 *  500개 넘어가면
    	 */
		
		int rect = 1;
		int count = 0;
		
			for(count = 0; rect < 500; count++) {
				rect = rect * 2;  // rect <<=1;
			}
		
		System.out.println(count);
		System.out.println(rect);
		
		 //Scanner sc = new Scanner(System.in);		
			//int num1 = sc.nextInt();
		
				//for(int i =num1 ; i < 600 ; Math.pow(2, num1)) {
					//	System.out.println(i);
	}
}
