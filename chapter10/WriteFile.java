import java.io.FileWriter;

public class WriteFile {

	public static void main(String[] args) {
		try {
			FileWriter writer = new FileWriter("Test.txt");  
			writer.write("This is a plain text file");
			writer.close();              
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
