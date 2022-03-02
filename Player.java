public class Player {

  private String name;
  private JankenStrategy strategy = new RandomStrategy();

  // Javaのコンストラクタはクラス名と同じ名前で定義
  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public JankenStrategy getStrategy() {
    return this.strategy;
  }

  public void setStrategy(JankenStrategy strategy) {
    this.strategy = strategy;
  }

  public void prevHands(JankenHand myHand, JankenHand opponentHand) {
    strategy.prevHands(myHand, opponentHand);
  }

  //JankenHandのデータを返すnextHand()という関数を定義
  public JankenHand nextHand() {
    return strategy.nextHand();
  }

}
