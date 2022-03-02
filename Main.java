public class Main {

  private static int JANKEN_COUNT = 10;

  public static void main(String[] args) {
    Player player1 = new Player("Taro");
    Player player2 = new Player("Hanako");
    player1.setStrategy(new AdjustStrategy());

    int player1win = 0;
    int player2win = 0;

    for (int i=0; i<JANKEN_COUNT; i++) {
      JankenHand hand1 = player1.nextHand();
      JankenHand hand2 = player2.nextHand();

      String result = hand1.winTo(hand2) ? 
                        player1.getName() + "の勝利" : 
                      hand1.loseTo(hand2) ? 
                        player2.getName() + "の勝利" : "あいこ";
      System.out.println(
        String.format("%s: %s - %s :%s (%s)", 
          player1.getName(), hand1, 
          hand2, player2.getName(),
          result
        )
      );
      if (hand1.winTo(hand2)) {
        player1win++;
      }
      if (hand2.winTo(hand1)) {
        player2win++;
      }
      player1.prevHands(hand1, hand2);
      player2.prevHands(hand2, hand1);
    }
    String finalResult = player1win > player2win ?
                           player1.getName() + "の勝利" : 
                         player2win > player1win ?
                           player2.getName() + "の勝利" : "引き分け";
    System.out.println(
      String.format("%s!!!(%s: %d - %d :%s)", 
        finalResult,
        player1.getName(), player1win, 
        player2win, player2.getName()
      )
    );
  }
}
