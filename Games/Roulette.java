package Games;

//imports
import utils.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Roulette {
    //calling the scanner
    Scanner scan = new Scanner(System.in);

    //calling the other classes
    Cleaning screen = new Cleaning();
    press_enter enter = new press_enter();

    //main game interface
    public void mainGame(){
        boolean weLooping = true;
        while(weLooping){
            //checking that the player does not enter a differnt number
            try{
                System.out.println("Roulette\n" +
                        "1. Play\n" +
                        "2. How to Play\n" +
                        "3. Exit");
                int choice = scan.nextInt(); //this is why the try catch statment is here

                //checking what the user input
                switch(choice){
                    case 1:
                        System.out.println("playing roulette");
                        break;
                    case 2:
                        System.out.println("How to play");
                        enter.press();
                        screen.clean();
                        break;
                    case 3:
                        weLooping = false;
                        break;
                    default:
                        System.out.println("Please enter a valid number from 1 - 3");
                        enter.press();
                        screen.clean();
                        break;
                }
            }
            //the player entered the wrong number
            catch (InputMismatchException e){
                System.out.println("Please Enter a Valid Number");
                enter.press();
                screen.clean();
                scan.next();
            //a differnt error occured
            } catch (Exception e) {
                System.out.println("Error Occurred: " + e);
                System.exit(0);
            }
        }

    }
}
