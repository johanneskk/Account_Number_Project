/*
    Task for Visma Solutions
    
 
    @author Johannes Kohvakka
 */
package AccountNumberProject;
import java.util.Scanner;

public class Main {

  
    public static void main(String[] args) {
        
        
        menu();
        
        //FinnishBankAccountNumber account = new FinnishBankAccountNumber("110335-1537");
        //System.out.println(account.getLongFormat());
        

    }
    /*Simple text user interface*/
    static public void menu() {
        
        
        System.out.println("The program checks if the entered account number is valid or not.");
        System.out.println("If the number is valid, program turns it to long format.");
        System.out.println("If you wanna exit the program at any point, just type 'exit' and press enter.");

        while(true){
        Scanner reader = new Scanner(System.in);
            
            System.out.println("");
            System.out.print("Enter an account number: ");
            String accNumber = reader.nextLine();
            if(accNumber.equals("exit")) {
                return;
            } else {
                FinnishBankAccountNumber account = new FinnishBankAccountNumber(accNumber);
            }
        }
    }
    
}
