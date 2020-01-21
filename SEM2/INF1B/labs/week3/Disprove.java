public class Disprove {
  public static void main(String[] args) {
    for (int i = 1; i < 100000; i++) {
      if (!isPrime(5*i*i+5*i+1)) {
        System.out.println(i);
        break;
      }
    }
  }

  public static boolean isPrime(final int n) {
    final boolean[] nums = new boolean[n + 1];
    for (int i = 2; i < n+1; i++) {
      nums[i] = true;
    }
    for (int i = 2; i < n; i++) {
      if (!(nums[i])) {
        continue;
      }
      for (int j = 2 * i; j < n; j += i) {
        nums[j] = false;
      }
    }
    return nums[n];
  }
}