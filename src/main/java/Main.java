import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        boolean isReplay = true;
        Game game = new Game();
        game.welcome();
        Better player1 = new Better(100);
        player1.getBalance();
        while (isReplay) {

            //creating fresh deck and shuffling, and setting player's bet and hand.
            Deck newDeck = new Deck();
            newDeck.shuffle();
            player1.setBet();
            List<Card> player1Hand = newDeck.getCards(2);
            player1.setCards(player1Hand);

            //Creating dealer and showing one card face up.
            List<Card> dealerHand = newDeck.getCards(1);
            Player dealer = new Player();
            dealer.setCards(dealerHand);
            System.out.println("\nDealer's face up card is:");
            dealer.showCards();
            dealerHand = newDeck.getCards(1);
            dealer.setCards(dealerHand);

            //Letting the player stick or twist
            int player1Total = game.playerPlay(player1, newDeck);

            //Letting the dealer stick or twist, and then determining whether the player won or lost.
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
