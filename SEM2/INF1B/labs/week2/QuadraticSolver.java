public class QuadraticSolver {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Expected 3 arguments: a b c");
      return;
    }
    double a = Double.parseDouble(args[0]);
    double b = Double.parseDouble(args[1]);
    double c = Double.parseDouble(args[2]);
    double r = b*b - 4*a*c;
    if (r < 0) {
      return;
    } else if (r == 0) {
      System.out.println(-b / (2*a));
    } else {
      System.out.println((-b + Math.sqrt(r)) / (2*a));
      System.out.println((-b - Math.sqrt(r)) / (2*a));
    }
  }
}