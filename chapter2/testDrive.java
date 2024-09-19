package chapter2;
// Lecture 2 (ch2) mentioned:
// Can use super class to hold subclass objects
// e.g. Dog d = new Dog();
// here, Object untitled = new AdvancedObject();
// is valid
// Overriding methods in subclass is possible
// but if the method is not in the superclass, it will not be accessible
// also the instance variable of the subclass will not be accessible
class Object {
    String name;
    int UniqueID;

    void exportInfo() {
        System.out.println("Hi! I am " + name + " and my ID is " + UniqueID);
    }
}

class AdvancedObject extends Object {
    String description;

    @Override
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
