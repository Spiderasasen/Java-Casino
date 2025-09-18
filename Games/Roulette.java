package Games;

//imports
import Games.Util.Money;
import utils.*;
import java.util.*;

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
    public void mainGame(Money money){
        boolean weLooping = true;
        while(weLooping){
            //checking that the player does not enter a differnt number
            try{
                System.out.println("Roulette\n" +
                        "Your Money: " + money.getMoney() +"\n" +
                        "1. Play\n" +
                        "2. How to Play\n" +
                        "3. Exit");
                int choice = scan.nextInt(); //this is why the try catch statment is here

                //checking what the user input
                switch(choice){
                    case 1:
                        int value = 0;
                        money.setMoney(game(money));
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
    public int game(Money bet){
        //asking the player how much they want to enter
        int money = bet.howMuchBet();

        //setting the bets of the loop
        ArrayList<Object> bets = new ArrayList<Object>();
        bets = place_bets();
        int winnings = 0;
        int winning_number = rouletteWheel();
        System.out.println("Winning Number: " + winning_number);
        if(isWinner(bets, winning_number)){
            if(bets.contains("Red") || bets.contains("Black")){
                System.out.println("You Won!!!");
                winnings += (money * 2);
            }
            else{
                System.out.println("You Won!!!");
                winnings += money;
            }
        }
        else{
            if(bets.contains("Red") || bets.contains("Black")){
                System.out.println("You Lose!!!");
                winnings -= (money * 2);
            }
            else{
                System.out.println("You Lose!!!");
                winnings -= money;
            }
        }
        return winnings;
    }

    //ensures all changes are visible across all threads
    private volatile boolean roundOver = false;

    //making a section where makes the user place there bets
    private ArrayList<Object> place_bets(){
        ArrayList<Object> bets = new ArrayList<Object>();

        System.out.println("Place Your Bets!");

        //placing the timer
        Thread timer = new Thread(() -> {
            try{
                Thread.sleep(120000); //timer for 2 minutes

                //when the timer is up it will make the roundOver is
                roundOver = true;
                System.out.println("Times Up!!!");
            }
            catch (InterruptedException e){
                System.err.println("Timer was Interrupted");
            }
        });

        //safety check
        //switch round over to false
        roundOver = false;

        //checking the round is not over
        while (!roundOver){
            System.out.println("3 6 9 12 15 18 21 24 27 30 33 36\n" +
                    "2 5 8 11 14 17 20 23 26 29 32 35\n" +
                    "1 4 7 10 13 16 19 22 25 28 31 34\n" +
                    "         Red       Black         \n" +
                    "             Done                 ");
            //getting the selection
            String betSelection = scan.nextLine();

            //checking if the input is a number
            try{
                //if user selects a number, then it returns the number
                int numberBet = Integer.parseInt(betSelection);
                if(numberBet >= 1 && numberBet <= 36){
                    bets.add(Integer.parseInt(betSelection));
                    if(anyRepeats(bets)){
                        System.out.println("Please enter a unique number");
                        bets.removeLast();
                    }
                }
                else{
                    System.out.println("Please enter a valid number from 1 - 36");
                }
            }
            //if not a number then it will do something else
            catch (NumberFormatException e){
                //if the user selected a number in red
                if(betSelection.equalsIgnoreCase("red")){
                    bets.add("Red");
                    roundOver = true;
                    timer.interrupt();
                }

                //if the user selected a number in black
                else if(betSelection.equalsIgnoreCase("black")){
                    bets.add("Black");
                    roundOver = true;
                    timer.interrupt();
                }

                //if the choise is done
                else if(betSelection.equalsIgnoreCase("done")){
                    roundOver = true;
                    timer.interrupt();
                }

                //if the choise was not one of these options then we tell the user to go again
                else{
                    System.out.println("Please Chose an correct item");
                }
            }

            //adding a small sleep
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException e){
                //nothing happens
            }
        }
        return bets;
    }

    //checks if the number is not repeated
    private boolean anyRepeats(ArrayList<Object> bets){
        Set<Object> seenElements = new HashSet<Object>();

        //if the hash map has a dublicate then it will return true
        for (Object element : bets){
            if(!seenElements.add(element)){
                return true;
            }
        }
        return false;
    }

    //setting the numbers on the roullet table
    private int[] red = {1, 3, 5, 7, 9, 12, 14, 16, 18, 21, 23, 25, 27, 28, 30, 32, 34, 36};
    private int[] black = {2, 4, 6, 8, 10, 11, 13, 15, 17, 19, 20, 24, 22, 26, 29, 31, 33, 35};

    private int rouletteWheel(){
        roundOver = false;
        //placing the timer
        Thread timer = new Thread(() -> {
            try{
                Thread.sleep(30000); //timer for 30 sec

                //when the timer is up it will make the roundOver is
                roundOver = true;
            }
            catch (InterruptedException e){
                System.err.println("Timer was Interrupted");
            }
        });

        timer.start();

        //while the timer is still going. it will return a number
        int number = 0;
        while (!roundOver){
            //prints the number
            System.out.println(number);
            number++;

            //if the number is 37, return number to 0
            if(number == 37){
                number = 0;
            }

            //adding a small sleep
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException e){
                //nothing happens
            }
        }

        //making a random number that will determine the final winning number
        Random rand = new Random();
        int winning_Number = rand.nextInt(36);

        //looping until the number is winning number
        for(int i = number; i != winning_Number; i++){
            //if number is 37 then reset the number
            System.out.println(i);
            if (i == 37){
                i = 0;
            }
        }

        return winning_Number;
    }

    private boolean isWinner(ArrayList<Object> bets, int winningNumber){
        //checking if the user got red or black as a bet
        if(bets.contains("Red")) { //for red
            //looping through all the numbers in red and seeing if the winning number is in this table
            for(int num : red){
                if(num == winningNumber){
                    return true;
                }
            }
        }
        else if(bets.contains("Black")){ //for black
            //looping through all the numbers in black to see if the winning number is in the table
            for(int num : black){
                if(num == winningNumber){
                    return true;
                }
            }
        }
        else{ //contains numbers
            //loops through all the numbers in the bets array list
            for(Object num : bets){
                //checks if it is a integer number
                if (num instanceof Integer){
                    int number = (Integer) num;
                    //checks if number is a winning number
                    if(number == winningNumber){
                        return true;
                    }
                }
            }
        }

        //if both strings and ints don't have a winning number, then return false
        return false;
    }
}
