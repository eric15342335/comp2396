package chapter7;

@SuppressWarnings("unused")
final class dog extends animal {
    int y;
    @Override
    void mustbeoverriden() {
        System.out.println("dog");
    }
}

@SuppressWarnings("unused")
interface friendly {
    void pet();
}

class bruh {
    public final int x;
    bruh() {
        x = 1;
    }
}

class test {
    static int x=8;
    test() {
        x = 9;
    }
}

public class abstracts {
    static int z;
    static {
        z = 3;
        System.out.println(z); // is called before main
    }
    public static void main(String[] args) {
        animal[] a = new animal[10]; //support polymorphic array
        //a[0] = new animal(); //error
        a[0] = new dog();
        a[0].mustbeoverriden();
        a[1] = new cat();
        final int x;
        x=1;
        System.out.println(x);
        bruh b = new bruh();
        System.out.println(b.x);
        final int y;
        {
            y = 1;
            System.out.println(y);
        }
        System.out.println(test.x);
        test t = new test();
        System.out.println(t.x);
        Integer pp = 100;
        System.out.println(pp);
    }
}