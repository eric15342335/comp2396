public class CmdInsertCoin implements Command {

	@Override
	public String execute(VendingMachine v, String[] cmdParts) {
		Integer c = Integer.valueOf(cmdParts[1]);
		// Add the coin to Coin Slot
		v.insertCoin(c);
		// Do something
		// return a string "Inserted a $x coin. $y in total."
		return "Inserted a $" + c + " coin. $" + v.getTotalInsertedCoinsAmount() + " in total.";
		// x and y must be replaced by an appropriate value

	}
}