class A {
    public void function1() {
        System.out.println("A Function 1");
    }
    public void function2() {
        this.function1(); // "function1();" has the same effect
        System.out.println("A Function 2");
    }
}

class B extends A {
    public void function1() {
        System.out.println("B Function 1");
    }
    public void function2() {
        super.function2();
        System.out.println("B Function 2");
    }
}

class superthis {
    public static void main(String[] args) {
        B obj = new B();
        obj.function2();
        /*
        B Function 1
        A Function 2
        B Function 2
         */
    }
}