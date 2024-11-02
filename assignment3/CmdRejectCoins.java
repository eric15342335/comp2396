import java.util.ArrayList;

public class CmdRejectCoins implements Command {
    @Override
    public String execute(VendingMachine v, String[] cmdParts) {
        int total = v.getTotalInsertedCoinsAmount();
        if (total == 0) {
            return "Rejected no coin!";
        }
        else {
            String return_text = "Rejected ";
            // only $1, $2, $5, $10 coins are accepted
            // print the coins in the order of $1, $2, $5, $10
            ArrayList<ArrayList<Integer>> coins_list = v.getGroupedInsertedCoins();
            for (int i = 0; i < 4; i++) {
                for (Integer c : coins_list.get(i)) {
                    return_text += "$" + c + ", ";
                }
            }
            // remove the last ", " and add "."
            return_text = return_text.substring(0, return_text.length() - 2) + ".";
            return_text += " $" + total + " in total.";
            v.dumpAllCoins();
            return return_text;
        }
    }
}