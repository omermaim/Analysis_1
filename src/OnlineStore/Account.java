package OnlineStore;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

import java.sql.Date;
import java.util.*;

// line 24 "model.ump"
// line 131 "model.ump"
public class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String id;
  private String billing_address;
  private boolean is_closed;
  private Date open;
  private Date closed;
  private int balance;

  //Account Associations
  private Customer customer;
  private ShopingCart shopingCart;
  private List<Order> orders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aId, String aBilling_address, boolean aIs_closed, int aBalance, Customer aCustomer, ShopingCart aShopingCart)
  {
    id = aId;
    billing_address = aBilling_address;
    is_closed = aIs_closed;
    open = new java.sql.Date(System.currentTimeMillis());
    balance = aBalance;
    orders = new ArrayList<Order>();
    closed = null;
    customer=aCustomer;
    shopingCart=aShopingCart;
  }

  public Account(String aId, String aBilling_address, boolean aIs_closed, Date aOpen, Date aClosed, int aBalance, String aIdForCustomer, Address aAddressForCustomer, String aPhoneForCustomer, String aEmailForCustomer, Date aCreatedForShopingCart, User aUserForShopingCart)
  {
    id = aId;
    billing_address = aBilling_address;
    is_closed = aIs_closed;
    open = aOpen;
    closed = aClosed;
    balance = aBalance;
    customer = new Customer(aIdForCustomer, aAddressForCustomer, aPhoneForCustomer, aEmailForCustomer, this);
    shopingCart = new ShopingCart(aCreatedForShopingCart, aUserForShopingCart, this);
    orders = new ArrayList<Order>();
  }


  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setBilling_address(String aBilling_address)
  {
    boolean wasSet = false;
    billing_address = aBilling_address;
    wasSet = true;
    return wasSet;
  }

  public boolean setIs_closed(boolean aIs_closed)
  {
    boolean wasSet = false;
    is_closed = aIs_closed;
    wasSet = true;
    return wasSet;
  }

  public boolean setOpen(Date aOpen)
  {
    boolean wasSet = false;
    open = aOpen;
    wasSet = true;
    return wasSet;
  }

  public boolean setClosed(Date aClosed)
  {
    boolean wasSet = false;
    closed = aClosed;
    wasSet = true;
    return wasSet;
  }

  public boolean setBalance(int aBalance)
  {
    boolean wasSet = false;
    balance = aBalance;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }

  public String getBilling_address()
  {
    return billing_address;
  }

  public boolean getIs_closed()
  {
    return is_closed;
  }

  public Date getOpen()
  {
    return open;
  }

  public Date getClosed()
  {
    return closed;
  }

  public int getBalance()
  {
    return balance;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public ShopingCart getShopingCart()
  {
    return shopingCart;
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(String aNumber, Date aShipped, Address aShip_to)
  {
    return new Order(aNumber, aShipped, aShip_to, this);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Account existingAccount = aOrder.getAccount();
    boolean isNewAccount = existingAccount != null && !this.equals(existingAccount);
    if (isNewAccount)
    {
      aOrder.setAccount(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a account
    if (!this.equals(aOrder.getAccount()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }
  public String printObject(){
    return this.getClass() + " " + this.getId() + " " +  "Unique System Id : " + Integer.toHexString(System.identityHashCode(this));
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.delete();
    }
    ShopingCart existingShopingCart = shopingCart;
    shopingCart = null;
    if (existingShopingCart != null)
    {
      existingShopingCart.delete();
    }
    for(int i=orders.size(); i > 0; i--)
    {
      Order aOrder = orders.get(i - 1);
      aOrder.delete();
    }
  }


  public String toString()
  {
    return super.toString() + System.getProperties().getProperty("line.separator") +
            "id" + ": " + getId()+ System.getProperties().getProperty("line.separator") +
            "billing_address" + ": " + getBilling_address()+ System.getProperties().getProperty("line.separator") +
            "is_closed" + ": " + getIs_closed()+ System.getProperties().getProperty("line.separator") +
            "balance" + ": " + getBalance()+  System.getProperties().getProperty("line.separator") +
            "open" + " = " + (getOpen() != null ? !getOpen().equals(this)  ? getOpen().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "closed" + " = " + (getClosed() != null ? !getClosed().equals(this)  ? getClosed().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "shopingCart = "+(getShopingCart()!=null?Integer.toHexString(System.identityHashCode(getShopingCart())):"null");
  }

  public void setShopingCart(ShopingCart shopingCart) {
    this.shopingCart = shopingCart;
  }
}