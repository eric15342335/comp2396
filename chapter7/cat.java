package chapter7;

final public class cat extends animal implements friendly {
    @Override
    public void mustbeoverriden() {
        System.out.println("cat");
    }
    @Override
    public void pet() {
        System.out.println("cat");
    }
}
