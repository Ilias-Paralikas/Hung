package UserInterface;

public class Probabilities {
    private double A ,B,C;

    Probabilities(double a, double b,double c){
        this.A = a;
        this.B = b;
        this.C = c;
    }
    public void setA(double a) {    this.A = a; }
    public void setB(double b) {    this.B = b; }
    public void setC(double c) {    this.C = c; }
    public double getA() {  return A;   }
    public double getB() {  return B;   }
    public double getC() {  return C;   }


}