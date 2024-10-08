package Fractals;
public class ComplexNum {
    // Numbers of the form a + ib
    private double a; // real
    private double b; // imaginary

    public ComplexNum(double a, double b){
        this.a = a;
        this.b = b;
    }

    // Math implementations
    public ComplexNum add(ComplexNum other){
        return new ComplexNum(a+other.a, b+other.b);
    }
    public ComplexNum multiply(ComplexNum other){
        return new ComplexNum(a*other.a - b*other.b, a*other.b + b*other.a);
    }
    public ComplexNum power(int exponent){
        ComplexNum pow = new ComplexNum(a, b);
        while(exponent-1 > 0){
            pow = pow.multiply(this);
            exponent--;
        }
        return pow;
    }
    public ComplexNum conjugate(){
        return new ComplexNum(a, -1.0*b);
    }
    public double magnitude(){
        return Math.sqrt(Math.pow(a, a) + Math.pow(b, b));
    }
}
