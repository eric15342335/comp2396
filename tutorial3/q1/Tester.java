import java.io.*;

public class Tester {
	public static void main(String[] args) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader inData = new BufferedReader(isr);
		
		int c2_diameter = Integer.parseInt(inData.readLine());
		String c2_color = inData.readLine();
		int c3_diameter = Integer.parseInt(inData.readLine());
		String c3_color = inData.readLine();
		
        System.out.println("Creating circles...");
        Circle c1 = new Circle();
        Circle c2 = new Circle(c2_diameter);
        Circle c3 = new Circle(c3_diameter, c3_color, true);
        
        System.out.println("Getting the diameter of the circles...");
        System.out.println("C1: " + c1.getDiameter());
        System.out.println("C2: " + c2.getDiameter());
        System.out.println("C3: " + c3.getDiameter());
        
        System.out.println("Setting a new diameter of C2...");
        c2.setDiameter(16);
        System.out.println("The new perimeter of C2...");
        System.out.println("C2: " + c2.getPerimeter());
        
        System.out.println("Setting new color of c2...");
        c2.setColor(c2_color);
        c2.setFilled(true);
        
        System.out.println("Getting the information of c2...");
        System.out.println("C2 Color: " + c2.getColor() + " Filled: " + c2.getFilled());
        System.out.println("Getting the information of c3...");
        System.out.println("C3 Color: " + c3.getColor() + " Filled: " + c3.getFilled());
    }
}
