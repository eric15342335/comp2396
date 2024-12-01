import java.io.*;  
import java.net.*;

public class DailyAdviceServer {
	String[] adviceList = {"Practice makes perfect", "Never give up",  "Focus on the task at hand", "Don't look back", "Be yourself", "Believe in your own work"};
	ServerSocket serverSock;

	public String getAdvice() {
		int random = (int) (Math.random() * adviceList.length);  
		return adviceList[random];
	}

	public static void main(String[] args) {  
		DailyAdviceServer server = new DailyAdviceServer();  
		server.go();
	}

	public void go() {  
		try {
			serverSock = new ServerSocket(5000);
			while (true) {
				Socket sock = serverSock.accept();
				PrintWriter writer = new PrintWriter(sock.getOutputStream());  
				String advice = getAdvice();
				writer.println(advice);  
				writer.close();  
				System.out.println(advice);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} // close go

}