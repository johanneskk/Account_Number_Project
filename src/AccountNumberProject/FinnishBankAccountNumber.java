package AccountNumberProject;
import java.lang.*;

public class FinnishBankAccountNumber {
    private final String accNumber;
        
    
    FinnishBankAccountNumber(String accNumber) {
        
        this.accNumber = accNumber;
        if (checksum()== 1) {
            System.out.println("Account number is valid!");
            System.out.println("");
            System.out.print("Long version of the account number is: ");
            System.out.println(getLongFormat());
            System.out.println("");
        } else {
            System.out.println("Account number is not valid.");
           
        }
        
    }
    
    /*returns the long format of account number*/
    public long getLongFormat() {
        
        
        /*dash have to be 7. mark if account number is in short form*/
        if(accNumber.contains("-") && accNumber.indexOf("-")!= 6) {
            if (accNumber.length()!= 14) {
                return 0;
            }
        }
        
        
        
        /*removes all non-digits from account number string*/
        String parsedAccNumber = accNumber.replaceAll("\\D+","");
        int accNumberLength= parsedAccNumber.length();
        if(accNumberLength < 8 || accNumberLength > 14) {
            return 0;
        }
        
        StringBuilder sb = new StringBuilder(parsedAccNumber);
        
        /*First character of account number is needed to create a long format*/
        char firstChar = parsedAccNumber.charAt(0);
        
        /*If first number belongs in "123468" case A if in "45" case B*/
        String caseA = "12368";
        String caseB = "45";
        
        if(caseA.indexOf(firstChar)!= -1) {
            for(int i = 0;i<(14-accNumberLength);i++){
                sb.insert(6, "0");
                
            }
        }else if(caseB.indexOf(firstChar)!= -1) {
            for(int i = 0;i<(14-accNumberLength);i++){
                sb.insert(7, "0");
                
            }
        }else {
            System.out.println("Account number is not valid.");
            return 0;
        }
        
        parsedAccNumber = sb.toString();
        
        long longForm = Long.parseLong(parsedAccNumber);
        return longForm;
    }
    
    /*Function checks if the account number is valid using Luhns algorithm(mod10)*/
    private int checksum() {
        
        long longAccNumber = getLongFormat();
        if (longAccNumber == 0) {
            return -1;
        }
        
        String longAccString = Long.toString(longAccNumber);
        int luhnsSum = 0;
        for (int i = 0 ; i<13 ; i++) {
            
            /*Every other number multiplied by 2 and added to sum*/
            if(i%2 == 0) {
                int currentNum = Character.getNumericValue(longAccString.charAt(i));
                /*If the result of multiplication is 10 or over,
                numbers are separated and added to sum separately*/
                
                if (currentNum*2 >=10) {
                    
                    currentNum = currentNum*2;
                    luhnsSum += (currentNum % 10) +1;                   
                    
                } else {
                    luhnsSum += currentNum*2;
                }
                                
            /*Every other number multiplied by 1 and added to sum*/    
            }else {
                int currentNum = Character.getNumericValue(longAccString.charAt(i));
                luhnsSum += currentNum;
                
                
            }
            
        }
        
        /*solving the real last digit and comparing it to the digit in given 
        account number*/
        int lastNum = (int) (longAccNumber %10);
        int luhnLastNum = 10 - (luhnsSum %10);
        
        
        if (lastNum == luhnLastNum) {
            return 1;
        } else {
            return 0;
        }
        
        
    }
    
}
