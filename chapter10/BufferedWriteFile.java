import java.io.BufferedWriter;
import java.io.FileWriter;

public class BufferedWriteFile {
	public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("Test.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);  
            writer.write("This is a plain text file");
            writer.close();
        } catch (Exception ex) {  
            ex.printStackTrace();
        }
	}
}
