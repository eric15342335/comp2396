import java.io.*;

public class Tester {
	public static void main(String[] args) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader inData = new BufferedReader(isr);
		
		int c1_real = Integer.parseInt(inData.readLine());
		int c1_imaginary = Integer.parseInt(inData.readLine());
		int c2_real = Integer.parseInt(inData.readLine());
		int c2_imaginary = Integer.parseInt(inData.readLine());
		
		Complex c1 = new Complex(c1_real, c1_imaginary);
		Complex c2 = new Complex(c2_real, c2_imaginary);
		
		c1.add(c2);
		System.out.println(c1.toString());
		c1.subtract(c2);
		c1.subtract(c2);
		System.out.println(c1.toString());
	}
}
