package BankingApplication;

import java.util.*;

import java.io.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import BankingApplication.*;


class Main implements Serializable
{
    public static void main(String[] args) throws IOException, ClassNotFoundException,FileNotFoundException
    {
        clrscr();
        Customer[] obj = new Customer[50];
        Bank bk = new Bank();
        File objFile = new File("E:\\SARTHAK\\BankProject\\src");
        if(!objFile.exists())
        {
            objFile.mkdir();
        }
        String name[] = objFile.list();
        Bank.customercount = name.length;

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E");
        DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("yyyy");
        int date = Integer.parseInt(myDateObj.format(myFormatObj1));
        String day = myDateObj.format(myFormatObj);
        String year = myDateObj.format(myFormatObj2);
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            int n,n1,n2,n3;
            boolean rep = true;
            clrscr();
            System.out.println("$$$$$$$$$$$$$$  Bank Application  $$$$$$$$$$$$$$");
            System.out.println("1. Admin Login\n2. Customer Login\n3. Exit");
            n = sc.nextInt();
            switch (n)
            {
                case 1:

                    clrscr();
                    System.out.println("***************** Admin Login *****************");
                    System.out.print("Enter the admin username : ");
                    String username = sc.next();
                    System.out.print("Enter the admin password : ");
                    String password = sc.next();

                    if(username.equals(bk.adminUserName) && password.equals(bk.adminPassWord))
                    {


                        while(rep)
                        {

                            clrscr();// it clear the command prompt
                            System.out.println("############## Admin Access ###############");
                            System.out.println("1. Create New User\n2. Display User Details\n3. Go to Main Menu");
                            System.out.println("###########################################");
                            n1 = sc.nextInt();
                            switch (n1)
                            {
                                case 1:

                                    clrscr();
                                    obj[Bank.customercount] = new Customer();
                                    obj[Bank.customercount].scanCustomerDetails(year);
                                    Bank.customercount++;
                                    System.out.println("\nNEW USER CREATED SUCCESSFULLY!!!!............\nPRESS ENTER FOR MORE OPTION");
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;


                                case 2:

                                    if(Bank.customercount==0)
                                    {
                                        System.out.println("\nNO DATA FOUND!!!!!!...........\nPRESS ENTER FOR MORE OPTION");
                                        sc.nextLine();
                                        sc.nextLine();
                                        break;
                                    }
                                    clrscr();
                                    name = objFile.list();
                                    System.out.println("###########################################");
                                    System.out.println("Display User In Order");
                                    System.out.println("1. Ascending\n2. Descending\n3. Go to Main Menu");
                                    System.out.println("###########################################");
                                    n3 = sc.nextInt();
                                    switch (n3)
                                    {
                                        case 1:
                                            for(int i=0;i<name.length;i++)
                                            {
                                                for(int j=0;j<name.length-1;j++)
                                                {
                                                    if (Integer.parseInt(name[j].substring(7))>Integer.parseInt(name[j+1].substring(7)))
                                                    {
                                                        String temp;
                                                        temp = name[j];
                                                        name[j] = name[j+1];
                                                        name[j+1] = temp;

                                                    }
                                                }
                                            }
                                            break;

                                        case 2:
                                            for(int i=0;i<name.length;i++)
                                            {
                                                for(int j=0;j<name.length-1;j++)
                                                {
                                                    if (Integer.parseInt(name[j].substring(7))<Integer.parseInt(name[j+1].substring(7)))
                                                    {
                                                        String temp;
                                                        temp = name[j];
                                                        name[j] = name[j+1];
                                                        name[j+1] = temp;

                                                    }

                                                }
                                            }
                                            break;

                                        default:
                                            break;
                                    }
                                    clrscr();

                                    System.out.println("-----------------------------------------------------------------------------------");
                                    System.out.printf("%10s  |%10s  |%9s    |  %20s   |  %6s", "ACCOUNT ID", "USERNAME", "NAME","Account Created","BALANCE");
                                    System.out.println();
                                    System.out.println("-----------------------------------------------------------------------------------");

                                    for(int i=0;i<name.length;i++)
                                    {

                                        Customer obj1 = new Customer();
                                        obj1.displaycustomerDetails(name[i]);
                                    }
                                    System.out.println("\nPRESS ENTER FOR MORE OPTION");
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;

                                case 3:
                                    rep = false;
                                    break;

                                default:
                                    System.out.println("INVALID INPUT");
                                    break;

                            }
                        }
                    }
                    else
                    {
                        System.out.print("\nInvalid Username or Password!!!....\nGO TO MAIN MENU  PRESS ENTER");
                        sc.nextLine();
                        sc.nextLine();
                    }
                    break;

                case 2:

                    try
                    {

                        if(day.equals("Sun") || (day.equals("Sat")&&((date > 7 && date < 15)||(date > 21 && date < 29))))
                        {
                            if(day.equals("Sun"))
                            {
                                throw new Myexception("Access Denied!!!.........\nYou cannot login on Sunday\nHave A Nice Day");
                            }
                            else
                            {
                                throw new Myexception("Access Denied!!!.........\nYou cannot login on 2nd & 4th Saturday\nHave A Nice Day");

                            }

                        }

                        else
                        {
                            clrscr();
                            System.out.println("*****************Customer Login*****************");
                            System.out.print("Enter Account Id : ");
                            String acc = sc.next();
                            System.out.print("Enter customer username : ");
                            username = sc.next();
                            System.out.print("Enter customer password : ");
                            password = sc.next();
                            boolean check = false;
                            Customer custobj = new Customer();
                            File checkFile = new File("E:\\SARTHAK\\BankProject\\src\\"+acc);
                            if(checkFile.exists())
                            {
                                check = custobj.checkcustomer(username, password,acc);
                            }
                            else
                            {
                                System.out.print("\nEnter Valid Account Id");
                            }

                            if(check)
                            {

                                FileInputStream fis = new FileInputStream("E:\\SARTHAK\\BankProject\\src\\"+acc);
                                ObjectInputStream objIS = new ObjectInputStream(fis);
                                Customer newObject = (Customer)objIS.readObject();
                                rep = true;
                                while(rep)
                                {

                                    clrscr();
                                    System.out.println("############## Customer Access ###############");
                                    System.out.println("1. Account Details\n2. Withdraw Money\n3. Deposit Money\n4. Transfer Money\n5. Go To Main Menu");
                                    System.out.println("##############################################");
                                    n2 = sc.nextInt();
                                    switch (n2)
                                    {
                                        case 1:

                                            clrscr();
                                            System.out.println("-----------------------------------------------------------------------------------");
                                            System.out.printf("%10s  |%10s  |%9s    |  %20s   |  %6s", "ACCOUNT ID", "USERNAME", "NAME","Account Created","BALANCE");
                                            System.out.println();
                                            System.out.println("-----------------------------------------------------------------------------------");
                                            custobj.displaycustomerDetails(acc);
                                            System.out.println("\nPRESS ENTER FOR MORE OPTION");
                                            sc.nextLine();
                                            sc.nextLine();
                                            break;

                                        case 2:

                                            clrscr();
                                            newObject.balance = custobj.withdrawal(newObject.balance);
                                            custobj = newObject;
                                            fis.close();
                                            objIS.close();
                                            File objfile = new File("E:\\SARTHAK\\BankProject\\src\\"+acc);
                                            FileOutputStream fos = new FileOutputStream(objfile);
                                            ObjectOutputStream objOs=new ObjectOutputStream(fos);
                                            objOs.writeObject(custobj);
                                            fos.close();
                                            objOs.close();
                                            sc.nextLine();
                                            sc.nextLine();
                                            break;

                                        case 3:

                                            clrscr();
                                            newObject.balance = custobj.deposit(newObject.balance);
                                            custobj = newObject;
                                            fis.close();
                                            objIS.close();
                                            File objfile1 = new File("E:\\SARTHAK\\BankProject\\src\\"+acc);//make a file of customer using accountid
                                            FileOutputStream fos1 = new FileOutputStream(objfile1);
                                            ObjectOutputStream objOs1=new ObjectOutputStream(fos1);
                                            objOs1.writeObject(custobj);
                                            fos1.close();
                                            objOs1.close();
                                            sc.nextLine();
                                            sc.nextLine();
                                            break;

                                        case 4:


                                            clrscr();
                                            System.out.print("Enter the Account ID to Which U want to transfer : ");
                                            String acc1 = sc.next();
                                            boolean tf = false;
                                            File checkFile1 = new File("E:\\SARTHAK\\BankProject\\src\\"+acc1);
                                            if(checkFile1.exists())
                                            {
                                                tf = true;
                                            }
                                            float amt = 0;

                                            if(tf)
                                            {
                                                FileInputStream fis1 = new FileInputStream("E:\\SARTHAK\\BankProject\\src\\"+acc1);
                                                ObjectInputStream objIS1 = new ObjectInputStream(fis1);
                                                Customer newObject1 = (Customer)objIS1.readObject();
                                                System.out.print("Enter the amount U want transfer : ");
                                                amt = sc.nextFloat();

                                                if(amt<0)
                                                {
                                                    System.out.println("\nAmount entered is negative.......... so you can not withdraw \nPRESS ENTER FOR MORE OPTION");
                                                }
                                                else if(amt < newObject.balance && newObject.balance-amt>=1000)
                                                {
                                                    newObject.balance -= amt;
                                                    newObject1.balance += amt;
                                                    System.out.println("\nTransfer successful!.........\nFinal Balance is "+newObject.balance+"\nPRESS ENTER FOR MORE OPTION" );
                                                }
                                                else if (amt > newObject.balance)
                                                {
                                                    System.out.println("\nTransfer failed, not enough balance!!!!......\nPRESS ENTER FOR MORE OPTION");
                                                }
                                                else
                                                {
                                                    System.out.println("\nLess Balance or minimuim balance should be 1000..Transaction Failed..\nPRESS ENTER FOR MORE OPTION");

                                                }
                                                custobj = newObject;
                                                Customer tempobj = new Customer();
                                                tempobj = newObject1;
                                                fis.close();
                                                objIS.close();
                                                fis1.close();
                                                objIS1.close();
                                                File objfile2 = new File("E:\\SARTHAK\\BankProject\\src\\"+acc);
                                                FileOutputStream fos2 = new FileOutputStream(objfile2);
                                                ObjectOutputStream objOs2=new ObjectOutputStream(fos2);
                                                objOs2.writeObject(custobj);
                                                File objfile3 = new File("E:\\SARTHAK\\BankProject\\src\\"+acc1);
                                                FileOutputStream fos3 = new FileOutputStream(objfile3);
                                                ObjectOutputStream objOs3=new ObjectOutputStream(fos3);
                                                objOs3.writeObject(tempobj);
                                                fos2.close();  objOs2.close();  fos3.close();  objOs3.close();
                                                sc.nextLine();
                                                sc.nextLine();
                                            }
                                            else
                                            {
                                                System.out.println("\nINVALID ACCOUNT ID !!..........\nPRESS ENTER FOR MORE OPTION");
                                                sc.nextLine();
                                                sc.nextLine();
                                            }
                                            break;

                                        case 5:
                                            rep = false;
                                            break;

                                        default:
                                            System.out.println("INVALID INPUT");
                                            break;
                                    }
                                }
                            }
                            else
                            {
                                System.out.print("\nInvalid Username or Password!!!....\nGO TO MAIN MENU  PRESS ENTER");
                                sc.nextLine();
                                sc.nextLine();

                            }
                            break;
                        }
                    }
                    catch(Myexception e)
                    {
                        System.out.println(e.getMessage());
                    }

                case 3:
                    System.exit(0);

                default:
                    System.out.println("INVALID INPUT");
                    break;

            }

        }
    }
    static void clrscr()
    {

        try
        {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}

