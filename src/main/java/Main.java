import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        boolean isReplay = true;
        while (isReplay) {
            Deck newDeck = new Deck();
            newDeck.shuffle();
            List<Card> player1Hand = newDeck.getCards(2);
            Player player = new Player(player1Hand);
            List<Card> dealerHand = newDeck.getCards(2);
            Player dealer = new Player(dealerHand);
            boolean isStick = false;
            System.out.println("Your Cards: \n");
            player.showCards();
            int total = player.getTotal();
            System.out.println("Your sum is " + total);
            while (!isStick) {

                player.setChoice();
                String player1Choice = player.getChoice();
                if (player1Choice.equals("Twist")) {
                    player.addCard(newDeck.getCards(1));
                } else {
                    isStick = true;
                }
                System.out.println("Your Cards: \n");
                player.showCards();
                total = player.getTotal();
                System.out.println("\nYour sum is " + total);
                if (total > 21) {
                    isStick = true;
                }
            }
            if (total > 21) {
                System.out.println("You went bust!");
            } else {
                int dealerTotal = dealer.getTotal();
                while (dealerTotal < 17) {
                    dealer.addCard(newDeck.getCards(1));
                    dealerTotal = dealer.getTotal();
                }
                System.out.println("The dealer had:\n");
                dealer.showCards();
                System.out.println("\nThe dealer's total is: ");
                System.out.println(dealerTotal + "\n");
                if(total > dealerTotal || dealerTotal > 21) {
                    System.out.println("You won!");
                } else if(total == dealerTotal) {
                    System.out.println("You drew!");
                } else {
                    System.out.println("You lost.");
                }
            }



            Scanner scanner = new Scanner(System.in);
            System.out.println("Play again? (y/n)");
            String replay;
            while (true) {
                replay = scanner.nextLine();
                if (replay.equals("y")) {
                    isReplay = true;
                    break;
                } else if (replay.equals("n")) {
                    isReplay = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter either y or n.");
                }
            }
        }
    }
}
