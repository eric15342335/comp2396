package tutorial4.part3.q4;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Restaurant res = new Restaurant("Hang Ten Restaurant");
        res.add_table("T1", 2);
        res.add_table("T2", 2);
        res.add_table("T3", 4);
        res.add_table("T4", 4);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader inData = new BufferedReader(isr);

        String input = inData.readLine();
        while (!input.equals("Exit")) {
            int n = Integer.parseInt(input);
            System.out.println(res.make_reservation(n));
            input = inData.readLine();
        }
        
        System.out.println("bye");
    }
}