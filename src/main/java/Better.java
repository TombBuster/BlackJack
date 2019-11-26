import java.util.List;
import java.util.Scanner;

public class Better extends Player {
    private int bet;
    private int balance;

    public Better(int balance) {
        this.balance = balance;
    }

    void setBet() {
        Scanner scanner = new Scanner(System.in);
        int inputBet = 0;
        boolean isValidBet = false;
        while (!isValidBet) {
            try {
                System.out.println("Please enter an amount to bet: ");
                inputBet = Integer.parseInt(scanner.nextLine());
            } catch(Exception e) {
                System.out.println("Invalid type. Please enter a number.");
                continue;
            }
            if (inputBet > balance) {
                System.out.println("You don't have enough money for that bet. Please enter a lower amount.");
            } else if (inputBet <= 0) {
                System.out.println("Please enter a positive amount.");
            } else {
                isValidBet = true;
            }
        }
        this.bet = inputBet;
    }

    void addToBalance(int result) {
        this.balance += result*bet;
    }

    int getBalance() {
        System.out.println("Your balance is: £" + balance);
        return balance;
    }

    void finalBalance(int initialBalance) {
        int balanceDifference = balance - initialBalance;
        if (balanceDifference > 0) {
            System.out.println("You made a profit of £" + balanceDifference + "!");
        } else if (balanceDifference == 0) {
            System.out.println("You broke even!");
        } else {
            System.out.println("You lost £" + Math.abs(balanceDifference) + ".");
        }
    }
}
