/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package OnlineStore;

import java.sql.Date;

// line 73 "model.ump"
// line 164 "model.ump"
public class DelayedPayment extends Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DelayedPayment Attributes
  private Date paymentDate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DelayedPayment(String aId, Date aPaid, float aTotal, String aDetails, Order aOrder, Date aPaymentDate)
  {
    super(aId, aPaid, aTotal, aDetails, aOrder);
    paymentDate = aPaymentDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPaymentDate(Date aPaymentDate)
  {
    boolean wasSet = false;
    paymentDate = aPaymentDate;
    wasSet = true;
    return wasSet;
  }

  public Date getPaymentDate()
  {
    return paymentDate;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "paymentDate" + "=" + (getPaymentDate() != null ? !getPaymentDate().equals(this)  ? getPaymentDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}