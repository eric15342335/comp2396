// You don't need to keep this line too
@SuppressWarnings("unused")
public class Circle {
    private double diameter = 1;
    private String color = "red";
    private boolean filled = true;
    public Circle() {
        // Use the default values
    }
    public Circle(double diameter) {
        this.diameter = diameter;
    }
    public Circle(double diameter, String color, boolean filled) {
        this.diameter = diameter;
        this.color = color;
        this.filled = filled;
    }
    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }
    public double getDiameter() {
        return diameter;
    }
    public double getPerimeter() {
        // pi * 2r
        return Math.PI * diameter;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    public boolean getFilled() {
        return filled;
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
