import java.util.Scanner;

public class Game {

    void welcome() {
        System.out.println("Welcome to BlackJack!\n");
    }

    int playerPlay(Player player, Deck newDeck) {
        boolean isStick = false;
        System.out.println("Your Cards: \n");
        player.showCards();
        int total = player.getTotal();
        System.out.println("\nYour sum is " + total + "\n");
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
        return total;
    }

    void result(int total, Player dealer, Deck newDeck) {
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
    }

    boolean isReplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Play again? (y/n)");
        String replay;
        while (true) {
            replay = scanner.nextLine();
            if (replay.equals("y")) {
                return true;
            } else if (replay.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter either y or n.");
            }
        }
    }
}
