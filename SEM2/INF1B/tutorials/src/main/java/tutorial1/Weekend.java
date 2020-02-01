package tutorial1;

public class Weekend {
  public static boolean isIt(String day, int delta) throws IllegalArgumentException {
    if (delta < 0) {
      throw new IllegalArgumentException("Received negative time difference.");
    }
    int index = dayToIndex(day);
    if ((index + delta) % 7 >= 5) {
      return true;
    }
    return false;
  }

  private static String[] days = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
      "Sunday" };

  private static int dayToIndex(String day) throws IllegalArgumentException {
    for (int i = 0; i < days.length; i++) {
      if (days[i] == day) {
        return i;
      }
    }
    throw new IllegalArgumentException("Given argument is not a weekday.");
  }
}