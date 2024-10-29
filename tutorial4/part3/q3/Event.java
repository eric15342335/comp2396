package tutorial4.part3.q3;

import java.util.ArrayList;

public class Event {
    private final String name;
    private final ArrayList<String> venues = new ArrayList<>();
    public Event(String name) {
        this.name = name;
    }
    public void addVenue(String venue) {
        venues.add(venue);
    }

    public String getName() {
        return name;
    }
}
