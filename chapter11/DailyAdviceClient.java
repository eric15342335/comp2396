import java.io.*;  
import java.net.*;

public class DailyAdviceClient {  
	Socket sock;

	public void go() {  
		try {
			sock = new Socket("127.0.0.1", 5000);  
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());  
			BufferedReader reader = new BufferedReader(streamReader);  
			String advice = reader.readLine();
			System.out.println("Today's advice: " + advice);  
			reader.close();
		} catch (Exception ex) { 
			ex.printStackTrace(); 
		}
	}

	public static void main(String[] args) {
		DailyAdviceClient d = new DailyAdviceClient();
		d.go();
	}

}
