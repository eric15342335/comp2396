import java.io.*;

public class Box implements Serializable {  
	private static final long serialVersionUID = 2396L;
	private int width;
	private int height;
	public void setWidth(int w) { width = w; }  
	public void setHeight(int h) { height = h; }

	public int getWidth() { return width; }  
	public int getHeight() { return height; }
}