public class ImQuadraticSolver {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Expected 3 arguments: a b c");
      return;
    }
    double a = Double.parseDouble(args[0]);
    double b = Double.parseDouble(args[1]);
    double c = Double.parseDouble(args[2]);
    double d = b*b - 4*a*c;
    if (d < 0) {
      double r, i;
      r = -b / (2*a);
      i = Math.sqrt(-d) / (2 * a);
      System.out.printf("%.1f + %.1fi\n", r, i);
      System.out.printf("%.1f - %.1fi\n", r, i);

    } else if (d == 0) {
      System.out.println(-b / (2*a));
    } else {
      System.out.println((-b + Math.sqrt(d)) / (2*a));
      System.out.println((-b - Math.sqrt(d)) / (2*a));
    }
  }
}