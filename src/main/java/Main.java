import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String args[]){
        Deck newDeck = new Deck();
        newDeck.shuffle();
        List<Card> player1Hand = newDeck.getCards(2);
        Player player = new Player(player1Hand);
        boolean isStick = false;
        player.showCards();
        int total = player.getTotal();
        while(!isStick) {

            player.setChoice();
            String player1Choice = player.getChoice();
            if (player1Choice.equals("Twist")) {
                player.addCard(newDeck.getCards(1));
            } else {
                isStick = true;
            }
            player.showCards();
            total = player.getTotal();
            if (total > 21) {
                isStick = true;
            }
        }
        if (total > 21) {
            System.out.println("You went bust!");
        }
    }
}
