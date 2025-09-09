package Games.Util;

public class Money {
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

    //gets you the money ammount
    public int getMoney() {
        return this.money;
    }
}
