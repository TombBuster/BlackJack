import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        boolean isReplay = true;
        Game game = new Game();
        game.welcome();
        while (isReplay) {
            Deck newDeck = new Deck();
            newDeck.shuffle();
            List<Card> player1Hand = newDeck.getCards(2);
            Player player1 = new Player(player1Hand);
            List<Card> dealerHand = newDeck.getCards(2);
            Player dealer = new Player(dealerHand);
            int player1Total = game.playerPlay(player1, newDeck);
            game.result(player1Total, dealer, newDeck);
            isReplay = game.isReplay();
        }
    }
}
