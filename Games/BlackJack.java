package Games;
//imports
import utils.*;
import Games.Util.Money;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class BlackJack {
    //calling the scanner and utils
    Cleaning screen = new Cleaning(); //cleaning the screen
    Scanner scan = new Scanner(System.in); //scanner
    press_enter enter = new press_enter(); //making the user to only press enter
    Random randomNumber = new Random();

    //calling a private var that states the money
    private int money;

    //main void for blackjack
    public void mainBlackjack(Money money) {
        //condition to make loop continue
        boolean weLooping = true;

        //while loop to check if the player wants to continue playing
        while (weLooping){
            //checking if all the inputs are good
            try{
                //asking the user what they want to do
                System.out.println("Blackjack\n");
                money.printMoney();
                System.out.println("1. Play\n" +
                        "2. How to Play\n" +
                        "3. Exit");
                int choice = scan.nextInt();

                //selecting the correct item
                switch (choice){
                    case 1:
                        screen.clean();
                        money.setMoney(game(money));
                        break;
                    case 2:
                        System.out.println("Players receive all cards face up, and the dealer's first card is face up and the second is face down. \nThe object of the game is to get closer to 21 than the dealer without going over 21. \nIf a hand goes over 21, it is called a “bust” or “break,” and the wager is lost. \nIn Blackjack, Jacks, Queens, Kings, and 10s count as 10.");
                        enter.press();
                        screen.clean();
                        break;
                    case 3:
                        weLooping = false;
                        screen.clean();
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

    /*only public for testing only the game
    * this is where all the functions go in*/
    public int game(Money bet){
        //getting the bet
        int betMade = bet.howMuchBet();

        //making the list for both items
        ArrayList<Object> botCards = new ArrayList<Object>();
        ArrayList<Object> playerCards = new ArrayList<Object>();

        //giving the bot 1 card
        botCards.add(hittingCard());

        //giving the player 2 cards
        for(int i = 0; i < 2; i++){
            playerCards.add(hittingCard());
        }

        //making the vars for the players
        int playerCardCount = 0;
        int botCardCount = 0;

        boolean weLooping = true;

        //checking the winnings
        int winnings = 0;

        //while loop how long the game will last
        while (weLooping){
            int numTurns = 0;

            //checking if the user did not just enter a letter
            try{
                //printing out the numbers
                System.out.println("Opponent: " + botCards);
                System.out.println("You: " + playerCards);

                //checking the player count of the player
                playerCardCount = totalCards(playerCards);
                System.out.println("Total Card Value: " + playerCardCount);

                //if the player got 21 in the first turn, then we dont have to do anything else
                if(is21(playerCardCount) && numTurns == 0){
                    System.out.println("BLACKJACK!!!\n" +
                            "YOU WIN!!!!!");
                    winnings += (betMade * 2);
                }

                //if the player got 21
                if(is21(playerCardCount)){
                    System.out.println("You Win!!!");
                    winnings += betMade;
                    return winnings;
                }

                //checking if the player did not go over 21
                if(over21(playerCardCount)){
                    System.out.println("You Lost!!!");
                    winnings -= betMade;
                    return winnings;
                }

                //asking the user what else they want to do
                System.out.println("What will you like to do?\n" +
                        "1. Hit\n" +
                        "2. Stand");
                int choice = scan.nextInt();
                switch (choice){
                    case 1:
                        numTurns++;
                        playerCards.add(hittingCard());
                        screen.clean();
                        continue;
                    case 2: //once the player stands, we make the bot go next
                        screen.clean();
                        numTurns = 0;
                        weLooping = false;

                        //looping through the bot, if the bot has a 16 or lower, he has to hit
                        while(botCardCount <= 16){
                            //making the bot hit
                            botCards.add(hittingCard());
                            botCardCount = totalCards(botCards);

                            //the cards of the bot
                            System.out.println("Opponent: " + botCards);
                            System.out.println("Opponent Total Card Value: " + botCardCount);
                            //the cards from the player
                            System.out.println("You: " + playerCards);
                            System.out.println("Your Total Card Value: " + playerCardCount);

                            //if the bot is over the 16 min amount
                            if(botCardCount > 16){
                                //checking if the bot got 21 on the first turn
                                if(is21(botCardCount) && numTurns == 0){
                                    System.out.println("BLACKJACK!!!!\n" +
                                            "YOU LOST!!!!!");
                                    winnings -= (betMade * 2);
                                }
                                //checking if the bot lost
                                if (over21(botCardCount)){
                                    System.out.println("You Win!!!");
                                    winnings += betMade;
                                }
                                //checking who won
                                else if(botCardCount > playerCardCount){
                                    System.out.println("You Lost!!!");
                                    winnings -= betMade;
                                }
                                else{
                                    System.out.println("You Win!!!");
                                    winnings += betMade;
                                }
                                enter.press();
                                screen.clean();
                                return winnings;
                            }
                            enter.press();
                            screen.clean();
                            numTurns++;
                        }
                        break;
                    default:
                        System.out.println("Please enter a valid number from 1 - 2");
                        enter.press();
                        screen.clean();
                        break;
                }
            }
            //the user entered a wrong number
            catch (InputMismatchException e){
                System.out.println("Please enter a valid number");
                enter.press();
                screen.clean();
                scan.next();
            //error occurred
            } catch (Exception e) {
                System.out.println("Error Occurred: " + e);
                System.exit(0);
            }
        }
        return -1;
    }

    private String prettyPrintCard(int card){
        //checking if the number is 1(it going to be an ace)
        if (card == 1){
            return "A";
        }
        //if the number is 10 it will return a jack, a queen, or a king depending on what a random number gen, says
        else if (card == 10){
            String[] cardTypes = {"Jack", "Queen", "King"};
            int choice = randomNumber.nextInt(0,2);
            return cardTypes[choice];
        }
        return null;
    }

    //checking what card can be shown and which cant
    private Object correct_card(String PrettyCard, int numCard){
        Object card;
        if(PrettyCard == null){
            card = numCard;
        }
        else{
            card = PrettyCard;
        }
        return card;
    }

    private int totalCards(ArrayList<Object> allCards){
        int totalNumCards = 0;
        int aceCount = 0; // We'll count the Aces separately

        // Add up all the cards except the Aces.
        for (Object card : allCards) {
            // If the card is a face card (Jack, Queen, or King)
            if (card.equals("Jack") || card.equals("Queen") || card.equals("King")) {
                totalNumCards += 10;
            }
            // If the card is a number card
            else if (card instanceof Integer) {
                Integer myCard = (Integer) card;
                totalNumCards += myCard;
            }
            // If the card is an Ace, just count it for now.
            else if (card.equals("A")) {
                aceCount++;
            }
        }

        // Add the Aces to the total.
        // We assume each Ace is worth 11, then adjust if we bust.
        for (int i = 0; i < aceCount; i++) {
            totalNumCards += 11;
            // Check if the total is over 21 and we have an Ace to "demote" to a 1.
            if (totalNumCards > 21) {
                totalNumCards -= 10;
            }
        }

        return totalNumCards;
    }

    //checking if the user has a 21
    private boolean is21(int hand){return hand == 21;}

    //checking if the player went over 21
    private boolean over21(int hand){return hand > 21;}

    //hitting a card
    private Object hittingCard(){
        int card_number = randomNumber.nextInt(10);
        card_number++;
        String cards = prettyPrintCard(card_number);
        return correct_card(cards, card_number);
    }
}
