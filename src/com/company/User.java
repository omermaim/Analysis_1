/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package com.company;

// line 2 "model.ump"
// line 114 "model.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private UserState state;
  private String password;
  private String login_id;

  //User Associations
  private Customer customer;
  private ShopingCart shopingCart;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(UserState aState, String aPassword, String aLogin_id, Customer aCustomer)
  {
    state = aState;
    password = aPassword;
    login_id = aLogin_id;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create user due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setState(UserState aState)
  {
    boolean wasSet = false;
    state = aState;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setLogin_id(String aLogin_id)
  {
    boolean wasSet = false;
    login_id = aLogin_id;
    wasSet = true;
    return wasSet;
  }

  public UserState getState()
  {
    return state;
  }

  public String getPassword()
  {
    return password;
  }

  public String getLogin_id()
  {
    return login_id;
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

  public boolean hasShopingCart()
  {
    boolean has = shopingCart != null;
    return has;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setCustomer(Customer aNewCustomer)
  {
    boolean wasSet = false;
    if (aNewCustomer == null)
    {
      //Unable to setCustomer to null, as user must always be associated to a customer
      return wasSet;
    }
    
    User existingUser = aNewCustomer.getUser();
    if (existingUser != null && !equals(existingUser))
    {
      //Unable to setCustomer, the current customer already has a user, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Customer anOldCustomer = customer;
    customer = aNewCustomer;
    customer.setUser(this);

    if (anOldCustomer != null)
    {
      anOldCustomer.setUser(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setShopingCart(ShopingCart aNewShopingCart)
  {
    boolean wasSet = false;
    if (shopingCart != null && !shopingCart.equals(aNewShopingCart) && equals(shopingCart.getUser()))
    {
      //Unable to setShopingCart, as existing shopingCart would become an orphan
      return wasSet;
    }

    shopingCart = aNewShopingCart;
    User anOldUser = aNewShopingCart != null ? aNewShopingCart.getUser() : null;

    if (!this.equals(anOldUser))
    {
      if (anOldUser != null)
      {
        anOldUser.shopingCart = null;
      }
      if (shopingCart != null)
      {
        shopingCart.setUser(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.setUser(null);
    }
    ShopingCart existingShopingCart = shopingCart;
    shopingCart = null;
    if (existingShopingCart != null)
    {
      existingShopingCart.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "password" + ":" + getPassword()+ "," +
            "login_id" + ":" + getLogin_id()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "state" + "=" + (getState() != null ? !getState().equals(this)  ? getState().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "shopingCart = "+(getShopingCart()!=null?Integer.toHexString(System.identityHashCode(getShopingCart())):"null");
  }
}