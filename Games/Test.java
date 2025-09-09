package Games;
import Games.Util.Money;

public class Test {
    public static void main(String[] args) {
        //calling everything
        BlackJack test1 = new BlackJack();
        Roulette test2 = new Roulette();
        Money moneyTest = new Money();

        //testing area
//        test1.mainBlackjack(); //all the test will come in here. it just depends on what tf you are going to do with it
//        test1.game();
//        test2.mainGame();
        test2.game();
        int[] differntAmount = {100, -59, -1000, 1000};
        for (int amount : differntAmount){
            System.out.println(moneyTest.getMoney());
            moneyTest.setMoney(amount);
        }
    }
}
