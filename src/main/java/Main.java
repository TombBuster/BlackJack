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
            List<Card> dealerSecondHand = newDeck.getCards(1);
            dealerHand.addAll(dealerSecondHand);
            dealer.setCards(dealerHand);

            //Letting the players stick or twist
            List<Integer> betterTotals = new ArrayList<>();
            int min = 22;
            boolean isDealerPlaying = false;
            for (int i = 0; i < numPlayers; i++) {
                betterTotals.add(game.playerPlay(betters.get(i), newDeck));
                if (betterTotals.get(i) < min) {
                         isDealerPlaying = true;
                }
            }
            //Letting the dealer stick or twist, and then determining whether the player won or lost.
                 int dealerTotal = 21;
            if (isDealerPlaying) {
                dealerTotal = game.dealerPlay(dealer, newDeck);
            }
           int maxBalance = 0;
            List<Better> newBetters = new ArrayList<>();
            for (int i = 0; i < numPlayers; i++) {
                int result = game.result(betterTotals.get(i), dealerTotal, betters.get(i).getName());
                betters.get(i).addToBalance(result);
                int balance = betters.get(i).getBalance();
                if (balance > 0) {
                    maxBalance = balance;
                    newBetters.add(betters.get(i));
                }
                if (balance == 0) {
                    System.out.println("You've run out of money!");
                }
            }
            if (maxBalance == 0) {
                isReplay = false;
            } else {
                isReplay = game.isReplay();
            }
            betters = newBetters;
            numPlayers = betters.size();
        }
            for (int i = 0; i < numPlayers; i++) {
                betters.get(i).finalBalance(100);
            }
    }
}
