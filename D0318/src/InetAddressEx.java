import java.net.*;  // InetAddress 의 메소드 쓸려고 하는거 // 스테틱 메소드 쓰는 법 알아야할듯?
public class InetAddressEx {

	public static void main(String[] args) throws UnknownHostException { // 필수

		InetAddress iaddr = InetAddress.getLocalHost(); // 로컬
		System.out.printf("호스트이름 : %s %n", iaddr.getHostName());
		System.out.printf("호스트IP주소 : %s %n", iaddr.getHostAddress());

		iaddr = InetAddress.getByName("java.sun.com"); //
		System.out.printf("호스트이름 : %s %n", iaddr.getHostName());
		System.out.printf("호스트IP주소 : %s %n", iaddr.getHostAddress());

		InetAddress sw[] = InetAddress.getAllByName("www.kbstar.com");
		for (InetAddress temp_sw : sw) {
			System.out.printf("호스트이름 : %s,", temp_sw.getHostName());
			System.out.printf("호스트IP주소 : %s %n", temp_sw.getHostAddress());
		}
	}
}
