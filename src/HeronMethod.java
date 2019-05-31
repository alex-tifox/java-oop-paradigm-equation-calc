//import com.vm.jcomplex.Complex;
//
//import java.util.Vector;
//
//import static java.lang.Math.abs;
//
//public class HeronMethod extends Equation implements ResultPrint<HeronMethod> {
//    double err, delta, pdelta, x1r, x2r, x3r, x4r, x1u, x2u, x3u, x4u, su, sr, rr, ru, mr, mu;
//
//    double heronSqrt(double d, double err) {
//        if (d < 0) {
//            d = d * (-1);
//        }
//        if (d == 0)
//            return 0;
//
//        double oldVal = 1.0;
//        double newVal = 0.5*(1 + d);
//        double a;
//        for (a = abs(newVal - oldVal); a > err; a = abs(newVal - oldVal)) {
//            oldVal = newVal;
//            newVal = 0.5*(newVal + d / newVal);
//        }
//
//        return newVal;
//    }
//    @Override
//    public void setErr(double err) {
//        this.err = err;
//    }
//
//    @Override
//    public void calculateRoots() {
//        double pdelta;
//        double t1, t2, t1r, t2r, t1u, t2u;
//
//
//        double err = GetErr();
//
//
//        pdelta = SqrtHeron(abs(delta), err);
//
//        if (inputData.at(0) == 0)
//        {
//            delta = 0 - 4 * inputData[0] * inputData[2];
//            pdelta = SqrtHeron(abs(delta), err);
//            if (delta < 0)
//            {
//                outputData.push_back({ (1.0)*(-1) * 0 / (2 * inputData[1]),(-1.0)*pdelta / ((1.0) * (2 * inputData[1])) });
//                outputData.push_back({ (1.0)*(-1) * 0 / (2 * inputData[1]),pdelta / ((1.0) * (2 * inputData[1])) });
//            }
//            else if (delta > 0)
//            {
//                outputData.push_back({ inputData[0] - pdelta / (2.0* inputData[1]),0 });
//                outputData.push_back({ inputData[0] + pdelta / (2.0* inputData[1]),0 });
//            }
//            else
//            {
//                outputData.push_back({ inputData[0] + 1e-5 / (2 * inputData[1]),0 });
//            }
//        }
//        else
//        {
//
//            if (delta > 0)
//            {
//
//                t1 = ((-1.0)* inputData[1] - pdelta) / (2.0 *  inputData[0]);
//                t2 = ((-1.0)* inputData[1] + pdelta) / (2.0 *  inputData[0]);
//
//                if (t1 >= 0)
//                {
//
//                    outputData.push_back({ SqrtHeron(t1,err),0 });
//                    outputData.push_back({ (-1.0)*SqrtHeron(t1,err),0 });
//                }
//                else
//                {
//
//                    outputData.push_back({ 0 ,SqrtHeron(-1.0*t1,err) });
//                    outputData.push_back({ 0 ,(-1.0)*SqrtHeron(-1.0*t1,err) });
//                }
//                if (t2 >= 0)
//                {
//                    outputData.push_back({ SqrtHeron(t2,err),0 });
//                    outputData.push_back({ (-1.0)*SqrtHeron(t2,err),0 });
//                }
//                else
//                {
//                    outputData.push_back({ 0 , SqrtHeron(-1.0*t2,err) });
//                    outputData.push_back({ 0 ,(-1.0)*SqrtHeron(-1.0*t2,err) });
//                }
//
//            }
//            else if (delta < 0)
//            {
//
//                t1r = ((-1.0)* inputData[1]) / (2.0 *  inputData[0]);
//                t2r = t1r;
//
//                t1u = (-1.0*pdelta) / (2 * inputData[0]);
//                t2u = (-1.0)*t1u;
//
//
//                outputData.push_back({ SqrtHeron((SqrtHeron((pow(t1r, 2) + pow(t1u, 2)),err) + t1r) / 2,err) ,SqrtHeron((SqrtHeron(pow(t1r, 2) + pow(t1u, 2),err) - t1r) / 2,err) });
//
//                outputData.push_back({ (-1.0)*SqrtHeron((SqrtHeron((pow(t1r, 2) + pow(t1u, 2)),err) + t1r) / 2,err) ,SqrtHeron((SqrtHeron(pow(t1r, 2) + pow(t1u, 2),err) - t1r) / 2,err) });
//
//                outputData.push_back({ SqrtHeron((SqrtHeron(pow(t2r, 2) + pow(t2u, 2),err) + t2r) / 2,err) ,(-1.0)*SqrtHeron((SqrtHeron(pow(t2r, 2) + pow(t2u, 2),err) - t2r) / 2,err) });
//
//                outputData.push_back({ (-1.0)*SqrtHeron((SqrtHeron(pow(t2r, 2) + pow(t2u, 2),err) + t2r) / 2,err) ,(-1.0)*SqrtHeron((SqrtHeron(pow(t2r, 2) + pow(t2u, 2),err) - t2r) / 2,err) });
//
//
//            }
//            else
//            {
//
//                t1r = ((-1.0)*inputData[1]) / (2.0 * inputData[0]);
//                if (t1r >= 0)
//                {
//                    outputData.push_back({ SqrtHeron(t1r,err),0 });
//                    //x2r = (-1.0)*sqrt(t1r);
//                    outputData.push_back({ (-1.0)*SqrtHeron(t1r,err),0 });
//                    //x3r = sqrt(t1r);
//                    outputData.push_back({ SqrtHeron(t1r,err),0 });
//                    //x4r = (-1.0)*sqrt(t1r);
//                    outputData.push_back({ (-1.0)*SqrtHeron(t1r,err),0 });
//                }
//                else
//                {
//                    outputData.push_back({ 0 , SqrtHeron(-1.0*t1r,err) });
//
//                    outputData.push_back({ 0 , (-1.0)*SqrtHeron(-1.0*t1r,err) });
//
//                    outputData.push_back({ 0 , SqrtHeron(-1.0*t1r,err) });
//
//                    outputData.push_back({ 0 , (-1.0)*SqrtHeron(-1.0*t1r,err) });
//                }
//
//            }
//
//        }
//    }
//
//    @Override
//    public double getErr() {
//        return err;
//    }
//
//    @Override
//    public double getDelta() {
//        return delta;
//    }
//
//    @Override
//    public double getSr() {
//        return sr;
//    }
//
//    @Override
//    public double getRr() {
//        return rr;
//    }
//
//    @Override
//    public double getSu() {
//        return su;
//    }
//
//    @Override
//    public double getRu() {
//        return ru;
//    }
//
//    @Override
//    public void setSr(double sr) {
//        this.sr = sr;
//    }
//
//    @Override
//    public void setRr(double rr) {
//        this.rr = rr;
//    }
//
//    @Override
//    public void setSu(double su) {
//        this.su = su;
//    }
//
//    @Override
//    public void setRu(double ru) {
//        this.ru = ru;
//    }
//}
