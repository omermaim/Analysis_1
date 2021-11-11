package com.company;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Machine\n");
        Scanner input = new Scanner(System.in);

        int choice;
        while(true){
            System.out.println("Enter the number of the operation you would like to do:");
            System.out.println("1) Add user");
            System.out.println("2) Remove user");
            System.out.println("3) Login user");
            System.out.println("4) Logout user");
            System.out.println("5) Create new order");
            System.out.println("6) Add product to order ");
            System.out.println("7) Display order");
            System.out.println("8) Link Product");
            System.out.println("9) Add Product");
            System.out.println("10) Delete Product");
            System.out.println("11) ShowAllObjects");
            System.out.println("12) ShowObjectId");
            System.out.println("13) Exit");

            choice = input.nextInt();
            String id,password,ans,address,phone_num,email,billing_address;

            ArrayList<User> users = new ArrayList<User>();
            switch (choice){

                case 1:
                    //Add user
                    System.out.println("Enter desired User ID");
                    id = input.next();
                    System.out.println("Enter desired password");
                    password = input.next();
                    System.out.println("Enter customer address");
                    address = input.next();
                    System.out.println("Enter customer phone number");
                    phone_num = input.next();
                    System.out.println("Enter customer email");
                    email = input.next();
                    System.out.println("Enter account billing address");
                    billing_address = input.next();
                    System.out.println("Would you like to upgrade to premium account?\nIf you do enter Y");
                    ans = input.next();


                    java.sql.Date currdate = new java.sql.Date(System.currentTimeMillis());
                    Account account;

                    if(ans.equals("Y"))
                    {
                        account = new PremiumAccount(id, billing_address, false,currdate, null, 0,id,new Address(address),phone_num,email,currdate,null);
                    }
                    else{
                        account = new Account(id, billing_address, false,currdate, null, 0,id,new Address(address),phone_num,email,currdate,null);

                    }
                    users.add(new User(UserState.New,password,id,(Customer)account.getCustomer(),account.getShopingCart()));
                    System.out.println(users.get(0));





                    break;

                case 2:
                    //Add user

                    break;

                case 3:
                    //Add user

                    break;

                case 4:
                    //Add user

                    break;

                case 5:
                    //Add user

                    break;

                case 6:
                    //Add user

                    break;

                case 7:
                    //Add user

                    break;

                case 8:
                    //Add user

                    break;

                case 9:
                    //Add user

                    break;

                case 10:
                    //Add user

                    break;

                case 11:
                    //Add user

                    break;

                case 12:
                    //Add user

                    break;

                case 13:
                    //Exit
                    System.out.println("Thank you for choosing the machine, Goodbye.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Input, please try again");
                    break;

            }
        }

    }
}