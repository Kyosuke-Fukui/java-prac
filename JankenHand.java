// ENUM型（数え上げることのできる有限の集合を表すのに適したデータ型）
public enum JankenHand {
  Rock,
  Scissors,
  Paper;

  //決まったメソッドを使う場合、overrideを使う。
  @Override
  public String toString() {
    switch (this) {
      case Rock: return "グー";
      case Scissors: return "チョキ";
      case Paper: return "パー";
    }
    throw new IllegalStateException();
  }

  public static JankenHand fromInt(int n) {
    switch (n % 3) {
      case 0: return Rock;
      case 1: return Scissors;
      case 2: return Paper;
    }
    throw new IllegalArgumentException(Integer.toString(n));
  }

  public boolean winTo(JankenHand hand) {
    switch (this) {
      case Rock: return hand == Scissors;
      case Scissors: return hand == Paper;
      case Paper: return hand == Rock;
    }
    throw new IllegalStateException();
  }

  public boolean loseTo(JankenHand hand) {
    return this != hand && !winTo(hand);
  }
}
