import java.util.Scanner;

public class Main {

    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter err");
        double err = scanner.nextDouble();
        if (err == 0) {
            SqrtMethod sqrtMethod = new SqrtMethod();
            sqrtMethod.setErr(err);
            sqrtMethod.setTab();
            sqrtMethod.delta = sqrtMethod.calculateDelta();
            sqrtMethod.printEquation();
            sqrtMethod.calculateRoots();
            sqrtMethod.printResult(sqrtMethod);
        } else if (err > 0.1) {
            NewtonMethod newtonMethod = new NewtonMethod();
            newtonMethod.setErr(err);
            newtonMethod.setTab();
            newtonMethod.delta = newtonMethod.calculateDelta();
            newtonMethod.printEquation();
            newtonMethod.calculateRoots();
            newtonMethod.printResult(newtonMethod);
        } else {
            HeronMethod heronMethod = new HeronMethod();
            heronMethod.setErr(err);
            heronMethod.setTab();
            heronMethod.delta = heronMethod.calculateDelta();
            heronMethod.printEquation();
            heronMethod.calculateRoots();
            heronMethod.printResult(heronMethod);
        }
    }
}
