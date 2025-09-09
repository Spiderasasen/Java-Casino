package Games;

//imports
import Games.Util.Money;
import utils.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Roulette {
    //calling the scanner
    Scanner scan = new Scanner(System.in);

    //calling the other classes
    Cleaning screen = new Cleaning();
    press_enter enter = new press_enter();

    //making a constructor
    private int money;

    public Roulette(Money money){this.money = money.getMoney();}

    public Roulette(){this.money = 10000;}

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
                        game();
                        break;
                    case 2:
                        System.out.println("The Dealer spins the Roulette Wheel in one direction and a small ball in the opposite direction. \nBets may be placed until the Dealer announces “No More Bets.” \nWhen the ball comes to rest in one of the pockets of the Roulette Wheel, the Dealer will announce the winning number and Place a marker on the winning number. \n" +
                                "\n" +
                                "First the table is cleared of losing wagers and then all the winners are paid. \nPlease do not remove winning wagers or place new bets until all winners have been paid and the Dealer announces “Place Your Bets.”");
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

    //calling the games section of the game
    public void game(){
        System.out.println("hi");
    }

    //making a section where makes the user place there bets
    private void place_bets(){
        System.out.println("Place Your Bets!");
    }
}
