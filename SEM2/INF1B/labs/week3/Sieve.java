public class Sieve {
  public static void main(final String[] args) {
    if (args.length != 1) {
      System.out.println("Expected 1 argument: n");
      return;
    }
    final int n = Integer.parseInt(args[0]);
    final boolean[] nums = new boolean[n + 1];
    for (int i = 2; i < n; i++) {
      nums[i] = true;
    }
    for (int i = 2; i < n; i++) {
      if (!(nums[i])) {
        continue;
      }
      for (int j = 2*i; j < n; j += i) {
        nums[j] = false;
      }
    }
    for (int i = 2; i < n; i++) {
      if (nums[i]) {
        System.out.print(i);
        System.out.print(" ");
      }
    }
    System.out.println();
  }
}