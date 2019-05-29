import com.vm.jcomplex.Complex;

import java.util.Vector;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class NewtonMethod extends Equation {
     double err, delta, pdelta, x1r, x2r, x3r, x4r, x1u, x2u, x3u, x4u, su, sr, rr, ru, mr, mu;

     private double newtonSqrt(double d, double err) {

        if (d < 0)
            d = d * (-1);

        if (d == 0)
            return 0;

        double squareRoot = 1.;
        double b = d;

        double a;
        for (a = abs(squareRoot - b); a >= err; a = abs(squareRoot - b)) {
            squareRoot = (squareRoot + b) / 2.;
            b = d / squareRoot;
        }
        return squareRoot;
    }

    @Override
    public void setErr(double err) {
        this.err = err;
    }

    @Override
    public void calculateRoots() {
        double t1, t2, t1r, t2r, t1u, t2u;

        double err = getErr();

        pdelta = newtonSqrt(abs(delta), err);

        if (inputData.elementAt(0) == 0) {
            delta = 0 - 4 * inputData.elementAt(1)* inputData.elementAt(2);
            pdelta = newtonSqrt(abs(delta), err);
            if (delta < 0) {
                outputData.add(new Complex((1.0)*(-1) * 0 / (2 * inputData.elementAt(1)),(-1.0)*pdelta / ((1.0) * (2 * inputData.elementAt(1)))));
                outputData.add(new Complex((1.0)*(-1) * 0 / (2 * inputData.elementAt(1)),pdelta / ((1.0) * (2 * inputData.elementAt(1))) ));
            } else if (delta > 0) {
                outputData.add(new Complex(inputData.elementAt(0) - pdelta / (2.0* inputData.elementAt(1)),0 ));
                outputData.add(new Complex(inputData.elementAt(0) + pdelta / (2.0* inputData.elementAt(1)),0 ));
            } else {
                outputData.add(new Complex(inputData.elementAt(0)+ 1e-5 / (2 * inputData.elementAt(1)),0 ));
            }
        } else {

            if (delta > 0) {

                t1 = ((-1.0)* inputData.elementAt(1) - pdelta) / (2.0 *  inputData.elementAt(0));
                t2 = ((-1.0)* inputData.elementAt(1) + pdelta) / (2.0 *  inputData.elementAt(0));

                addReplacement(t1, err);
                addReplacement(t2, err);
            } else if (delta < 0) {
                t1r = ((-1.0)* inputData.elementAt(1)) / (2.0 *  inputData.elementAt(0));
                t2r = t1r;

                t1u = (-1.0*pdelta) / (2 * inputData.elementAt(0));
                t2u = (-1.0)*t1u;

                outputData.add(new Complex(newtonSqrt((newtonSqrt(pow(t1r, 2) + pow(t1u, 2),err) + t1r) / 2,err) ,newtonSqrt((newtonSqrt(pow(t1r, 2) + pow(t1u, 2),err) - t1r) / 2,err)));
                outputData.add(new Complex((-1.0)*newtonSqrt((newtonSqrt(pow(t1r, 2) + pow(t1u, 2),err) + t1r) / 2,err) ,newtonSqrt((newtonSqrt(pow(t1r, 2) + pow(t1u, 2),err) - t1r) / 2,err)));
                outputData.add(new Complex(newtonSqrt((newtonSqrt(pow(t2r, 2) + pow(t2u, 2),err) + t2r) / 2,err) ,(-1.0)*newtonSqrt((newtonSqrt(pow(t2r, 2) + pow(t2u, 2),err) - t2r) / 2,err)));
                outputData.add(new Complex((-1.0)*newtonSqrt((newtonSqrt(pow(t2r, 2) + pow(t2u, 2),err) + t2r) / 2,err) ,(-1.0)*newtonSqrt((newtonSqrt(pow(t2r, 2) + pow(t2u, 2),err) - t2r) / 2,err)));

            } else {

                t1r = ((-1.0)* inputData.elementAt(1)) / (2.0 *  inputData.elementAt(0));
                if (t1r >= 0) {

                    outputData.add(new Complex(newtonSqrt(t1r,err),0));
                    outputData.add(new Complex((-1.0)*newtonSqrt(t1r,err),0));
                    outputData.add(new Complex(newtonSqrt(t1r,err),0));
                    outputData.add(new Complex((-1.0)*newtonSqrt(t1r,err),0));
                } else {
                    x1u = newtonSqrt(-1.0*t1r, err);
                    outputData.add(new Complex( 0 , newtonSqrt(-1.0*t1r,err)));

                    x2u = (-1.0)*newtonSqrt(-1.0*t1r, err);
                    outputData.add(new Complex(0 , (-1.0)*newtonSqrt(-1.0*t1r,err)));

                    x3u = newtonSqrt(-1.0*t1r, err);
                    outputData.add(new Complex(0 , newtonSqrt(-1.0*t1r,err)));

                    x4u = (-1.0)*newtonSqrt(-1.0*t1r, err);
                    outputData.add(new Complex( 0 , (-1.0)*newtonSqrt(-1.0*t1r,err)));
                }
            }
        }
    }

    private void addReplacement(double t, double err) {
        if (t >= 0) {
            outputData.add(new Complex(newtonSqrt(t,err),0));
            outputData.add(new Complex((-1.0)*newtonSqrt(t,err),0));
        } else {
            outputData.add(new Complex(0 , newtonSqrt(-1.0* t,err) ));
            outputData.add(new Complex(0 ,(-1.0)*newtonSqrt(-1.0* t,err)));
        }
    }

    @Override
    public void printResult() {
        System.out.print("\nDelta=%.2f" + getDelta());
        double pdelta = (abs(getDelta()));
        System.out.print(String.format("Pdelta=%.2f", pdelta));
        if (outputData.elementAt(0).getReal() != 0) {
            System.out.print(String.format("%.2f", outputData.elementAt(0).getReal()));
            if (outputData.elementAt(0).getImaginary() != 0) {
                if (outputData.elementAt(0).getImaginary() > 0) {
                    System.out.print(String.format("+%.2fi", outputData.elementAt(0).getImaginary()));
                } else if (outputData.elementAt(0).getImaginary() < 0) {
                    System.out.print(String.format("+(%.2f)i", outputData.elementAt(0).getImaginary()));
                }
            }
        } else {
            if (outputData.elementAt(0).getImaginary() != 0) {
                if (outputData.elementAt(0).getImaginary() > 0) {
                    System.out.print(String.format("%.2fi", outputData.elementAt(0).getImaginary()));
                } else if (outputData.elementAt(0).getImaginary() < 0) {
                    System.out.print(String.format("(%.2f)i", outputData.elementAt(0).getImaginary()));
                }
            }
        }
        System.out.print("\n");
        if (outputData.elementAt(1).getReal() != 0) {
            System.out.print(String.format("%.2f", outputData.elementAt(1).getReal()));
            if (outputData.elementAt(1).getImaginary() != 0) {
                if (outputData.elementAt(1).getImaginary() > 0) {
                    System.out.print(String.format("+%.2fi", outputData.elementAt(1).getImaginary()));
                } else if (outputData.elementAt(1).getImaginary() < 0) {
                    System.out.print(String.format("+(%.2f)i", outputData.elementAt(1).getImaginary()));
                }
            }
        } else {
            if (outputData.elementAt(1).getImaginary() != 0) {
                if (outputData.elementAt(1).getImaginary() > 0) {
                    System.out.print(String.format("%.2fi", outputData.elementAt(1).getImaginary()));
                } else if (outputData.elementAt(1).getImaginary() < 0) {
                    System.out.print(String.format("(%.2f)i", outputData.elementAt(1).getImaginary()));
                }
            }
        }
        System.out.print("\n");
        if (outputData.elementAt(2).getReal() != 0) {
            System.out.print(String.format("%.2f", outputData.elementAt(2).getReal()));
            if (outputData.elementAt(2).getImaginary() != 0) {
                if (outputData.elementAt(2).getImaginary() > 0) {
                    System.out.print(String.format("+%.2fi", outputData.elementAt(2).getImaginary()));
                } else if (outputData.elementAt(2).getImaginary() < 0) {
                    System.out.print(String.format("+(%.2f)i", outputData.elementAt(2).getImaginary()));
                }
            }
        } else {
            if (outputData.elementAt(2).getImaginary() != 0) {
                if (outputData.elementAt(2).getImaginary() > 0) {
                    System.out.print(String.format("%.2fi", outputData.elementAt(2).getImaginary()));
                } else if (outputData.elementAt(2).getImaginary() < 0) {
                    System.out.print(String.format("(%.2f)i", outputData.elementAt(2).getImaginary()));
                }
            }
        }
        System.out.print("\n");
        if (outputData.elementAt(3).getReal() != 0) {
            System.out.print(String.format("%.2f", outputData.elementAt(3).getReal()));
            if (outputData.elementAt(3).getImaginary() != 0) {
                if (outputData.elementAt(3).getImaginary() > 0) {
                    System.out.print(String.format("+%.2fi", outputData.elementAt(3).getImaginary()));
                } else if (outputData.elementAt(3).getImaginary() < 0) {
                    System.out.print(String.format("+(%.2f)i", outputData.elementAt(3).getImaginary()));
                }
            }
        } else {
            if (outputData.elementAt(3).getImaginary() != 0) {
                if (outputData.elementAt(3).getImaginary() > 0) {
                    System.out.print(String.format("%.2fi", outputData.elementAt(3).getImaginary()));
                } else if (outputData.elementAt(3).getImaginary() < 0) {
                    System.out.print(String.format("(%.2f)i", outputData.elementAt(3).getImaginary()));
                }
            }
        }
        System.out.print("\n");
        System.out.print(String.format("\nSr=%.2f\n", sr));
        System.out.print(String.format("Rr=%.2f\n", rr));

        if (addEquationRoot()[2] == 1) {
            System.out.print(String.format("\nSu=%.2f\n", su));
        }
        if (minusEquationRoot()[2] == 0) {
            System.out.print(String.format("Ru=%.2f\n", ru));
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
        System.out.println("Iloczyn liczb zespolonych: " + v2.firstElement() + " " +v2.lastElement());
    }

    @Override
    public double getErr() {
        return err;
    }

    @Override
    public double getDelta() {
        return delta;
    }
}
