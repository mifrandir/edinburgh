public class PolarCoordinates {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Expected to arguments: x y");
      return;
    }
    double x, y;
    x = Double.parseDouble(args[0]);
    y = Double.parseDouble(args[1]);
    double r, t;
    t = Math.atan2(y, x);
    r = Math.sqrt(x*x + y*y);
    System.out.println(r);
    System.out.println(t);
  }
}