package leet;

public class Power {
    public static void main(String[] args) {
        myPow_b(2.00000, 10);
    }

    public static double myPow(double x, int n) {
        long newPow = n;
        newPow = newPow < 0 ? (-1 * newPow) : newPow;
        double res = 1;
        while (newPow > 0) {
            if (newPow % 2 == 1) {
                res = x * res;
                newPow--;
            } else {
                x = x * x;
                newPow = newPow / 2;
            }
        }
        if (n < 0) {
            res = (double) 1.0 / (double) res;
        }
        return res;
    }

    public static double myPow_b(double x, int n) {
        double mul = 1.0;
        if (n < 0) {
            mul = (double) (1.0) / pow(x, Math.abs(n));
        } else {
            mul = pow(x, n);
        }

        return mul;
    }

    // 2 pow 5 * 2 pow 5
    // 2 * 2 pow 2 * 2 pow 2
    //2 pow 1 * 2 pow 1
    // 2 * 2 pow 0 * 2 pow 0
    static double pow(double p, int q) {
        if (q == 0) return 1;
        double smallPow = pow(p, q / 2);
        if (q % 2 == 0) {
            System.out.println(smallPow + " * " + smallPow);
            return smallPow * smallPow;
        }
        System.out.println(p + " * " + smallPow + " * " + smallPow);
        return p * smallPow * smallPow;
        // return x*pow(double x, int n-1)
        // if(n==0) return 1;
        // return x*pow(x,n-1);
    }

}
