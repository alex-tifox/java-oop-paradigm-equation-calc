import com.vm.jcomplex.Complex;

import java.util.Scanner;
import java.util.Vector;

public abstract class Equation {
    protected Vector<Integer> inputData = new Vector<>();
    protected Vector<Complex> outputData = new Vector<>();

    protected Equation(){}

    public void printEquation(){
        if  (inputData.elementAt(0) != 0) {
            System.out.print(inputData.elementAt(0)+"x^4");
            System.out.print(inputData.elementAt(1) >= 0 ? "+" + inputData.elementAt(1)+"x^2" :
                    "+(" + inputData.elementAt(1)+")x^2");
            System.out.print(inputData.elementAt(2) >= 0 ? "+" + inputData.elementAt(2) :
                    "+(" + inputData.elementAt(2)+")");
        } else if (inputData.elementAt(0) == 0) {
            if (inputData.elementAt(1) != 0) {
                System.out.print(inputData.elementAt(1) + "x^2");
                System.out.print(inputData.elementAt(2) >= 0 ? "+" + inputData.elementAt(2) :
                        "+(" + inputData.elementAt(2) + ")");
            } else {
                System.out.print(inputData.elementAt(2) != 0 ? inputData.elementAt(2) : "");
            }
        } else if (inputData.elementAt(0) == 0 &&
                inputData.elementAt(1) == 0 &&
                inputData.elementAt(2) == 0) {
            System.out.println(0);
        }
    }

    public void setTab() {
        Scanner scanner = new Scanner(System.in);
        int inputValue;
        System.out.println("Enter a,\nb,\nc,\n");

        for (int i = 0; i < 3; i++) {
            inputValue = scanner.nextInt();
            inputData.add(inputValue);
        }
    }

    public double calculateDelta() {
        return inputData.elementAt(1) * inputData.elementAt(1) - 4 * inputData.elementAt(0) * inputData.elementAt(2);
    }

    public abstract void setErr(double err);

    public abstract void calculateRoots();
    public abstract void printResult();

    public abstract double getErr();
    public abstract double getDelta();

    public double[] addEquationRoot() {
        boolean flag = false;
        int sr = 0;
        int su = 0;

        for (Complex element : outputData) {
            if (element.getReal() != 0)
                sr += element.getReal();
            if (element.getImaginary() != 0) {
                su += element.getImaginary();
                flag = true;
            }
        }

        if (flag)
            return new double[]{sr, su, 1};
        else
            return new double[]{sr, su, 0};
    }

    public double[] minusEquationRoot() {
        boolean flag = false;
        int rr = 0;
        int ru = 0;
        for (Complex element : outputData) {
            if (element.getReal() != 0)
                rr -= element.getReal();
            if (element.getImaginary() != 0) {
                ru -= element.getImaginary();
                flag = true;
            }
        }

        if (flag)
            return new double[]{rr, ru, 1};
        else
            return new double[]{rr, ru, 0};
    }
}
