
public class ScrableWinner {

  public static int evaluate(String word) {
    char[] cs = word.toCharArray();
    int val = 0;
    for (int i = 0; i < cs.length; i++) {
      val += scoreOf(cs[i]);
    }
    return val;
  }

  private static int[] scores = new int[] { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4,
      10 };

  private static int scoreOf(char c) {
    c = Character.toLowerCase(c);
    return scores[c - 'a'];
  }
}