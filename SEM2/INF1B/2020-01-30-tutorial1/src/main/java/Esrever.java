
public class Esrever {
  public static String reverse(String s) {
    int l = s.length();
    char[] cs = new char[s.length()];
    for (int i = 0; i < s.length(); i++) {
      cs[i] = s.charAt(l - 1 - i);
    }
    return String.copyValueOf(cs);
  }
}