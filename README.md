Online_store
welcome to the Online Store.
in this project i have implemented an online store system using java based on this class diagram:
this online store starts with 2 users and 2 products.(the information of each object can be seen by clicking 10 in the main menu).
also the only way to buy a product is to buy from a user that holds the product you want(only Premieum accounts can sell products)
the Online stores goal is to make the experience of online shopping easy to use and creates an easy way for users to buy and sell products to other users.



![image](https://user-images.githubusercontent.com/81911093/145676300-f4abf45a-c2c7-4bbe-9f9e-d8f07acb547d.png)

once you run the program you will recieve a menu with this options:
1. Add user.
   input: *Login_id* 
   after entering your login id you will need to enter a password for your user and details of the user including if he is a premieum account or not.
2. Remove User.
   input:*login_id*
   after entering the id the system will remove the user if the it exists
3. Login user.
   input: *Login_id*   *password*
   if the login id and the password match this user will be logged in to the system
   
4.Logout User.
  input: *login_id*
  log out the user
  
5.Create new order
  input: *address*
  creates a new order for the logged in user that will be shipped to the adress that was inserted. the order number will be shown for the user to use
  
6. Add product to order
   input: *Order_ID* *Login_ID* *Product Name*
   adds the product that has the name to the order that has the order id that were inserted. the login id is the id of the user you want to buy from
   
7.Display order
  displays the last order of the user that is logged in
  
8.Link Product 
  input: *Product_Name* *Price* *Quantity*
  links a product to a Premiuem account, while price and quantity are how much the premieum account user selling the product wants to price this product and 
  how many units he wants to buy from the supplier to sell
  
9.Add Product
  input: *Product_Name* *Supplier_Name*
  adds product with the product name inserted to the system. the supplier name is the name of the supplier in the system that has these products.
  
10. ShowAllObjects 
    shows all objects in the system including there unique id that can be used in option 11
    
11.ShowObjectId 
   input :*id*
   shows the details of the object with the unique id inserted.





