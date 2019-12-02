import java.util.Scanner;

public class Game {

    int welcome() throws InterruptedException {
        System.out.println("Welcome to BlackJack!\n");
        Thread.sleep(1000);
        Scanner scanner = new Scanner(System.in);
        int inputNum;

        while (true) {
            try {
                System.out.println("How many people are playing? (1-5)");
                inputNum = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid type. Please enter a number.");
                continue;
            }
            if (inputNum >= 1 && inputNum <= 5) {
                return inputNum;
            }
        }
    }

    int playerPlay(Better player, Deck newDeck) throws InterruptedException {
        boolean isStick = false;
        Thread.sleep(500);
        int total;
        while (!isStick) {
            System.out.println("\n" + player.getName() + " Cards: \n");
            player.showCards();
            total = player.getTotal();
            Thread.sleep(500);
            System.out.println("\nYour sum is " + total);
            player.setChoice();
            String player1Choice = player.getChoice();
            if (player1Choice.equals("Twist")) {
                player.addCard(newDeck.getCards(1));
            } else {
                isStick = true;
            }
            total = player.getTotal();
            if (total > 21) {
                isStick = true;
            }
        }
        System.out.println("\n||" + player.getName() + "||'s Final Cards: \n");
        player.showCards();
        total = player.getTotal();
        Thread.sleep(500);
        System.out.println("\nYour sum is " + total);
        return total;
    }

    int dealerPlay(Player dealer, Deck newDeck) throws InterruptedException {


        int dealerTotal = dealer.getTotal();
        while (dealerTotal < 17) {
            dealer.addCard(newDeck.getCards(1));
            dealerTotal = dealer.getTotal();
        }
        System.out.println("The dealer had:\n");
        dealer.showCards();
        System.out.println("\nThe dealer's total is: ");
        System.out.println(dealerTotal + "\n");
        return dealerTotal;
    }


    int result(int total, int dealerTotal, String name) throws InterruptedException {
        System.out.println("Hello, " + name);
            if(total >21) {
                System.out.println("You went bust!");
                return -1;
            }
            else if(total > dealerTotal || dealerTotal > 21) {
                System.out.println("You won!");
                return 1;
            } else if(total == dealerTotal) {
                System.out.println("You drew!");
                return 0;
            } else {
                System.out.println("You lost.");
                return -1;
            }
        }


    boolean isReplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Play again? (y/n)");
        String replay;
        while (true) {
            replay = scanner.nextLine();
            if (replay.equalsIgnoreCase("y") || replay.equalsIgnoreCase("yes")) {
                return true;
            } else if (replay.equalsIgnoreCase("n") || replay.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter either y or n.");
            }
        }
    }
}
