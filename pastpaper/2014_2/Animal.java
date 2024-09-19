package pastpaper._2014_2;

// 2014 Dec 1b
/*
* The problem is that toString() in Animal attempt to
* override a public method, Object.toString(),
* with a private method. Overriding a method does not
* allow the overriding method to have a more restrictive
* access modifier. The method in the subclass must have
* the same or less restrictive access modifier.
* The fix is to remove the private modifier from the
* toString() method in Animal and make it public.
* The output of the program is:
* Animal()
* Animal
*/

public class Animal {
    public Animal() {
        System.out.println("Animal()");
    }
    // private String toString() {
    public String toString() {
        return "Animal";
    }
    public static void main(String[] args) {
        Animal a = new Animal();
        System.out.println(a);
    }
}