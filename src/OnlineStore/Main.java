package OnlineStore;


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
        Account Ac1 = new PremiumAccount("Dana", null, false, 0, Cust1, null);
        ((PremiumAccount)Ac1).addProduct(products.get(0),50,100);
        ShopingCart Shp1 = new ShopingCart(Ac1.getOpen(), Us1, Ac1);
        Cust1.setAccount(Ac1);
        Cust1.setUser(Us1);
        Shp1.setAccount(Ac1);
        Ac1.setShopingCart(Shp1);
        Users.add(Us1);
        Users.add(Us);
        accounts.add(Ac1);
        accounts.add(Ac);


        // for order id we will hold a counter
        //*
        //*
        Integer numoforders=0;


        int choice;
        while (true) {
            System.out.println("Enter the number of the operation you would like to do:");
            System.out.println("*** Our machine is case-sensitive ***");;
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
            input.nextLine();




            String id, password, ans, address, phone_num, email, billing_address;

            switch (choice) {

                case 1:
                    //Add user

                    System.out.println("Enter desired User ID");
                    id = input.nextLine();
                    System.out.println("Enter desired password");
                    password = input.nextLine();
                    System.out.println("Enter customer address");
                    address = input.nextLine();
                    System.out.println("Enter customer phone number");
                    phone_num = input.nextLine();
                    System.out.println("Enter customer email");
                    email = input.nextLine();
                    System.out.println("Enter account billing address");
                    billing_address = input.nextLine();
                    System.out.println("Would you like to upgrade to premium account?\nIf you do enter Y");
                    ans = input.nextLine();
                    //create a new user by the information provided


                    Customer Cust2 = new Customer(id, new Address(address), phone_num, email, null);
                    User Us2 = new User(UserState.New, password, id, Cust2);
                    Account Ac2;
                    if (ans.equals("Y")){
                        Ac2 = new PremiumAccount(id, billing_address, false, 0, Cust2, null);
                    }
                    else{
                        Ac2= new Account(id, billing_address, false, 0, Cust2, null);
                    }
                    ShopingCart Shp2 = new ShopingCart(Ac2.getOpen(), Us2, Ac2);
                    Cust2.setAccount(Ac2);
                    Cust2.setUser(Us2);
                    Shp2.setAccount(Ac2);
                    Ac2.setShopingCart(Shp2);
                    Users.add(Us2);
                    accounts.add(Ac2);



                    break;

                case 2:
                    System.out.println("Enter desired User ID to remove");
                    id = input.nextLine();
                    //check if the User ID exists and remove it, need to check that the delete method works as it deletes
                    // also the account,customer and shopping cart associated with the deleted user

                    for (int i = 0; i < Users.size(); i++) {
                        if (Users.get(i).getLogin_id().equals(id)) {
                            if(loggedInUser==Users.get(i)){
                                loggedInUser=null;
                                System.out.println("you have the deleted the user that is logged in, currently ther is no User logged in");
                            }

                            Users.get(i).getCustomer().getAccount().delete();
                            accounts.remove(i);

                            Users.get(i).delete();
                            Users.remove(i);

                        }

                    }
                    break;

                case 3:
                    //login user
                    System.out.println("Enter User ID");
                    id = input.nextLine();
                    System.out.println("Enter User password");
                    password = input.nextLine();
                    //check if there is a user logged in,if there is automatically print that there is a user
                    //if no one is logged in check that the infromation given applies to one of our users
                    boolean found = false;
                    if (loggedInUser == null) {
                        for (int i = 0; i < Users.size(); i++) {
                            if (Users.get(i).getLogin_id().equals(id) && Users.get(i).getPassword().equals(password)) {
                                loggedInUser = Users.get(i);
                                found = true;
                                System.out.println("User " + loggedInUser.getLogin_id() + " successfly logged in");
                            }
                        }
                        if (!found) {
                            System.out.println("User does not exist");
                        }
                    } else {
                        System.out.println("User " + loggedInUser.getCustomer().getId() +  " already logged in");
                    }
                    break;


                case 4:
                    // log out
                    System.out.println("Enter User ID");
                    id = input.nextLine();
                    //check if user is logged in and if he is change LoggedINuser to null
                    if (loggedInUser.getLogin_id().equals(id)) {
                        loggedInUser = null;
                        System.out.println("User with id " + id + " succesfuly logged out");
                    } else {
                        System.out.println("User with id " + id + " is not logged in");
                    }


                case 5:
                    //create new order
                    //check if there is a user logged in and if there is go to his account and add the order

                    if (loggedInUser != null) {
                        System.out.println("Please enter address to send to ");
                        address = input.nextLine();
                        numoforders++;

                       loggedInUser.getCustomer().getAccount().addOrder(numoforders.toString(), new java.sql.Date(System.currentTimeMillis()), new Address(address));
                        System.out.println("the order number is " + numoforders.toString());



                        }

                    else {
                        System.out.println("no User logged in");
                    }






                            break;

                case 6:
                    //add a product to an order.
                    //first we find the supplier and put him in purchasefrom then we look at this suppliers products and look for the name the loggedin user entered.\
                    //then we find the spcefic order using the orderid given to us. then we check if he already has this product in his order,
                    //if he does we only increase the quantity by 1 of the lineitem already inside , but if he doesnt we create a new lineitem and insert it with quantity 1 and price=50(no reason for the specific price)
                    //at last we calculate the total price of the order and add it to the datamember of the same order.

                    if(loggedInUser!=null) {
                    System.out.println("Please enter Order ID ");
                    String orderid = input.nextLine();
                    System.out.println("Please enter UserId to purchase from ");
                    String userid = input.nextLine();
                    System.out.println("Please enter product to purchase ");
                    String product_name = input.nextLine();
                    boolean alreadyin=false;
                    PremiumAccount purchasefrom = null;
                    Product product=null;
                    int productnum = -1;
                    boolean foundaccount = false;
                    boolean foundproduct = false;
                    boolean outofstock = false;
                    int ordernum= -1;
                        for (int i = 0; i < accounts.size(); i++) {
                            if(accounts.get(i) instanceof PremiumAccount){
                                if (accounts.get(i).getId().equals(userid)) {
                                    purchasefrom = (PremiumAccount) accounts.get(i);
                                    foundaccount = true;
                                }
                            }
                        }
                        if(!foundaccount){
                            System.out.println("no such User");
                            break;
                        }

                        for (int j = 0; j <purchasefrom.getProducts().size() ; j++) {
                             if(purchasefrom.getProducts().get(j).getName().equals(product_name)){
                                 product = purchasefrom.getProducts().get(j);
                                 foundproduct = true;
                                 productnum = j;

                             }
                        }
                        if(!foundproduct){
                            System.out.println("this User does not sell this product");
                            break;
                        }
                        for (int j = 0; j <loggedInUser.getCustomer().getAccount().getOrders().size() ; j++) {
                                if(loggedInUser.getCustomer().getAccount().getOrders().get(j).getNumber().equals(orderid)){
                                    ordernum = j;
                                    for (int i = 0; i < loggedInUser.getCustomer().getAccount().getOrders().get(j).getLineItems().size(); i++) {
                                        if (loggedInUser.getCustomer().getAccount().getOrders().get(j).getLineItems().get(i).getProduct().getName().equals(product_name)){
                                            if(purchasefrom.getProduct(productnum).getQuantity()>0) {
                                                alreadyin = true;
                                                loggedInUser.getCustomer().getAccount().getOrders().get(j).getLineItems().get(i).setQuantity(loggedInUser.getCustomer().getAccount().getOrders().get(j).getLineItems().get(i).getQuantity() + 1);
                                                purchasefrom.getProduct(productnum).setQuantity(purchasefrom.getProduct(productnum).getQuantity() - 1);
                                            }
                                            else{
                                                outofstock = true;
                                            }

                                        }


                                    }
                                    if(!alreadyin) {
                                        if (purchasefrom.getProduct(productnum).getQuantity() > 0) {
                                            LineItem newitem = new LineItem(1, product.getPrice(), loggedInUser.getCustomer().getAccount().getOrders().get(j), product);
                                            purchasefrom.getProduct(productnum).setQuantity(purchasefrom.getProduct(productnum).getQuantity() - 1);
                                        }
                                    }


                                    }

                                if(outofstock){
                                    System.out.println("this product has run out for the premiuem account");
                                }


                                }
                        if(ordernum>=0) {
                            loggedInUser.getCustomer().getAccount().getOrders().get(ordernum).CalculateTotal();
                            System.out.println("the total price of your order is:");
                            System.out.println(loggedInUser.getCustomer().getAccount().getOrders().get(ordernum).getTotal());
                            System.out.println("the Premium accounts remainig quantity of product is : " + purchasefrom.getProduct(productnum).getQuantity());
                        }
                        else{
                            System.out.println("no such order for this User");
                        }

                        }


                    else{
                        System.out.println("No User is logged in");
                    }

                            break;

                case 7:
                    //print the order, look at order tostring to see
                if(loggedInUser!=null) {
                    if (loggedInUser.getCustomer().getAccount().getOrders().size() > 0) {
                        loggedInUser.getCustomer().getAccount().getOrders().get(loggedInUser.getCustomer().getAccount().getOrders().size() - 1).CalculateTotal();
                        System.out.println(loggedInUser.getCustomer().getAccount().getOrders().get(loggedInUser.getCustomer().getAccount().getOrders().size() - 1).print());
                    } else {
                        System.out.println("User has no orders");
                    }
                }
                else{
                    System.out.println("No user logged in");
                }
                            break;

                case 8:
                            //Link Product *Product_Name* *Price* *Quantity*
                    // we need to check why they want to recieve price and quantity, they ask for a link between product to premieum account.


                if(loggedInUser!=null) {
                    if (loggedInUser.getCustomer().getAccount() instanceof PremiumAccount) {
                        System.out.println("Please enter the product name ");
                        String productname = input.nextLine();
                        System.out.println("Please enter Price");
                        String price = input.nextLine();
                        System.out.println("Please enter Quantity");
                        String quantity = input.nextLine();
                        for (int i = 0; i < products.size(); i++) {
                            if (products.get(i).getName().equals(productname)) {
                                ((PremiumAccount) loggedInUser.getCustomer().getAccount()).addProduct(products.get(i),Integer.parseInt(quantity),Integer.parseInt(price));
                                System.out.println("link succesful");
                            }

                        }


                    } else {
                        System.out.println("We are sorry, you are not a Premiem User.");
                    }
                }
                else{
                    System.out.println("No User logged in");
                }

                            break;

                case 9:
                            //Add Product
                    System.out.println("Please enter the new products name ");
                    String newname = input.nextLine();
                    System.out.println("Please enter the supplier that provides it");
                    String sup_name = input.nextLine();
                    boolean found_sup = false;
                    for (int i = 0; i < suppliers.size() ; i++) {
                        if(suppliers.get(i).getName().equals(sup_name)){
                            found_sup=true;
                            Product newproduct = new Product(newname,newname,suppliers.get(i));
                            products.add(newproduct);
                            System.out.println("product succesfly added to supplier " + suppliers.get(i).getName());
                        }

                    }
                    if(!found_sup){
                        System.out.println("There is no such supplier");
                    }


                            break;

                case 10:
                            //Delete product
                    System.out.println("Please enter the product you want to delete");
                    String prod_delete = input.nextLine();
                    boolean found_del = false;
                    for (int i = 0; i < products.size() ; i++) {
                        if(products.get(i).getName().equals(prod_delete)){
                            found_del = true;
                            products.get(i).delete();

                            products.remove(i);
                            System.out.println("Product deleted");
                        }

                    }
                    if(!found_del){
                        System.out.println("product not found");
                    }

                    break;

                case 11:
                            //show all objects
                    String final_print = "";
                    final_print = final_print + "Users: " + "\n" + "******************************" + "\n";
                    for (int i = 0; i < Users.size(); i++) {
                        final_print= final_print + Users.get(i).printObject() + "\n" + "******************************" + "\n";
                    }
                    final_print = final_print + "Suppliers: " + "\n" + "******************************" + "\n";
                    for (int i = 0; i < suppliers.size(); i++) {
                        final_print= final_print + suppliers.get(i).printObject() + "\n" + "******************************" + "\n";

                    }
                    final_print = final_print + "Products: " + "\n" + "******************************" + "\n";
                    for (int i = 0; i < products.size(); i++) {
                        final_print= final_print + products.get(i).printObject()+ "\n" + "******************************" + "\n";

                    }
                    final_print = final_print + "Orders: " + "\n" + "******************************" + "\n";
                    for (int i = 0; i < accounts.size(); i++) {
                        for (int j = 0; j < accounts.get(i).getOrders().size(); j++) {
                            accounts.get(i).getOrders().get(j).CalculateTotal();
                            final_print= final_print + accounts.get(i).getOrders().get(j).printObject()+ "\n" + "******************************" + "\n";
                        }
                    }
                    final_print = final_print + "LineItems: " + "\n" + "******************************" + "\n";
                    for (int i = 0; i < accounts.size(); i++) {
                        for (int j = 0; j <accounts.get(i).getOrders().size() ; j++) {
                            for (int k = 0; k < accounts.get(i).getOrders().get(j).getLineItems().size(); k++) {
                                final_print= final_print + accounts.get(i).getOrders().get(j).getLineItems().get(k).printObject() + "\n" + "******************************" + "\n";

                            }

                        }

                    }

                    final_print = final_print + "Accounts: " + "\n" + "******************************" + "\n";
                    for (int i = 0; i < accounts.size(); i++) {
                        final_print= final_print + accounts.get(i).printObject()+ "\n" + "******************************" + "\n";
                    }
                    final_print = final_print + "ShoppingCarts: " + "\n" + "******************************" + "\n";
                    for (int i = 0; i < accounts.size(); i++) {
                        if(accounts.get(i).getShopingCart()!=null) {
                            final_print = final_print + accounts.get(i).getShopingCart().printObject() + "\n" + "******************************" + "\n";
                        }
                    }
                    final_print = final_print + "Customers: " + "\n" + "******************************" + "\n";
                    for (int i = 0; i < Users.size(); i++) {
                        final_print= final_print + Users.get(i).getCustomer().printObject()+ "\n" + "******************************" + "\n";

                    }
                    System.out.println(final_print);

                        break;

                case 12:
                            //show object
                    System.out.println("Enter desired Unique System ID to show");
                    String Obj_id = input.nextLine();
                    boolean obj_found = false;
                    String final_obj = "";

                    for (int i = 0; i < Users.size(); i++) {
                        if(Integer.toHexString(System.identityHashCode(Users.get(i))).equals(Obj_id)){
                            final_obj = final_obj + Users.get(i).toString();
                            obj_found=true;

                        }
                    }

                    for (int i = 0; i < suppliers.size(); i++) {
                        if(Integer.toHexString(System.identityHashCode(suppliers.get(i))).equals(Obj_id)) {
                            final_obj = final_obj + suppliers.get(i).toString();
                            obj_found=true;
                        }

                    }

                    for (int i = 0; i < products.size(); i++) {
                        if(Integer.toHexString(System.identityHashCode(products.get(i))).equals(Obj_id)) {
                            final_obj = final_obj + products.get(i).toString();
                            obj_found=true;
                        }

                    }

                    for (int i = 0; i < accounts.size(); i++) {
                        for (int j = 0; j < accounts.get(i).getOrders().size(); j++) {
                            accounts.get(i).getOrders().get(j).CalculateTotal();
                            if (Integer.toHexString(System.identityHashCode( accounts.get(i).getOrders().get(j))).equals(Obj_id)) {
                                final_obj = final_obj + accounts.get(i).getOrders().get(j).toString();
                                obj_found = true;
                            }
                        }
                    }

                    for (int i = 0; i <accounts.size(); i++) {
                        for (int j = 0; j <accounts.get(i).getOrders().size() ; j++) {
                            for (int k = 0; k < accounts.get(i).getOrders().get(j).getLineItems().size(); k++) {
                                if (Integer.toHexString(System.identityHashCode(accounts.get(i).getOrders().get(j).getLineItems().get(k))).equals(Obj_id)) {
                                    final_obj = final_obj + accounts.get(i).getOrders().get(j).getLineItems().get(k).toString();
                                    obj_found = true;
                                }
                            }

                        }

                    }


                    for (int i = 0; i < accounts.size(); i++) {
                        if(Integer.toHexString(System.identityHashCode(accounts.get(i))).equals(Obj_id)) {
                            final_obj = final_obj + accounts.get(i).toString();
                            obj_found=true;
                        }
                    }

                    for (int i = 0; i < accounts.size(); i++) {
                        if(accounts.get(i).getShopingCart()!=null) {
                            if(Integer.toHexString(System.identityHashCode(accounts.get(i).getShopingCart())).equals(Obj_id)) {
                                final_obj = final_obj + accounts.get(i).getShopingCart().toString();
                                obj_found=true;
                            }
                        }
                    }

                    for (int i = 0; i < Users.size(); i++) {
                        if(Integer.toHexString(System.identityHashCode(Users.get(i).getCustomer())).equals(Obj_id)) {
                            final_obj = final_obj + Users.get(i).getCustomer().toString();
                            obj_found=true;
                        }

                    }
                    if(obj_found){
                        System.out.println(final_obj);
                    }
                    else{
                        System.out.println("there is no Object with this id in the system");
                    }


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
