package tutorial4.part3.q4;

import java.util.ArrayList;

class Table {
    private final String name;
    private final int capacity;
    private int reserved = 0;

    public Table(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getReserved() {
        return reserved;
    }

    public int getAvailable() {
        return getCapacity() - getReserved();
    }

    public boolean make_reservation(int n) {
        if (reserved + n <= capacity) {
            reserved += n;
            return true;
        } else {
            return false;
        }
    }
}

public class Restaurant {
    private final String name;
    private final ArrayList<Table> tables = new ArrayList<>();

    public Restaurant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add_table(String name, int capacity) {
        tables.add(new Table(name, capacity));
    }

    private String _make_reservation_formatter(Table table, int n) {
        return String.format("Table %s for %d guest(s).", table.getName(), n);
    }
    public String make_reservation(int n) {
        for (Table table : tables) {
            // if table fits all people perfectly
            if (table.getCapacity() == n && table.make_reservation(n)) {
                return _make_reservation_formatter(table, n);
            }
        }
        // Finds table that don't have anyone
        for (Table table : tables) {
            if (table.getReserved() == 0 && table.make_reservation(n)) {
                return _make_reservation_formatter(table, n);
            }
        }
        // Finds table that does not fit all people perfectly
        for (Table table : tables) {
            if (table.getAvailable() >= n && table.make_reservation(n)) {
                return _make_reservation_formatter(table, n);
            }
        }
        return String.format("No table available for %s guest(s).", n);
    }
}
