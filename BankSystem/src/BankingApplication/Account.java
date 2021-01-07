package BankingApplication;
import java.io.Serializable;
import java.util.Scanner;

//import Bank.java;


class Account extends Bank implements Serializable
{
    String accountID;
    float balance = 0;
    float amt;


    void scanAccountDetails()
    {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Opening Balance greater than 10,000 : ");
        while (true)
        {
            balance = sc.nextFloat();
            if (balance>=10000)
            {
                break;
            }
            else
            {
                System.out.print("Enter the amount greater than or equal to 10,000 : ");
            }
        }


    }

    void displayAccountDetails()
    {

        System.out.format("%10s  |  %.1f\n",accountID,balance);

    }



    float deposit(float balance1)
    {

        Scanner sc = new Scanner(System.in);
        System.out.println("Your Account Balance is "+balance1);
        System.out.print("Enter Amount U Want to Deposit : ");
        amt = sc.nextFloat();
        if(amt<0)  //it does not allow to deposit a negative amount
        {
            System.out.println("\nAmount entered is negative.......... so you can not withdraw \nPRESS ENTER FOR MORE OPTION");
        }
        else
        {
            balance1 = balance1+amt;
            System.out.println("\nTransaction successful!........\nFinal Balance is "+balance1+"\nPRESS ENTER FOR MORE OPTION");
            return balance1;
        }
        return balance1 ;
    }


    float withdrawal(float balance1)
    {

        Scanner sc = new Scanner(System.in);
        while(true)
        {

            System.out.println("Your Account Balance is "+balance1);
            System.out.print("Enter Amount U Want to withdraw : ");
            amt = sc.nextFloat();
            if(amt<0)
            {
                System.out.println("\nAmount entered is negative.......... so you can not withdraw \nPRESS ENTER FOR MORE OPTION");
                break;
            }
            else if(balance1==1000)// if the balance is 1,000 you can not withdraw as minimum balance should be 1,000
            {
                System.out.println("\nyour balance is 1,000.......... so you can not withdraw \nPRESS ENTER FOR MORE OPTION");
                break;
            }
            else if(balance1>=amt && balance1-amt>=1000)
            {
                balance1 = balance1-amt;
                System.out.println("\nTransaction successful!........\nFinal Balance is "+balance1+"\nPRESS ENTER FOR MORE OPTION");
                return balance1;
            }


            else
            {
                System.out.println("\nLess Balance or minimuim balance should be 1000..Transaction Failed.......\nPRESS ENTER FOR MORE OPTION");
                break;
            }

        }
        return balance1;
    }


}
