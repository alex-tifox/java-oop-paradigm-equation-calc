import com.vm.jcomplex.Complex;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class SqrtMethod extends Equation implements ResultPrint<SqrtMethod> {
    double err, delta, pdelta, x1r, x2r, x3r, x4r, x1u, x2u, x3u, x4u, su, sr, rr, ru, mr, mu;

    SqrtMethod(){}

    @Override
    public void calculateRoots() {

        double t1, t2, t1r, t2r, t1u, t2u;

        pdelta = sqrt(abs(delta));
        if (inputData.elementAt(0) == 0) {
            delta = 0 - 4 * inputData.elementAt(1) * inputData.elementAt(2);
            pdelta = sqrt(abs(delta));

            if (delta < 0) {
                outputData.add(new Complex(1.0 * (-1) * 0 / (2 * inputData.elementAt(1)),
                        (-1.0) * pdelta / ((1.0) * (2 * inputData.elementAt(1)))));
                outputData.add(new Complex((1.0) * (-1) * 0 / (2 * inputData.elementAt(1)),
                        pdelta / ((1.0) * (2 * inputData.elementAt(1)))));
            } else if (delta > 0) {
                outputData.add(new Complex(inputData.elementAt(0) - pdelta / (2.0 * inputData.elementAt(1)), 0.0));
                outputData.add(new Complex(inputData.elementAt(0) + pdelta / (2.0 * inputData.elementAt(1)), 0));
            } else {
                Complex complex = new Complex(inputData.elementAt(0) + 1e-5 / (2 * inputData.elementAt(1)), 0);
                outputData.add(complex);
            }
        }else{

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
                t1r = ((-1.0) * inputData.elementAt(1)) / (2.0 * inputData.elementAt(0));
                if (t1r >= 0) {
                    outputData.add(new Complex(sqrt(t1r), 0));
                    outputData.add(new Complex((-1.0) * sqrt(t1r), 0));
                    outputData.add(new Complex(sqrt(t1r), 0));
                    outputData.add(new Complex((-1.0) * sqrt(t1r), 0));
                } else {
                    x1u = sqrt(-1.0 * t1r);
                    outputData.add(new Complex(0, sqrt(-1.0 * t1r)));

                    x2u = (-1.0) * sqrt(-1.0 * t1r);
                    outputData.add(new Complex(0, (-1.0) * sqrt(-1.0 * t1r)));

                    x3u = sqrt(-1.0 * t1r);
                    outputData.add(new Complex(0, sqrt(-1.0 * t1r)));

                    x4u = (-1.0) * sqrt(-1.0 * t1r);
                    outputData.add(new Complex(0, (-1.0) * sqrt(-1.0 * t1r)));
                }
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
    public void setErr(double err) {
        this.err = err;
    }

    public double getErr() {
        return err;
    }

    @Override
    public double getDelta() {
        return delta;
    }
    @Override
    public double getPdelta(){
        return pdelta;
    }
    @Override
    public double getSr() {
        return sr;
    }
    @Override
    public double getRr() {
        return rr;
    }
    @Override
    public double getSu() {
        return su;
    }
    @Override
    public double getRu() {
        return ru;
    }
    @Override
    public void setSr(double sr) {
        this.sr = sr;
    }
    @Override
    public void setRr(double rr) {
        this.rr = rr;
    }
    @Override
    public void setSu(double su) {
        this.su = su;
    }
    @Override
    public void setRu(double ru) {
        this.ru = ru;
    }
}
