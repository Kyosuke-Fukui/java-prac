import java.util.Random;

// インターフェースとは、クラスに含まれるメソッドの具体的な処理内容を記述せず、変数とメソッドの型のみを定義したもの
public interface JankenStrategy {

  public default void prevHands(JankenHand myHand, JankenHand opponentHand) {}

  public JankenHand nextHand();
}

// インターフェースの実装には、implementsを使う
class RandomStrategy implements JankenStrategy {

  private Random random = new Random();

  public JankenHand nextHand() {
    int n = random.nextInt(3);
    return JankenHand.fromInt(n);
  }
}

class FixedHandStrategy implements JankenStrategy {

  private JankenHand hand;

  public FixedHandStrategy(JankenHand hand) {
    this.hand = hand;
  }

  public JankenHand nextHand() {
    return this.hand;
  }
}

class AdjustStrategy implements JankenStrategy {

  private JankenHand prevMyHand;
  private JankenHand prevOpponentHand;

  @Override
  public void prevHands(JankenHand myHand, JankenHand opponentHand) {
    prevMyHand = myHand;
    prevOpponentHand = opponentHand;
  }

  public JankenHand nextHand() {
    // 初回は前回の手がないのでRandomStrategyを流用
    if (prevMyHand == null || prevOpponentHand == null) {
      return new RandomStrategy().nextHand();
    }
    if (prevMyHand.winTo(prevOpponentHand)) { // 自分が勝った場合、次の手を出す（グーなら次はチョキ）
      return JankenHand.fromInt(prevMyHand.ordinal() + 1); //ordinal()はEnum型の宣言された順番を取得するメソッド
    } else if (prevMyHand.loseTo(prevOpponentHand)) { // 相手が勝った場合、相手の手を出す
      return prevOpponentHand;
    } else {
      //あいこの場合、前と同じ手を出す
      return prevMyHand;
    }
  }
}
