// 2014 Dec q1c
/* The error is because Java requires a call to the super class
* constructor in the subclass constructor, if the
* super class constructor is not default constructor.
* THe fix would be adding
* super(s, s); in the first line of the Square constructor
* The output would be:
* Rectangle()
* Square()
* Rectangle()
 */

class Rectangle {
    double width, height;

    public Rectangle(double w, double h) {
        width = w;
        height = h;
        System.out.println("Rectangle()");
    }
}
class Square extends Rectangle {
    double size;
    public Square(double s) {
        // Requires a call to the super class constructor
        super(s, s);
        size = s;
        System.out.println("Square()");
    }
}
public class Shape {
    public static void main(String[] args) {
        Square sq = new Square(1.0);
        Rectangle re = new Rectangle(1.0, 2.0);
    }
}
