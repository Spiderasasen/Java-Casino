package Games;
//imports
import utils.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BlackJack {
    //main void for blackjack
    public void mainBlackjack() {
        //calling the scanner and utils
        Cleaning screen = new Cleaning(); //cleaning the screen
        Scanner scan = new Scanner(System.in); //scanner
        press_enter enter = new press_enter(); //making the user to only press enter

        //condition to make loop continue
        boolean weLooping = true;

        //while loop to check if the player wants to continue playing
        while (weLooping){
            //checking if all the inputs are good
            try{
                //asking the user what they want to do
                System.out.println("1. Play\n" +
                        "2. How to Play\n" +
                        "3. Exit");
                int choice = scan.nextInt();

                //selecting the correct item
                switch (choice){
                    case 1:
                        System.out.println("playing blackjack");
                        break;
                    case 2:
                        System.out.println("teaching you how to play");
                        enter.press();
                        break;
                    case 3:
                        weLooping = false;
                        break;
                    default:
                        System.out.println("Please enter a valid number from 1 - 3");
                        break;
                }
            }
            //player chose the wrong input
            catch (InputMismatchException e){
                System.out.println("Please enter a valid number");
                enter.press();
                screen.clean();
                scan.next();
            }
            //unexpected error occurred
            catch (Exception e){
                System.out.println("Error Occurred: " + e);
                System.exit(0);
            }
        }
    }

    //checking if the user has a 21
    private boolean is21(int hand){
        if (hand == 21){
            return true;
        }
        return false;
    }
}
