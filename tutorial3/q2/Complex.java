@SuppressWarnings("unused")
public class Complex {
    private int real;
    private int imaginary;
    public Complex(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    public void add(Complex c) {
        this.real += c.real;
        this.imaginary += c.imaginary;
    }
    public void subtract(Complex c) {
        this.real -= c.real;
        this.imaginary -= c.imaginary;
    }
    public String toString() {
        // `imaginary >= 0`, not `imaginary > 0`
        return real + (imaginary >= 0 ? " + " : " - ") + Math.abs(imaginary) + "i";
    }
}
