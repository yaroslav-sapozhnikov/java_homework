public class Seminar1 {

    public static void main(String[] args) {

        System.out.println("Hello world!");

        int x = 2;
        System.out.println(x);

        int y = 0;
        y = y + 1;
        y += 1;
        y = y++;
        System.out.println(y);

        int z = 1;
        int d = 2;
        int e = z;
        z = d;
        d = e;
        System.out.println("c = " + z + "; d = " + d + ";");

        double a = 5;
        double b = 4;
        double c = Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 0.5);
        System.out.println(c);

        int natural = 2373024;
        System.out.println(natural % 10);

        int number = 49134838;
        System.out.println(number / 10 % 10);

        int number2 = 93;
        System.out.println(number2 / 10);

        System.out.println(difference(9) + "; " + difference(21) + "; " + difference(30) + ";");

        System.out.println(arith_mean(2, 6));

        System.out.println(arith_mean(6, 8));

        System.out.println(distance(1, 2, 2, 3));

    }

    public static int difference (int number3){
        return 21 - number3;
    }

    public static int arith_mean(int num1, int num2){
        return (num1 + num2) / 2;
    }

    public static double geom_mean(double num1, double num2){
        return Math.pow(num1 * num2, 2);
    
    }

    public static double distance(double x1, double y1, double x2, double y2){
        double xd = x1 - x2;
        double yd = y1 - y2;
        double dist = Math.pow(Math.pow(xd, 2) + Math.pow(yd, 2), 0.5);
        return dist;

    }
}