package assignment1;
import java.io.*;

class Route {
    String route_name;
    String company_code;
    int full_fare;
    Route(String[] route) {
        this.route_name = route[0];
        this.company_code = route[1];
        this.full_fare = Integer.parseInt(route[2]);
    }

    // Compare the fare of two routes
    public boolean expensive_than(Route route) {
        return this.full_fare > route.full_fare;
    }
    public int fare_difference(Route route) {
        return Math.abs(this.full_fare - route.full_fare);
    }
    public boolean operatedByBNB() {
        return this.company_code.equals("BNB");
    }
    public boolean routeNameStartsWith(String prefix) {
        return this.route_name.startsWith(prefix);
    }
}

class Question1 {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int remaining_balance = Integer.parseInt(br.readLine());
        Route first = new Route(br.readLine().split(" "));
        Route second = new Route(br.readLine().split(" "));

        if (remaining_balance >= 1) {
            // Check for (3) "does not apply" first
            if (!first.operatedByBNB() || second.routeNameStartsWith("A")) {
                // Full fare
                remaining_balance -= second.full_fare;
            } else if (!first.routeNameStartsWith("P") & second.expensive_than(first)) {
                remaining_balance -= first.fare_difference(second);
            }
        }
        System.out.println("The remaining balance is " + remaining_balance + ".");
    }
}
