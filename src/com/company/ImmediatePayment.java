/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package com.company;

import java.sql.Date;

// line 80 "model.ump"
// line 169 "model.ump"
public class ImmediatePayment extends Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ImmediatePayment Attributes
  private boolean phoneConfirmation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ImmediatePayment(String aId, Date aPaid, float aTotal, String aDetails, Order aOrder, boolean aPhoneConfirmation)
  {
    super(aId, aPaid, aTotal, aDetails, aOrder);
    phoneConfirmation = aPhoneConfirmation;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneConfirmation(boolean aPhoneConfirmation)
  {
    boolean wasSet = false;
    phoneConfirmation = aPhoneConfirmation;
    wasSet = true;
    return wasSet;
  }

  public boolean getPhoneConfirmation()
  {
    return phoneConfirmation;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "phoneConfirmation" + ":" + getPhoneConfirmation()+ "]";
  }
}