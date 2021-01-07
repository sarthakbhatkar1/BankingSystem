package BankingApplication;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


class Customer extends Account implements Serializable
{
    String name ;

    String customerUser;
    String customerPass;
    String dateCreated;
    Customer()
    {}
    public Customer(Customer obj)
    {
        this.accountID = obj.accountID;
        this.name = obj.name;
        this.customerUser = obj.customerUser;
        this.customerPass = obj. customerPass;
        this.balance = obj.balance;
        this.dateCreated = obj.dateCreated;
    }

    void scanCustomerDetails(String year) throws IOException
    {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj3 = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        String fulldate = myDateObj.format(myFormatObj3);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Customer Name : ");
        name = sc.nextLine().toUpperCase();

        System.out.print("Enter the Customer Username : ");
        customerUser = sc.nextLine();
        System.out.print("Enter the Customer Password : ");
        customerPass = sc.nextLine();
        scanAccountDetails();

        accountID = year + name.substring(0,2).toUpperCase() + Integer.toString(customercount +101);
        System.out.println("\nYour Account ID  : "+accountID);
        System.out.println("Account Created on : "+fulldate);
        dateCreated = fulldate;
        File objfile = new File("E:\\SARTHAK\\BankProject\\src\\"+accountID);//make a file of customer using accountid of customer id
        FileOutputStream fos = new FileOutputStream(objfile);
        ObjectOutputStream objOs=new ObjectOutputStream(fos);
        objOs.writeObject(this);
        fos.close();
        objOs.close();
    }

    void displaycustomerDetails(String identity) throws IOException, ClassNotFoundException
    {

        FileInputStream fis = new FileInputStream("E:\\SARTHAK\\BankProject\\src\\"+identity);
        ObjectInputStream objIS = new ObjectInputStream(fis);
        Customer newObject = (Customer)objIS.readObject();
        System.out.printf("%10s  |%10s  |%9s    |  %10s   |  %.1f  \n", newObject.accountID, newObject.customerUser, newObject.name, newObject.dateCreated, newObject.balance);
        fis.close();
        objIS.close();
    }


    boolean checkcustomer(String username, String password, String acc ) throws IOException,ClassNotFoundException
    {

        FileInputStream fis = new FileInputStream("E:\\SARTHAK\\BankProject\\src\\"+acc);
        ObjectInputStream objIS = new ObjectInputStream(fis);
        Customer newObject = (Customer)objIS.readObject();
        String tempuser = newObject.customerUser;
        String temppass = newObject.customerPass;
        if(tempuser.equals(username) && temppass.equals(password))
        {
            return  true;
        }

        return false;
    }

}

