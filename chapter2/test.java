package chapter2;

/**
 * Conclusion: Object is like a remote control
 * Can only access the buttons that are on the remote control
 * (methods and instance variables)
 * But the behavior of the buttons is determined by the actual object
 * (subclass)
 */
@SuppressWarnings("unused")
class base {
    int instance_variable;
    void method() {
        // do nothing
    }
}

@SuppressWarnings("unused")
class extended extends base {
    int another_instance_variable;
    @Override
    void method() {
        System.out.println("Does something " + another_instance_variable);
    }
    // new method
    void newMethod() {
        System.out.println("Does something new");
    }
}

public class test {
    public static void main(String[] args) {
        base example = new extended();
        example.method();
        // example.newMethod(); // compiler error
    }
}
