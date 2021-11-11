package com.company;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Machine\n");
        Scanner input = new Scanner(System.in);
        //create 3 data structers for products, accounts, users, and suppliers, and automatically adding users that they want us to add
        //*
        //*


        ArrayList<User> Users = new ArrayList<>();
        User loggedInUser = null;
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<Account> accounts = new ArrayList<>();
        suppliers.add(new Supplier("osem", "osem"));
        suppliers.add(new Supplier("EastWest", "EastWest"));
        products.add(new Product("Bamba", "Bamba", suppliers.get(0)));
        products.add(new Product("Ramen", "Ramen", suppliers.get(1)));
        suppliers.get(0).addProduct(products.get(0));
        suppliers.get(1).addProduct(products.get(1));
        Customer Cust = new Customer("Dani", null, null, null, null);
        User Us = new User(UserState.New, "Dani123", "Dani", Cust);
        Account Ac = new Account("Dani", null, false, 0, Cust, null);
        ShopingCart Shp = new ShopingCart(Ac.getOpen(), Us, Ac);
        Cust.setAccount(Ac);
        Cust.setUser(Us);
        Shp.setAccount(Ac);
        Ac.setShopingCart(Shp);
        Customer Cust1 = new Customer("Dana", null, null, null, null);
        User Us1 = new User(UserState.New, "Dana123", "Dana", Cust1);
        PremiumAccount Ac1 = new PremiumAccount("Dana", null, false, 0, Cust1, null);
        Ac1.addProduct(products.get(0));
        ShopingCart Shp1 = new ShopingCart(Ac1.getOpen(), Us1, Ac1);
        Cust.setAccount(Ac1);
        Cust.setUser(Us1);
        Shp.setAccount(Ac1);
        Ac.setShopingCart(Shp1);
        Users.add(Us1);
        Users.add(Us);
        accounts.add(Ac1);
        accounts.add(Ac);


        // for order id we will hold a counter
        //*
        //*
        int numoforders=0;


        int choice;
        while (true) {
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
            String id, password, ans, address, phone_num, email, billing_address;


            switch (choice) {

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
                    //create a new user by the information provided

                    java.sql.Date currdate = new java.sql.Date(System.currentTimeMillis());
                    Customer Cust2 = new Customer(id, new Address(address), phone_num, email, null);
                    User Us2 = new User(UserState.New, password, id, Cust2);
                    Account Ac2 = new Account(id, billing_address, false, 0, Cust2, null);
                    ShopingCart Shp2 = new ShopingCart(Ac2.getOpen(), Us2, Ac2);
                    Cust.setAccount(Ac2);
                    Cust.setUser(Us2);
                    Shp.setAccount(Ac2);
                    Ac.setShopingCart(Shp2);
                    Users.add(Us2);
                    accounts.add(Ac2);
                    for (int i = 0; i < Users.size(); i++) {
                        System.out.println(Users.get(i));
                    }


                    break;

                case 2:
                    System.out.println("Enter desired User ID to remove");
                    id = input.next();
                    //check if the User ID exists and remove it, need to check that the delete method works as it deletes
                    // also the account,customer and shopping cart associated with the deleted user
                    System.out.println(Users.size());
                    for (int i = 0; i < Users.size(); i++) {
                        if (Users.get(i).getLogin_id().equals(id)){
                            Users.get(i).delete();
                            Users.remove(i);

                        }

                    }
                    for (int i = 0; i < Users.size(); i++) {
                        System.out.println(Users.get(i));
                    }


                    break;

                case 3:
                    //login user
                    System.out.println("Enter User ID");
                    id = input.next();
                    System.out.println("Enter User password");
                    password = input.next();
                    //check if there is a user logged in,if there is automatically print that there is a user
                    //if no one is logged in check that the infromation given applies to one of our users
                    boolean found = false;
                    if (loggedInUser == null) {
                        for (int i = 0; i < Users.size(); i++) {
                            if (Users.get(i).getLogin_id().equals(id) && Users.get(i).getPassword().equals(password)) {
                                loggedInUser = Users.get(i);
                                found = true;
                                System.out.println("User succesfly logged in");
                            }
                        }
                        if (!found) {
                            System.out.println("User does not exist");
                        }
                    }
                    else {
                        System.out.println("User already logged in");
                    }



                    System.out.println(loggedInUser);

                    break;


                case 4:
                    // log out
                    System.out.println("Enter User ID");
                    id = input.next();
                    //check if user is logged in and if he is change LoggedINuser to null
                    if(loggedInUser.getLogin_id().equals(id)) {
                        loggedInUser=null;
                        System.out.println("User with id " + id + " succesfuly logged out");
                    }
                    else {
                        System.out.println("User with id" + id + " is not logged in");
                    }







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
