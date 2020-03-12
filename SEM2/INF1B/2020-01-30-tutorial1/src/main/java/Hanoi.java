
public class Hanoi {
  private int count;
  private int[] stacks;
  private int movesDone;

  public Hanoi(int count) {
    this.count = count;
    this.stacks = new int[] { count, 0, 0 };
    this.movesDone = 0;
  }

  public int howMany() {
    if (this.movesDone == 0) {
      this.move(0, 1, 2, this.count);
    }
    return this.movesDone;
  }

  private void move(int a, int b, int c, int n) {
    if (n < 1) {
      return;
    }
    this.move(a, c, b, n - 1);
    this.movesDone++;
    this.stacks[a]--;
    this.stacks[b]++;
    this.move(c, b, a, n - 1);
  }
}