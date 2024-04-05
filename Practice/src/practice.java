
public class practice {

int add(int[] a) {
	int result =0;
	for(int i =0;i<a.length;i++) {
		a[i]=i;
		result +=a[i];
	}
	return result;
}
	
public static void main (String[] args) {

	practice pr = new practice();
	int[] b = new int[6];
	
	System.out.println(pr.add(b));

 }
}
