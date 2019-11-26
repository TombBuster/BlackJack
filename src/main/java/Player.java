import java.util.List;
import java.util.Scanner;

public class Player {
    private List<Card> cards;
    private String choice;

    public Player(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(List<Card> card) {
        this.cards.add(card.get(0));
    }

    public List<Card> getCards() {
        return cards;
    }

    public void showCards() {
        for (Card i: cards) {
            System.out.print(i.getRank() + " OF ");
            System.out.println(i.getSuit());
        }
    }

    public void setChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Stick or twist?");
        boolean isValidChoice = false;
        String player1Choice = "T";
        String[] choices = {"Stick", "S", "Twist", "T"};
        while (!isValidChoice) {
            System.out.println("Please choose an option: ");
            player1Choice = scanner.nextLine();
            // Case insensitive
            player1Choice = player1Choice.substring(0, 1).toUpperCase() + player1Choice.substring(1).toLowerCase();
            for (String choice : choices) {
                if (player1Choice.equals(choice)) {
                    isValidChoice = true;
                    break;
                }
            }
            switch (player1Choice) {
                case "S":
                    player1Choice = "Stick";
                    break;
                case "T":
                    player1Choice = "Twist";
                    break;
            }
        }
        this.choice = player1Choice;
    }

    public String getChoice() {
        return choice;
    }

    public int getTotal() {
        int total = 0;
        int cardVal = 0;
        int AceNumber = 0;
        for (Card i: cards) {
             cardVal = i.getRank().getValue();
             if (cardVal == 11) {
                 AceNumber += 1;
             }
             total += cardVal;
        }
        // Adjust for aces
        if (AceNumber > 0) {
            for (int j=0; j<AceNumber; j++) {
                if (total > 21) {
                    total -= 10;
                }
            }
        }
        return total;
    }
}
