import java.io.BufferedReader;
import java.io.FileReader;

public class BufferedReadFile {

	public static void main(String[] args) {
		try {
			FileReader fileReader = new FileReader("Test.txt");  
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			// put reader.readLine() in a while loop condition checking
			// (line = reader.readLine()) is actually String line
			// aka (line = reader.readLine()) is what the variable `line` holding
			while ((line = reader.readLine()) != null) {  
				System.out.println(line);
			}
			reader.close();
		} catch (Exception ex) { ex.printStackTrace(); }
	}

}
