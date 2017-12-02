/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author bruna
 */
public class ValidateCreditCard {

    private int[] creditCardNumber;

    public ValidateCreditCard(String creditCard) {
        int[] creditCardNumber = new int[16];
        
        int i = 0;
        for(char c : creditCard.toCharArray()){
            if(i==16){
                System.out.println("No more than 16 digits accepted.");
                break;
            }
            
            creditCardNumber[i] = Character.getNumericValue(c);
            i++;
        }
        
        this.creditCardNumber = creditCardNumber;
    }    
    
    public boolean isValid(){
        if(creditCardNumber.length != 16)
            return false;
        
        int multiplier = 2;
        int sum = 0;
        int[] result = new int[16];
        int i = 0;
        
        for(int algarism : this.creditCardNumber){
            sum += multiplier*algarism;
            result[i] = multiplier*algarism;
            
            i++;
            if(multiplier == 1)
                multiplier = 2;
            else
                multiplier = 1;
        }
        
        if(sum > 10){
            int k = 0;
            
            sum = 0; //nova soma
            for(int j : result){
                // j só irá variar de 0 a 18
                if(j > 9)
                    result[k] = j%10 + j/10;
                sum += result[k];
                k++;
            }
        }
        
        if(sum % 10 == 0)
            return true;
        
        return false;
    }   
    
    public String industryIdentifier(){
        switch(creditCardNumber[0]){
            case 1:
            case 2:
                return "Companhias Aéreas";
            case 3:
                return "Viagem e Entretenimento";
            case 4:
            case 5:
                return "Bancos e Instituições financeiras";
            case 6:
                return "Marketing e Bancos";
            case 7:
                return "Petróleo";
            case 8:
                return "Telecomunicações";
            case 9:
                return "Atribuição nacional";
            default:
                return "No valid Industry Identifier";
        }
    }

    public String operator(){

      String operator = "No valid operator";
      if(creditCardNumber[0] == 4)
          operator = "Visa";
      if(creditCardNumber[0] == 5 && (creditCardNumber[1] == 1 || creditCardNumber[1] == 5))
          operator = "Mastercard";
      if(creditCardNumber[0] == 3 && (creditCardNumber[1] == 4 || creditCardNumber[1] == 7))
          operator = "Amex";
      if(creditCardNumber[0] == 3 && (creditCardNumber[1] == 6 || creditCardNumber[1] == 8 || (creditCardNumber[1] == 0 && (creditCardNumber[2] == 0 || creditCardNumber[2] == 5))))
          operator = "Diners";
      
      return operator;
    }
    
    public String operatorImage(String operator){
      
      switch(operator){
        case "Visa":
            return "visa.jpg";
        case "Mastercard":
            return "mastercard.jpg";
        case "Amex":
            return "amex.png";
        case "Diners":
            return "diners.jpg";
        default:
            return "";
      }
    }
    
    public String findDigit(){
        for(int i =0; i<10; i++){
            creditCardNumber[15] = i;
            if(isValid())
                return Integer.toString(i);
        }
        return ""; //Error
    }
}
