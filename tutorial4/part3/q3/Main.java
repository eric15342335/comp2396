package tutorial4.part3.q3;

public class Main {
    public static void main(String[] args) {
        Guest wing = new Guest("Wing");
        Guest joy = new Guest("Joy");
        Guest marco = new Guest("Marco");

        Event bookFair = new Event("Book Fair");
        bookFair.addVenue("Hall A");
        bookFair.addVenue("Room 1");

        wing.joinEvent(bookFair, "Hall A");
        joy.joinEvent(bookFair, "Room 1");
        marco.joinEvent(bookFair, "Room 1");

        Event gunplaExpo = new Event("Gunpla Expo");
        gunplaExpo.addVenue("Room 1");

        wing.joinEvent(gunplaExpo, "Room 1");

        System.out.println(wing.hasCloseContactWith(joy)); // output: false
        System.out.println(wing.hasCloseContactWith(marco)); // output: false
        System.out.println(joy.hasCloseContactWith(marco)); // output: true
    }
}