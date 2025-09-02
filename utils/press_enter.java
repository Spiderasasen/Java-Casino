package utils;

import java.util.Scanner;

public class press_enter {
    public void press(){
        //calling the scanner
        Scanner scan = new Scanner(System.in);

        System.out.println("Press Enter to Continue");
        String press = scan.nextLine();
    }
}
