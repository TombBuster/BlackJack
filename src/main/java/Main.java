import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        boolean isReplay = true;
        Game game = new Game();
        int numPlayers = game.welcome();
        List<Better> betters = new ArrayList<>();


            for (int i = 0; i < numPlayers; i++) {
                betters.add(new Better(100, i));
            }


        while (isReplay) {

            //creating fresh deck and shuffling.
            Deck newDeck = new Deck();
            newDeck.shuffle();

            // Setting players' bet and hand.
            for (int i = 0; i < numPlayers; i++) {
                betters.get(i).setBet();
                List<Card> playerHand = newDeck.getCards(2);
                betters.get(i).setCards(playerHand);
            }

            //Creating dealer and showing one card face up.
            List<Card> dealerHand = newDeck.getCards(1);
            Player dealer = new Player();
            dealer.setCards(dealerHand);
            System.out.println("\nDealer's face up card is:");
            dealer.showCards();
            dealerHand = newDeck.getCards(1);
            dealer.setCards(dealerHand);

            //Letting the players stick or twist
            List<Integer> betterTotals = new ArrayList<>();
            for (int i = 0; i < numPlayers; i++) {
                betterTotals.add(game.playerPlay(betters.get(i), newDeck));
            }
            //Letting the dealer stick or twist, and then determining whether the player won or lost.

            int dealerTotal = game.dealerPlay(dealer, newDeck);
            for (int i = 0; i < numPlayers; i++) {
                int result = game.result(betterTotals.get(i), dealerTotal, betters.get(i).getName());
                betters.get(i).addToBalance(result);
                int balance = betters.get(i).getBalance();
                if (balance == 0) {
                    System.out.println("You've run out of money!");
                }
            }
            isReplay = game.isReplay();
        }
            for (int i = 0; i < numPlayers; i++) {
                betters.get(i).finalBalance(100);
            }
    }
}
