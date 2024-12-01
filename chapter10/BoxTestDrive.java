import java.io.*;

public class BoxTestDrive {
	public static void main(String[] args) {  
		Box box = new Box();
		box.setWidth(70);  
		box.setHeight(30);

		try {
			FileOutputStream fs = new FileOutputStream("Box.sav");  
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(box);  
			os.close();
		} catch (Exception ex) {  
			ex.printStackTrace();
		}
	}
}
