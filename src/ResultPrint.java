import com.vm.jcomplex.Complex;

import java.util.Vector;

import static java.lang.Math.abs;

public interface ResultPrint<T extends Equation> {

    default void printResult(T equationMethod) {
        System.out.print(String.format("\nDelta=%.2f\n", equationMethod.getDelta()));
        double pdelta = equationMethod.getPdelta();
        System.out.print(String.format("Pdelta=%.2f\n", pdelta));
        if (equationMethod.outputData.elementAt(0).getReal() != 0) {
            System.out.print(String.format("%.2f", equationMethod.outputData.elementAt(0).getReal()));
            if (equationMethod.outputData.elementAt(0).getImaginary() != 0) {
                if (equationMethod.outputData.elementAt(0).getImaginary() > 0) {
                    System.out.print(String.format("+%.2fi", equationMethod.outputData.elementAt(0).getImaginary()));
                } else if (equationMethod.outputData.elementAt(0).getImaginary() < 0) {
                    System.out.print(String.format("+(%.2f)i", equationMethod.outputData.elementAt(0).getImaginary()));
                }
            }
        } else {
            if (equationMethod.outputData.elementAt(0).getImaginary() != 0) {
                if (equationMethod.outputData.elementAt(0).getImaginary() > 0) {
                    System.out.print(String.format("%.2fi", equationMethod.outputData.elementAt(0).getImaginary()));
                } else if (equationMethod.outputData.elementAt(0).getImaginary() < 0) {
                    System.out.print(String.format("(%.2f)i", equationMethod.outputData.elementAt(0).getImaginary()));
                }
            }
        }
        System.out.print("\n");
        try {
            if (equationMethod.outputData.elementAt(1).getReal() != 0) {
                System.out.print(String.format("%.2f", equationMethod.outputData.elementAt(1).getReal()));
                if (equationMethod.outputData.elementAt(1).getImaginary() != 0) {
                    if (equationMethod.outputData.elementAt(1).getImaginary() > 0) {
                        System.out.print(String.format("+%.2fi", equationMethod.outputData.elementAt(1).getImaginary()));
                    } else if (equationMethod.outputData.elementAt(1).getImaginary() < 0) {
                        System.out.print(String.format("+(%.2f)i", equationMethod.outputData.elementAt(1).getImaginary()));
                    }
                }
            } else {
                if (equationMethod.outputData.elementAt(1).getImaginary() != 0) {
                    if (equationMethod.outputData.elementAt(1).getImaginary() > 0) {
                        System.out.print(String.format("%.2fi", equationMethod.outputData.elementAt(1).getImaginary()));
                    } else if (equationMethod.outputData.elementAt(1).getImaginary() < 0) {
                        System.out.print(String.format("(%.2f)i", equationMethod.outputData.elementAt(1).getImaginary()));
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        System.out.print("\n");
        try {
            if (equationMethod.outputData.elementAt(2).getReal() != 0) {
                System.out.print(String.format("%.2f", equationMethod.outputData.elementAt(2).getReal()));
                if (equationMethod.outputData.elementAt(2).getImaginary() != 0) {
                    if (equationMethod.outputData.elementAt(2).getImaginary() > 0) {
                        System.out.print(String.format("+%.2fi", equationMethod.outputData.elementAt(2).getImaginary()));
                    } else if (equationMethod.outputData.elementAt(2).getImaginary() < 0) {
                        System.out.print(String.format("+(%.2f)i", equationMethod.outputData.elementAt(2).getImaginary()));
                    }
                }
            } else {
                if (equationMethod.outputData.elementAt(2).getImaginary() != 0) {
                    if (equationMethod.outputData.elementAt(2).getImaginary() > 0) {
                        System.out.print(String.format("%.2fi", equationMethod.outputData.elementAt(2).getImaginary()));
                    } else if (equationMethod.outputData.elementAt(2).getImaginary() < 0) {
                        System.out.print(String.format("(%.2f)i", equationMethod.outputData.elementAt(2).getImaginary()));
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}

        System.out.print("\n");
        try {
            if (equationMethod.outputData.elementAt(3).getReal() != 0) {
                System.out.print(String.format("%.2f", equationMethod.outputData.elementAt(3).getReal()));
                if (equationMethod.outputData.elementAt(3).getImaginary() != 0) {
                    if (equationMethod.outputData.elementAt(3).getImaginary() > 0) {
                        System.out.print(String.format("+%.2fi", equationMethod.outputData.elementAt(3).getImaginary()));
                    } else if (equationMethod.outputData.elementAt(3).getImaginary() < 0) {
                        System.out.print(String.format("+(%.2f)i", equationMethod.outputData.elementAt(3).getImaginary()));
                    }
                }
            } else {
                if (equationMethod.outputData.elementAt(3).getImaginary() != 0) {
                    if (equationMethod.outputData.elementAt(3).getImaginary() > 0) {
                        System.out.print(String.format("%.2fi", equationMethod.outputData.elementAt(3).getImaginary()));
                    } else if (equationMethod.outputData.elementAt(3).getImaginary() < 0) {
                        System.out.print(String.format("(%.2f)i", equationMethod.outputData.elementAt(3).getImaginary()));
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        equationMethod.setSr(equationMethod.addEquationRoot()[0]);
        equationMethod.setSu(equationMethod.addEquationRoot()[1]);
        equationMethod.setRr(equationMethod.minusEquationRoot()[0]);
        equationMethod.setRu(equationMethod.minusEquationRoot()[1]);

        System.out.print(String.format("\nSr=%.2f\n", equationMethod.getSr()));
        System.out.print(String.format("Rr=%.2f\n", equationMethod.getRr()));

        if (equationMethod.addEquationRoot()[2] == 1) {
            System.out.print(String.format("\nSu=%.2f\n", equationMethod.getSu()));
        }
        if (equationMethod.minusEquationRoot()[2] == 0) {
            System.out.print(String.format("Ru=%.2f\n", equationMethod.getRu()));
        }

        Vector<Double> v2 = new Vector<>();
        Double v2Element1 = 1.0;
        Double v2Element2 = 1.0;
        v2.add(1.0);
        v2.add(1.0);

        for (int i = 0; i < equationMethod.outputData.size(); i++) {
            v2Element1 *= equationMethod.outputData.elementAt(i).getReal();
            v2.setElementAt(v2Element1, 0);
            v2Element2 *= equationMethod.outputData.elementAt(i).getImaginary();
            v2.setElementAt(v2Element2, 0);
        }

        for (Complex data : equationMethod.outputData) {
            System.out.println(data.toString());
        }
        System.out.println("Iloczyn liczb zespolonych: "+v2.firstElement() +" " +v2.lastElement());
    }
}
