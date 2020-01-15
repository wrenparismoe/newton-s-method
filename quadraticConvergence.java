package Lab3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.math.*;

public class quadraticConvergence {

    public static void main(String[] ars){
        String tol = "";
        Scanner in = new Scanner(System.in);

        ArrayList<Integer> fc = new ArrayList<>();
        ArrayList<Integer> fe = new ArrayList<>();

        System.out.println("Enter the number of elements in f(x)");
        int elements = in.nextInt();
        for(int i = 0; i < elements; i++){
            System.out.println("Enter the coefficient for element " + (i+1) + ": ");
            fc.add(in.nextInt());
            System.out.println("Enter the exponent for element " + (i+1) + ": ");
            fe.add(in.nextInt());
        }
        ArrayList<Integer> fpc = new ArrayList<>();
        ArrayList<Integer> fpe = new ArrayList<>();
        for(int i = 0; i < elements; i++){
            fpc.add(fc.get(i)*fe.get(i));
            if(fe.get(i) != 0){
                fpe.add(fe.get(i) - 1);
            } else {
                fpe.add(0);
            }
        }
        ArrayList<Integer> fppc = new ArrayList<>();
        ArrayList<Integer> fppe = new ArrayList<>();
        for(int i = 0; i < elements; i++){
            fppc.add(fpc.get(i)*fpe.get(i));
            if(fpe.get(i) != 0){
                fppe.add(fpe.get(i) - 1);
            } else {
                fppe.add(0);
            }
        }
        // Above creates f(x), f'(x), and f''(x)


        // INITIAL GUESS
        double x0 = -1;

        BigDecimal xn = BigDecimal.valueOf(x0);
        BigDecimal xn1;
        BigDecimal top, bot;
        BigDecimal E;

        for(int n = 1; n < 6000; n++) {
            System.out.println("n: " + n);
            top = BigDecimal.valueOf(0.0);
            bot = BigDecimal.valueOf(0.0);
            for (int i = 0; i < elements; i++) {
                top = top.add(xn.pow(fe.get(i)).multiply(BigDecimal.valueOf(fc.get(i))));
                bot = bot.add(xn.pow(fpe.get(i)).multiply(BigDecimal.valueOf(fpc.get(i))));
            }

            xn1 = xn.subtract(top.divide(bot, 10000, RoundingMode.FLOOR));

            System.out.println("X: " + xn1);

            E = xn1.subtract(xn).abs();
            xn = xn1;
            String errorString = E.toString();

            int length = errorString.length();
            if (length < 25) {
                System.out.println("Err:   " + E);
            } else {
                tol = errorString.substring(length - 5, length);
                errorString = errorString.substring(0, 10) + " ... "
                        + errorString.substring(length - 10, length);
                System.out.println("Err:   " + errorString);

            }

            try {
                int t = Integer.parseInt(tol);
                if (t <= -1000) {
                    System.out.println("DONE");
                    break;
                }
            } catch (NumberFormatException e) {
                continue;
            }
            System.out.println();

        }
    }
}
