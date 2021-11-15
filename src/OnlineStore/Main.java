package OnlineStore;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Machine\n");
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        //create 3 data structers for products, accounts, users, and suppliers, and automatically adding users that they want us to add
        //*
        //*

        ArrayList<Order>orders = new ArrayList<>();
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
        Ac1.setShopingCart(Shp1);
        Users.add(Us1);
        Users.add(Us);
        accounts.add(Ac1);
        accounts.add(Ac);


        // for order id we will hold a counter
        //*
        //*
        Integer numoforders=0;


        String choice;
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

            choice = input.next();
            String id, password, ans, address, phone_num, email, billing_address;

            switch (Integer.parseInt(choice)) {

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
                    id = input.next();
                    //check if the User ID exists and remove it, need to check that the delete method works as it deletes
                    // also the account,customer and shopping cart associated with the deleted user

                    for (int i = 0; i < Users.size(); i++) {
                        if (Users.get(i).getLogin_id().equals(id)) {
                            if(loggedInUser==Users.get(i)){
                                loggedInUser=null;
                                System.out.println("you have the deleted the user that is logged in, currently ther is no User logged in");
                            }
                            Users.get(i).getCustomer().getAccount().setClosed(new java.sql.Date(System.currentTimeMillis()));
                            Users.get(i).getCustomer().getAccount().setIs_closed(true);
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
                                System.out.println("User " + loggedInUser.getLogin_id() + " successfly logged in");
                            }
                        }
                        if (!found) {
                            System.out.println("User does not exist");
                        }
                    } else {
                        System.out.println("User already logged in");
                    }
                    break;


                case 4:
                    // log out
                    System.out.println("Enter User ID");
                    id = input.next();
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
                        address = input.next();
                        numoforders++;

                        Order neworder = loggedInUser.getCustomer().getAccount().addOrder(numoforders.toString(), new java.sql.Date(System.currentTimeMillis()), new Address(address));
                        orders.add(neworder);
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
                    String orderid = input.next();
                    System.out.println("Please enter UserId to purchase from ");
                    String userid = input.next();
                    System.out.println("Please enter product to purchase ");
                    String product_name = input.next();
                    boolean alreadyin=false;
                    Supplier purchasefrom = null;
                    Product product=null;
                        for (int i = 0; i < suppliers.size(); i++) {
                            if (suppliers.get(i).getId().equals(userid)) {
                                purchasefrom = suppliers.get(i);
                            }
                        }

                        for (int j = 0; j <purchasefrom.getProducts().size() ; j++) {
                             if(purchasefrom.getProducts().get(j).getName().equals(product_name)){
                                 product = purchasefrom.getProducts().get(j);

                             }
                        }
                        for (int j = 0; j <loggedInUser.getCustomer().getAccount().getOrders().size() ; j++) {
                                if(loggedInUser.getCustomer().getAccount().getOrders().get(j).getNumber().equals(orderid)){
                                    for (int i = 0; i < loggedInUser.getCustomer().getAccount().getOrders().get(j).getLineItems().size(); i++) {
                                        if (loggedInUser.getCustomer().getAccount().getOrders().get(j).getLineItems().get(i).getProduct().getName().equals(product_name)){
                                            alreadyin=true;
                                            loggedInUser.getCustomer().getAccount().getOrders().get(j).getLineItems().get(i).setQuantity( loggedInUser.getCustomer().getAccount().getOrders().get(j).getLineItems().get(i).getQuantity()+1);

                                        }

                                    }
                                    if(!alreadyin) {
                                        LineItem newitem = new LineItem(1, 50, loggedInUser.getCustomer().getAccount().getOrders().get(j), product);

                                    }
                                    loggedInUser.getCustomer().getAccount().getOrders().get(j).CalculateTotal();
                                    System.out.println(loggedInUser.getCustomer().getAccount().getOrders().get(j).getTotal());

                                }

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
                        String productname = input.next();
                        System.out.println("Please enter Price");
                        String price = input.next();
                        System.out.println("Please enter Quantity");
                        String quantity = input.next();
                        for (int i = 0; i < products.size(); i++) {
                            if (products.get(i).getName().equals(productname)) {
                                ((PremiumAccount) loggedInUser.getCustomer().getAccount()).addProduct(products.get(i));
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
                    String newname = input.next();
                    System.out.println("Please enter the supplier that provides it");
                    String sup_name = input.next();
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
                    String prod_delete = input.next();
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
                    for (int i = 0; i < orders.size(); i++) {
                        orders.get(i).CalculateTotal();
                        final_print= final_print + orders.get(i).printObject()+ "\n" + "******************************" + "\n";
                    }
                    final_print = final_print + "LineItems: " + "\n" + "******************************" + "\n";
                    for (int i = 0; i < orders.size(); i++) {
                        for (int j = 0; j <orders.get(i).getLineItems().size() ; j++) {
                            final_print= final_print + orders.get(i).getLineItems().get(j).printObject() + "\n" + "******************************" + "\n";
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
                    String Obj_id = input.next();
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

                    for (int i = 0; i < orders.size(); i++) {
                        orders.get(i).CalculateTotal();
                        if(Integer.toHexString(System.identityHashCode(orders.get(i))).equals(Obj_id)) {
                            final_obj = final_obj + orders.get(i).toString();
                            obj_found=true;
                        }
                    }

                    for (int i = 0; i < orders.size(); i++) {
                        for (int j = 0; j <orders.get(i).getLineItems().size() ; j++) {
                            if (Integer.toHexString(System.identityHashCode(orders.get(i).getLineItems().get(j))).equals(Obj_id)) {
                                final_obj = final_obj + orders.get(i).getLineItems().get(j).toString();
                                obj_found=true;
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
