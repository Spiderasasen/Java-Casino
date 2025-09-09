//imports
import Games.*;
import utils.*;
import Games.Util.Money;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //calling the money class
        Money money = new Money();
        //calling the scanner
        Scanner scan = new Scanner(System.in);

        //calling other classes
        Cleaning screen = new Cleaning();
        press_enter enter = new press_enter();
        BlackJack blackjack = new BlackJack();

        while(true){
            //checking if the input is an integer
            try {
                //printing the main title
                System.out.println("̲\uD835\uDE79̲\uD835\uDE8A̲\uD835\uDE9F̲\uD835\uDE8A ̲\uD835\uDE72̲\uD835\uDE8A̲\uD835\uDE9C̲\uD835\uDE92̲\uD835\uDE97̲\uD835\uDE98");

                //placing the 2 items and asking the user to make a selection
                System.out.println("1. Play\n" +
                        "2. Exit\n" +
                        "Please select an option");
                int choice = scan.nextInt();

                //checking what choice the user slected
                switch (choice){
                    case 1:
                        //asking the player what game they want to play OR if they want to exit
                        System.out.println("1. Blackjack\n" +
                                "2. Roulette\n" +
                                "3. Slots\n" +
                                "4. War\n" +
                                "5. Poker\n" +
                                "6. Exit");
                        choice = scan.nextInt();

                        //checking the choice of the player
                        switch (choice){
                            case 1:
                                screen.clean();
                                blackjack.mainBlackjack();
                                break;
                            case 2:
                                System.out.println("Play roulette");
                                break;
                            case 3:
                                System.out.println("play slots");
                                break;
                            case 4:
                                System.out.println("playing war");
                                break;
                            case 5:
                                System.out.println("playing poker");
                                break;
                            case 6:
                                System.exit(0);
                            default:
                                System.out.println("Please enter a valid number that is in the menu");
                                break;
                        }
                        break;
                    case 2:
                        scan.close();
                        System.exit(0);
                    default:
                        System.out.println("Please enter a valid number that is in the menu");
                        enter.press();
                        break;
                }
                screen.clean();
            }
            //if there is another error that occurred
            catch (InputMismatchException e){
                System.out.println("Please enter a valid number");
                enter.press();
                screen.clean();
                scan.next();
            }
            catch (Exception e){
                System.out.println("Error Occurred: " + e);
                System.exit(0);
            }
        }
    }
}
