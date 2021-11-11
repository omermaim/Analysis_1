/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package com.company;

import java.sql.Date;
import java.util.*;
import com.company.*;

// line 18 "model.ump"
// line 125 "model.ump"
public class ShopingCart
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ShopingCart Attributes
  private Date created;

  //ShopingCart Associations
  private User user;
  private Account account;
  private List<LineItem> lineItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShopingCart(Date aCreated, User aUser, Account aAccount)
  {
    created = aCreated;
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create shopingCart due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aAccount == null || aAccount.getShopingCart() != null)
    {
      throw new RuntimeException("Unable to create ShopingCart due to aAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    account = aAccount;
    lineItems = new ArrayList<LineItem>();
  }

  public ShopingCart(Date aCreated, User aUser, String aIdForAccount, String aBilling_addressForAccount, boolean aIs_closedForAccount, Date aOpenForAccount, Date aClosedForAccount, int aBalanceForAccount, Customer aCustomerForAccount)
  {
    created = aCreated;
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create shopingCart due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    account = new Account(aIdForAccount, aBilling_addressForAccount, aIs_closedForAccount, aOpenForAccount, aClosedForAccount, aBalanceForAccount, aCustomerForAccount, this);
    lineItems = new ArrayList<LineItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCreated(Date aCreated)
  {
    boolean wasSet = false;
    created = aCreated;
    wasSet = true;
    return wasSet;
  }

  public Date getCreated()
  {
    return created;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetOne */
  public Account getAccount()
  {
    return account;
  }
  /* Code from template association_GetMany */
  public LineItem getLineItem(int index)
  {
    LineItem aLineItem = lineItems.get(index);
    return aLineItem;
  }

  public List<LineItem> getLineItems()
  {
    List<LineItem> newLineItems = Collections.unmodifiableList(lineItems);
    return newLineItems;
  }

  public int numberOfLineItems()
  {
    int number = lineItems.size();
    return number;
  }

  public boolean hasLineItems()
  {
    boolean has = lineItems.size() > 0;
    return has;
  }

  public int indexOfLineItem(LineItem aLineItem)
  {
    int index = lineItems.indexOf(aLineItem);
    return index;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setUser(User aNewUser)
  {
    boolean wasSet = false;
    if (aNewUser == null)
    {
      //Unable to setUser to null, as shopingCart must always be associated to a user
      return wasSet;
    }
    
    ShopingCart existingShopingCart = aNewUser.getShopingCart();
    if (existingShopingCart != null && !equals(existingShopingCart))
    {
      //Unable to setUser, the current user already has a shopingCart, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    User anOldUser = user;
    user = aNewUser;
    user.setShopingCart(this);

    if (anOldUser != null)
    {
      anOldUser.setShopingCart(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLineItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addLineItem(LineItem aLineItem)
  {
    boolean wasAdded = false;
    if (lineItems.contains(aLineItem)) { return false; }
    lineItems.add(aLineItem);
    if (aLineItem.indexOfShopingCart(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLineItem.addShopingCart(this);
      if (!wasAdded)
      {
        lineItems.remove(aLineItem);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeLineItem(LineItem aLineItem)
  {
    boolean wasRemoved = false;
    if (!lineItems.contains(aLineItem))
    {
      return wasRemoved;
    }

    int oldIndex = lineItems.indexOf(aLineItem);
    lineItems.remove(oldIndex);
    if (aLineItem.indexOfShopingCart(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLineItem.removeShopingCart(this);
      if (!wasRemoved)
      {
        lineItems.add(oldIndex,aLineItem);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLineItemAt(LineItem aLineItem, int index)
  {  
    boolean wasAdded = false;
    if(addLineItem(aLineItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLineItems()) { index = numberOfLineItems() - 1; }
      lineItems.remove(aLineItem);
      lineItems.add(index, aLineItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLineItemAt(LineItem aLineItem, int index)
  {
    boolean wasAdded = false;
    if(lineItems.contains(aLineItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLineItems()) { index = numberOfLineItems() - 1; }
      lineItems.remove(aLineItem);
      lineItems.add(index, aLineItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLineItemAt(aLineItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.setShopingCart(null);
    }
    Account existingAccount = account;
    account = null;
    if (existingAccount != null)
    {
      existingAccount.delete();
    }
    ArrayList<LineItem> copyOfLineItems = new ArrayList<LineItem>(lineItems);
    lineItems.clear();
    for(LineItem aLineItem : copyOfLineItems)
    {
      aLineItem.removeShopingCart(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "created" + "=" + (getCreated() != null ? !getCreated().equals(this)  ? getCreated().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null");
  }
}