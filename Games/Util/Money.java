package Games.Util;
import java.util.Scanner;

public class Money {
    //calling the scanner
    Scanner betScan = new Scanner(System.in);

    //private vars
    private int money;

    //default constructor
    public Money(){
        this.money = 10000;
    }

    public Money(int money){
        setMoney(money);
    }

    //if the current money is 0 or less then 0, then you wont be able to get anymore money
    public void setMoney(int money) {
        if (this.money <= 0){
            System.out.println("You can't get anymore money");
        }
        else{
            //if the amount is greater then 0
            if (money > 0){
                this.money += money;
            }
            //if the amount is less then 0
            else if (money < 0){
                this.money += money;
            }
        }
    }

    //seeing how much people can bet
    public int howMuchBet(){
        System.out.println("How much do you want to bet?");
        int bet = betScan.nextInt();

        if(money <= 0){
            System.out.println("You Can't bet anymore");
            return -1;
        }
        else{
            return bet;
        }
    }

    //just printing your money value
    public void printMoney(){
        System.out.println("Your Money: " + getMoney());
    }

    //gets you the money ammount
    public int getMoney() {
        return this.money;
    }
}
