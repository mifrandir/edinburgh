public class Multiplier {
  public static void main(final String[] args) {
    int num = 1;
    for (int i = 0; i < args.length; i++) {
      num *= Integer.parseInt(args[i]);
    }
    System.out.println(num);
  }
}