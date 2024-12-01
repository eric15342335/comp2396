import java.io.*;

public class BoxTestDrive2 {
	public static void main(String[] args) {  
		Box box = null;

		try {
			FileInputStream fs = new FileInputStream("Box.sav");  
			ObjectInputStream os = new ObjectInputStream(fs);
			box = (Box) os.readObject();
			os.close();
		} catch (Exception ex) {  
			ex.printStackTrace();
		}

		System.out.println(box.getWidth());
		System.out.println(box.getHeight());
	}
}

