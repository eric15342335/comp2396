package tutorial4.part3.q3;

import java.util.ArrayList;

final class Attendance {
    private final Event event;
    private final String venue;
    public Attendance(Event event, String venue) {
        this.event = event;
        this.venue = venue;
    }
    public boolean isIdentical(Attendance attendance) {
        return this.event == attendance.event && this.venue.equals(attendance.venue);
    }
}

public class Guest {
    private final String name;

    private final ArrayList<Attendance> joinedEvents = new ArrayList<>();
    public Guest(String name) {
        this.name = name;
    }
    public void joinEvent(Event event, String venue) {
        joinedEvents.add(new Attendance(event, venue));
    }
    private ArrayList<Attendance> getJoinedEvents() {
        return joinedEvents;
    }
    public boolean hasCloseContactWith(Guest guest) {
        for (Attendance attendance : joinedEvents) {
            for (Attendance guestAttendance : guest.getJoinedEvents()) {
                if (attendance.isIdentical(guestAttendance)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
