import java.util.List;

public class Main {

    public static void main(String[] args) {
        boolean isReplay = true;
        Game game = new Game();
        game.welcome();
        Better player1 = new Better(100);
        while (isReplay) {
            Deck newDeck = new Deck();
            newDeck.shuffle();
            player1.setBet();
            List<Card> player1Hand = newDeck.getCards(2);
            player1.setCards(player1Hand);
            List<Card> dealerHand = newDeck.getCards(2);
            Player dealer = new Player();
            dealer.setCards(dealerHand);
            int player1Total = game.playerPlay(player1, newDeck);
            int result = game.result(player1Total, dealer, newDeck);
            player1.addToBalance(result);
            int balance = player1.getBalance();
            if (balance == 0) {
                System.out.println("You've run out of money!");
                isReplay = false;
            } else {
                isReplay = game.isReplay();
            }
        }
        player1.finalBalance(100);
    }
}
