class Object {
    String name;
    int UniqueID;

    void exportInfo() {
        System.out.println("Hi! I am " + name + " and my ID is " + UniqueID);
    }
}

class AdvancedObject extends Object {
    String description;

    void exportInfo() {
        System.out.print("Hi! I am " + name + " and my ID is " + UniqueID);
        System.out.println(" and my description is " + description);
    }
}

class testDrive {
    public static void main(String[] args) {
        Object untitled = new AdvancedObject();
        untitled.name = "Untitled";
        untitled.UniqueID = 10;
        // untitled.description = "I am an untitled object";
        untitled.exportInfo();
    }
}
