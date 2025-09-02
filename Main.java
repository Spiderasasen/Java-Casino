//imports
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //calling the scanner
        Scanner scan = new Scanner(System.in);

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
                        break;
                    case 2:
                        System.exit(0);
                    default:
                        System.out.println("Please enter a valid number that is in the menu");
                }
            }
            //if there is another error that occurred
            catch (InputMismatchException e){
                System.out.println("Please enter a valid number");
                String empty = scan.next();
            }
            catch (Exception e){
                System.out.println("Error Occurred: " + e);
                System.exit(0);
            }
        }
    }
}
