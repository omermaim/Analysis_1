package com.company;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;

// line 9 "model.ump"
// line 119 "model.ump"
public class Customer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private String id;
  private Address address;
  private String phone;
  private String email;

  //Customer Associations
  private User user;
  private Account account;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aId, Address aAddress, String aPhone, String aEmail, Account aAccount)
  {
    id = aId;
    address = aAddress;
    phone = aPhone;
    email = aEmail;
   // if (aAccount == null || aAccount.getCustomer() != null)
    //{
    //  throw new RuntimeException("Unable to create Customer due to aAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    //}
    account = aAccount;
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

  public boolean setAddress(Address aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }
  public String printObject(){
    return this.getClass() + " " + this.getId() + " " +  Integer.toHexString(System.identityHashCode(this));
  }

  public boolean setPhone(String aPhone)
  {
    boolean wasSet = false;
    phone = aPhone;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }

  public Address getAddress()
  {
    return address;
  }

  public String getPhone()
  {
    return phone;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }

  public boolean hasUser()
  {
    boolean has = user != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Account getAccount()
  {
    return account;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setUser(User aNewUser)
  {
    boolean wasSet = false;
    if (user != null && !user.equals(aNewUser) && equals(user.getCustomer()))
    {
      //Unable to setUser, as existing user would become an orphan
      return wasSet;
    }

    user = aNewUser;
    Customer anOldCustomer = aNewUser != null ? aNewUser.getCustomer() : null;

    if (!this.equals(anOldCustomer))
    {
      if (anOldCustomer != null)
      {
        anOldCustomer.user = null;
      }
      if (user != null)
      {
        user.setCustomer(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.delete();
    }
    Account existingAccount = account;
    account = null;
    if (existingAccount != null)
    {
      existingAccount.delete();
    }
  }


  public String toString()
  {
    return super.toString() + System.getProperties().getProperty("line.separator") +
            "id" + ":" + getId()+ System.getProperties().getProperty("line.separator") +
            "phone" + ":" + getPhone()+ System.getProperties().getProperty("line.separator") +
            "email" + ":" + getEmail() + System.getProperties().getProperty("line.separator") +
            "address" + "=" + (getAddress() != null ? !getAddress().equals(this)  ? getAddress().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null");
  }

  public void setAccount(Account account) {
    this.account = account;
  }
}