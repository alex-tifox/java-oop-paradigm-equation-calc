import com.sun.xml.internal.ws.policy.ComplexAssertion;
import com.vm.jcomplex.Complex;

import java.util.Scanner;
import java.util.Vector;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class SqrtMethod extends Equation {
    protected double err, delta, pdelta, x1r, x2r, x3r, x4r, x1u, x2u, x3u, x4u, su, sr, rr, ru, mr, mu;
    SqrtMethod(){}

    @Override
    public void setErr(double err) {
        this.err = err;
    }

    @Override
    public void calculateRoots() {
        double err = getErr();
        double t1, t2, t1r, t2r, t1u, t2u;

        pdelta = sqrt(abs(delta));
        if (inputData.elementAt(0) == 0)
            if (delta < 0) {
                outputData.add(new Complex(1.0*(-1) * 0/ (2 * inputData.elementAt(1)),
                        (-1.0) * pdelta / ((1.0) * (2* inputData.elementAt(1)))));
                outputData.add(new Complex((1.0)*(-1)*0/(2*inputData.elementAt(1) / ((1.0)*(2*inputData.elementAt(1)) ))));
            } else if (delta > 0) {
                outputData.add(new Complex(inputData.elementAt(0) - pdelta / (2.0 * inputData.elementAt(1)), 0.0));
                outputData.add(new Complex(inputData.elementAt(0) + pdelta / (2.0 * inputData.elementAt(1)), 0));
            } else {
                Complex complex = new Complex(inputData.elementAt(0) +1e-5 / (2 * inputData.elementAt(1)), 0);
                outputData.add(complex);
            }
        else

            if (delta > 0) {
                t1 = ((-1.0) * inputData.elementAt(1) - pdelta) / (2.0 * inputData.elementAt(0));
                t2 = ((-1.0)* inputData.elementAt(1) + pdelta) / (2.0 *  inputData.elementAt(0));
                addTComplexReplacement(t1);
                addTComplexReplacement(t2);
            } else if (delta < 0) {
                t1r = ((-1.0)* inputData.elementAt(1)) / (2.0 *  inputData.elementAt(0));
                t2r = t1r;

                t1u = (-1.0*pdelta) / (2 * inputData.elementAt(0));
                t2u = (-1.0)*t1u;

                outputData.add(new Complex(sqrt((sqrt(pow(t1r, 2) + pow(t1u, 2)) + t1r) / 2) ,sqrt((sqrt(pow(t1r, 2) + pow(t1u, 2)) - t1r) / 2)) );
                outputData.add(new Complex((-1.0)*sqrt((sqrt(pow(t1r, 2) + pow(t1u, 2)) + t1r) / 2) ,sqrt((sqrt(pow(t1r, 2) + pow(t1u, 2)) - t1r) / 2) ));
                outputData.add(new Complex( sqrt((sqrt(pow(t2r, 2) + pow(t2u, 2)) + t2r) / 2) ,(-1.0)*sqrt((sqrt(pow(t2r, 2) + pow(t2u, 2)) - t2r) / 2) ));
                outputData.add(new Complex((-1.0)*sqrt((sqrt(pow(t2r, 2) + pow(t2u, 2)) + t2r) / 2) ,(-1.0)*sqrt((sqrt(pow(t2r, 2) + pow(t2u, 2)) - t2r) / 2)));

            } else {
                t1r = ((-1.0)* inputData.elementAt(1)) / (2.0 *  inputData.elementAt(0));
                if (t1r >= 0) {
                    outputData.add(new Complex(sqrt(t1r),0));
                    outputData.add(new Complex((-1.0)*sqrt(t1r),0));
                    outputData.add(new Complex(sqrt(t1r),0));
                    outputData.add(new Complex((-1.0)*sqrt(t1r),0));
                } else {
                    x1u = sqrt(-1.0*t1r);
                    outputData.add(new Complex(0 , sqrt(-1.0*t1r)));

                    x2u = (-1.0)*sqrt(-1.0*t1r);
                    outputData.add(new Complex(0 , (-1.0)*sqrt(-1.0*t1r)));

                    x3u = sqrt(-1.0*t1r);
                    outputData.add(new Complex(0 , sqrt(-1.0*t1r)));

                    x4u = (-1.0)*sqrt(-1.0*t1r);
                    outputData.add(new Complex(0 , (-1.0)*sqrt(-1.0*t1r)));
                }
        }
    }

    private void addTComplexReplacement(double t) {
        if (t >= 0) {
            outputData.add(new Complex(sqrt(t),0 ));
            outputData.add(new Complex((-1.0)*sqrt(t),0 ));
        } else {
            outputData.add(new Complex( 0 , sqrt(-1.0* t) ));
            outputData.add(new Complex(0 ,(-1.0)*sqrt(-1.0* t) ));
        }
    }

    @Override
    public void printResult() {
        System.out.println(String.format("\nDelta=%.2f\n", delta));
        double pdelta = sqrt(abs(delta));
        System.out.print(String.format("Pdelta=%.2f\n", pdelta));

        if (outputData.elementAt(0).getReal() == 0) {
            System.out.print(String.format("%.2f", outputData.elementAt(0).getReal()));
            if (outputData.elementAt(0).getImaginary() == 0) {
                if (outputData.elementAt(0).getImaginary() > 0) {
                    System.out.print(String.format("+%.2fi", outputData.elementAt(0).getImaginary()));
                } else if (outputData.elementAt(0).getImaginary() < 0) {
                    System.out.print(String.format("+(%.2f)i", outputData.elementAt(0).getImaginary()));
                }
            }
        } else {
            if (outputData.elementAt(0).getImaginary() == 0) {
                if (outputData.elementAt(0).getImaginary() > 0) {
                    System.out.print(String.format("%.2fi", outputData.elementAt(0).getImaginary()));
                } else if (outputData.elementAt(0).getImaginary() < 0) {
                    System.out.print(String.format("(%.2f)i", outputData.elementAt(0).getImaginary()));
                }
            }
        }
        System.out.print(String.format("\n"));
        if (outputData.elementAt(1).getReal() == 0) {
            System.out.print(String.format("%.2f", outputData.elementAt(1).getReal()));
            if (outputData.elementAt(1).getImaginary() == 0) {
                if (outputData.elementAt(1).getImaginary() > 0) {
                    System.out.print(String.format("+%.2fi", outputData.elementAt(1).getImaginary()));
                } else if (outputData.elementAt(1).getImaginary() < 0) {
                    System.out.print(String.format("+(%.2f)i", outputData.elementAt(1).getImaginary()));
                }
            }
        } else {
            if (outputData.elementAt(1).getImaginary() == 0) {
                if (outputData.elementAt(1).getImaginary() > 0) {
                    System.out.print(String.format("%.2fi", outputData.elementAt(1).getImaginary()));
                } else if (outputData.elementAt(1).getImaginary() < 0) {
                    System.out.print(String.format("(%.2f)i", outputData.elementAt(1).getImaginary()));
                }
            }
        }
        System.out.print(String.format("\n"));
        if (outputData.elementAt(2).getReal() == 0) {
            System.out.print(String.format("%.2f", outputData.elementAt(2).getReal()));
            if (outputData.elementAt(2).getImaginary() == 0) {
                if (outputData.elementAt(2).getImaginary() > 0) {
                    System.out.print(String.format("+%.2fi", outputData.elementAt(2).getImaginary()));
                }
                else if (outputData.elementAt(2).getImaginary() < 0) {
                    System.out.print(String.format("+(%.2f)i", outputData.elementAt(2).getImaginary()));
                }
            }
        } else {
            if (outputData.elementAt(2).getImaginary() == 0) {
                if (outputData.elementAt(2).getImaginary() > 0) {
                    System.out.print(String.format("%.2fi", outputData.elementAt(2).getImaginary()));
                } else if (outputData.elementAt(2).getImaginary() < 0) {
                    System.out.print(String.format("(%.2f)i", outputData.elementAt(2).getImaginary()));
                }
            }
        }
        System.out.print(String.format("\n"));
        if (outputData.elementAt(3).getReal() == 0) {
            System.out.print(String.format("%.2f", outputData.elementAt(3).getReal()));
            if (outputData.elementAt(3).getImaginary() == 0) {
                if (outputData.elementAt(3).getImaginary() > 0) {
                    System.out.print(String.format("+%.2fi", outputData.elementAt(3).getImaginary()));
                }
                else
                if (outputData.elementAt(3).getImaginary() < 0) {
                    System.out.print(String.format("+(%.2f)i", outputData.elementAt(3).getImaginary()));
                }
            }
        } else {
            if (outputData.elementAt(3).getImaginary() == 0) {
                if (outputData.elementAt(3).getImaginary() > 0) {
                    System.out.print(String.format("%.2fi", outputData.elementAt(3).getImaginary()));
                } else if (outputData.elementAt(3).getImaginary() < 0) {
                    System.out.print(String.format("(%.2f)i", outputData.elementAt(3).getImaginary()));
                }
            }
        }
        System.out.print(String.format("\n"));
        System.out.print(String.format("\nSr=%.2f\n", sr));
        System.out.print(String.format("Rr=%.2f\n", rr));
        //soS)tring.format\nSr=%.2f\n", sr);
        //soS)tring.formatRr=%.2f\n", rr);
        if (addEquationRoot()) {
            System.out.println(String.format("\nSu=%.2f\n", su));
        }
        if (minusEquationRoot()) {
            System.out.println(String.format("Ru=%.2f\n", ru));
        }

        Vector<Double> v2 = new Vector<>();
        Double v2Element1 = 1.0;
        Double v2Element2 = 1.0;
        v2.add(1.0);
        v2.add(1.0);

        for (int i = 0; i < outputData.size(); i++) {
            v2Element1 *= outputData.elementAt(i).getReal();
            v2.setElementAt(v2Element1, 0);
            v2Element2 *= outputData.elementAt(i).getImaginary();
            v2.setElementAt(v2Element2, 0);
        }
        System.out.println("Iloczyn liczb zespolonych: "+v2.firstElement() +" " +v2.lastElement());
    }

    @Override
    public double getErr() {
        return err;
    }

    @Override
    public double getDelta() {
        return delta;
    }

    @Override
    public boolean addEquationRoot() {
        return false;
    }

    @Override
    public boolean minusEquationRoot() {
        return false;
    }

    public static void main(String... args) {
        SqrtMethod sqrtMethod = new SqrtMethod();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter err");
        sqrtMethod.setErr(scanner.nextDouble());

        sqrtMethod.setTab();
        sqrtMethod.printEquation();
        sqrtMethod.delta = sqrtMethod.calculateDelta();
        sqrtMethod.calculateRoots();
        sqrtMethod.printResult();

    }
}
