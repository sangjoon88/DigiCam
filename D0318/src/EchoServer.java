import java.io.*; 
import java.net.*; 
public class EchoServer{ 
	private BufferedReader bufferR; 
	private BufferedWriter bufferW; 
	private InputStream is; 
	private OutputStream os; 
	private ServerSocket serverS; 
	public EchoServer(int port){ 
		try{ 
			serverS = new ServerSocket(port); 
		}catch(IOException ioe){ 
			ioe.printStackTrace(); 
			System.exit(0); 
		} 
		while(true){ 
			try{ 
				System.out.println("클라이언트의 요청을 기다리는 중"); 
				Socket tcpSocket = serverS.accept();                        //accept는 무한루프를 돌면서 소켓을 하겠다고 선언
				System.out.println("클라이언트의 IP 주소 : "+ 
						tcpSocket.getInetAddress().getHostAddress()); 
				is = tcpSocket.getInputStream(); 
				os = tcpSocket.getOutputStream(); 
				bufferR = new BufferedReader( 
						new InputStreamReader(is)); 
				bufferW = new BufferedWriter( 
						new OutputStreamWriter(os)); 
				String message = bufferR.readLine(); 
				System.out.println("수신메시지 : "+ message); 
				message += System.getProperty("line.separator"); 
				bufferW.write(message); 
				bufferW.flush(); 
				bufferR.close(); 
				bufferW.close(); 
				tcpSocket.close(); 
			}catch(IOException ioe){ 
				ioe.printStackTrace(); 
			} 
		} 
	} 
	public static void main(String[] args){ 
		new EchoServer(3000);            // 서버정보를 가져오는 포트
	} 
} 
