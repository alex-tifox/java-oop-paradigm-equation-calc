import com.vm.jcomplex.Complex;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class HeronMethod extends Equation implements ResultPrint<HeronMethod> {
    double err, delta, pdelta, x1r, x2r, x3r, x4r, x1u, x2u, x3u, x4u, su, sr, rr, ru, mr, mu;

    double heronSqrt(double d, double err) {
        if (d < 0) {
            d = d * (-1);
        }
        if (d == 0)
            return 0;

        double oldVal = 1.0;
        double newVal = 0.5*(1 + d);
        double a;
        for (a = abs(newVal - oldVal); a > err; a = abs(newVal - oldVal)) {
            oldVal = newVal;
            newVal = 0.5*(newVal + d / newVal);
        }

        return newVal;
    }
    @Override
    public void setErr(double err) {
        this.err = err;
    }

    @Override
    public void calculateRoots() {
        double t1, t2, t1r, t2r, t1u, t2u;


        pdelta = heronSqrt(abs(delta), err);
        if (inputData.elementAt(0) == 0) {
            delta = 0 - 4 * inputData.elementAt(1)* inputData.elementAt(2);
            pdelta = heronSqrt(abs(delta), err);

            if (delta < 0) {
                outputData.add(new Complex((1.0)*(-1) * 0 / (2 * inputData.elementAt(1)),
                        (-1.0)*pdelta / ((1.0) * (2 * inputData.elementAt(1)))));
                outputData.add(new Complex((1.0)*(-1) * 0 / (2 * inputData.elementAt(1)),
                        pdelta / ((1.0) * (2 * inputData.elementAt(1))) ));
            } else if (delta > 0) {
                outputData.add(new Complex(inputData.elementAt(0) - pdelta / (2.0* inputData.elementAt(1)),
                        0 ));
                outputData.add(new Complex(inputData.elementAt(0) + pdelta / (2.0* inputData.elementAt(1)),
                        0 ));
            } else {
                outputData.add(new Complex(inputData.elementAt(0)+ 1e-5 / (2 * inputData.elementAt(1)),
                        0 ));
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

                outputData.add(new Complex(heronSqrt((heronSqrt(pow(t1r, 2) + pow(t1u, 2),err) + t1r) / 2,err) ,heronSqrt((heronSqrt(pow(t1r, 2) + pow(t1u, 2),err) - t1r) / 2,err)));
                outputData.add(new Complex((-1.0)*heronSqrt((heronSqrt(pow(t1r, 2) + pow(t1u, 2),err) + t1r) / 2,err) ,heronSqrt((heronSqrt(pow(t1r, 2) + pow(t1u, 2),err) - t1r) / 2,err)));
                outputData.add(new Complex(heronSqrt((heronSqrt(pow(t2r, 2) + pow(t2u, 2),err) + t2r) / 2,err) ,(-1.0)*heronSqrt((heronSqrt(pow(t2r, 2) + pow(t2u, 2),err) - t2r) / 2,err)));
                outputData.add(new Complex((-1.0)*heronSqrt((heronSqrt(pow(t2r, 2) + pow(t2u, 2),err) + t2r) / 2,err) ,(-1.0)*heronSqrt((heronSqrt(pow(t2r, 2) + pow(t2u, 2),err) - t2r) / 2,err)));

            } else {

                t1r = ((-1.0)* inputData.elementAt(1)) / (2.0 *  inputData.elementAt(0));
                if (t1r >= 0) {

                    outputData.add(new Complex(heronSqrt(t1r,err),0));
                    outputData.add(new Complex((-1.0)*heronSqrt(t1r,err),0));
                    outputData.add(new Complex(heronSqrt(t1r,err),0));
                    outputData.add(new Complex((-1.0)*heronSqrt(t1r,err),0));
                } else {
                    x1u = heronSqrt(-1.0*t1r, err);
                    outputData.add(new Complex( 0 , heronSqrt(-1.0*t1r,err)));

                    x2u = (-1.0)*heronSqrt(-1.0*t1r, err);
                    outputData.add(new Complex(0 , (-1.0)*heronSqrt(-1.0*t1r,err)));

                    x3u = heronSqrt(-1.0*t1r, err);
                    outputData.add(new Complex(0 , heronSqrt(-1.0*t1r,err)));

                    x4u = (-1.0)*heronSqrt(-1.0*t1r, err);
                    outputData.add(new Complex( 0 , (-1.0)*heronSqrt(-1.0*t1r,err)));
                }
            }
        }
    }

    private void addReplacement(double t, double err) {
        if (t >= 0) {
            outputData.add(new Complex(heronSqrt(t,err),0));
            outputData.add(new Complex((-1.0)*heronSqrt(t,err),0));
        } else {
            outputData.add(new Complex(0 , heronSqrt(-1.0* t,err) ));
            outputData.add(new Complex(0 ,(-1.0)*heronSqrt(-1.0* t,err)));
        }
    }

    public double getErr() {
        return err;
    }

    @Override
    public double getPdelta(){
        return pdelta;
    }

    @Override
    public double getDelta() {
        return delta;
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
