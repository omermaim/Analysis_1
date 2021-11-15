/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package OnlineStore;

import java.util.*;

// line 86 "model.ump"
// line 174 "model.ump"
public class Product
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Product Attributes
  private String id;
  private String name;

  //Product Associations
  private Supplier supplier;
  private PremiumAccount premiumAccount;
  private List<LineItem> lineItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Product(String aId, String aName, Supplier aSupplier)
  {
    id = aId;
    name = aName;
    boolean didAddSupplier = setSupplier(aSupplier);
    if (!didAddSupplier)
    {
      throw new RuntimeException("Unable to create product due to supplier. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    lineItems = new ArrayList<LineItem>();
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

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public Supplier getSupplier()
  {
    return supplier;
  }
  /* Code from template association_GetOne */
  public PremiumAccount getPremiumAccount()
  {
    return premiumAccount;
  }

  public boolean hasPremiumAccount()
  {
    boolean has = premiumAccount != null;
    return has;
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
  /* Code from template association_SetOneToMany */
  public boolean setSupplier(Supplier aSupplier)
  {
    boolean wasSet = false;
    if (aSupplier == null)
    {
      return wasSet;
    }

    Supplier existingSupplier = supplier;
    supplier = aSupplier;
    if (existingSupplier != null && !existingSupplier.equals(aSupplier))
    {
      existingSupplier.removeProduct(this);
    }
    supplier.addProduct(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPremiumAccount(PremiumAccount aPremiumAccount)
  {
    boolean wasSet = false;
    PremiumAccount existingPremiumAccount = premiumAccount;
    premiumAccount = aPremiumAccount;
    if (existingPremiumAccount != null && !existingPremiumAccount.equals(aPremiumAccount))
    {
      existingPremiumAccount.removeProduct(this);
    }
    if (aPremiumAccount != null)
    {
      aPremiumAccount.addProduct(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLineItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LineItem addLineItem(int aQuantity, int aPrice, Order aOrder)
  {
    return new LineItem(aQuantity, aPrice, aOrder, this);
  }

  public boolean addLineItem(LineItem aLineItem)
  {
    boolean wasAdded = false;
    if (lineItems.contains(aLineItem)) { return false; }
    Product existingProduct = aLineItem.getProduct();
    boolean isNewProduct = existingProduct != null && !this.equals(existingProduct);
    if (isNewProduct)
    {
      aLineItem.setProduct(this);
    }
    else
    {
      lineItems.add(aLineItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLineItem(LineItem aLineItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aLineItem, as it must always have a product
    if (!this.equals(aLineItem.getProduct()))
    {
      lineItems.remove(aLineItem);
      wasRemoved = true;
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
  public String printObject(){
    return this.getClass() + " " + this.getId() + " " + "Unique System Id : " +  Integer.toHexString(System.identityHashCode(this));
  }

  public void delete()
  {
    Supplier placeholderSupplier = supplier;
    this.supplier = null;
    if(placeholderSupplier != null)
    {
      placeholderSupplier.removeProduct(this);
    }
    if (premiumAccount != null)
    {
      PremiumAccount placeholderPremiumAccount = premiumAccount;
      this.premiumAccount = null;
      placeholderPremiumAccount.removeProduct(this);
    }
    for(int i=lineItems.size(); i > 0; i--)
    {
      LineItem aLineItem = lineItems.get(i - 1);
      aLineItem.delete();
    }
  }


  public String toString()
  {
    String str = "";
    str = str + super.toString() +
            "id" + ": " + getId()+ System.getProperties().getProperty("line.separator") +
            "name" + ": " + getName() + System.getProperties().getProperty("line.separator") +
            "supplier = "+(getSupplier()!=null?Integer.toHexString(System.identityHashCode(getSupplier())):"null") + System.getProperties().getProperty("line.separator") +
            "premiumAccount = "+(getPremiumAccount()!=null?Integer.toHexString(System.identityHashCode(getPremiumAccount())):"null") + System.getProperties().getProperty("line.separator") +
             "LineItems" + ": " + "\n" + "**************";
    for (int i = 0; i < getLineItems().size(); i++) {
      str = str + System.getProperties().getProperty("line.separator") + getLineItems().get(i).printObject();
    }
    return  str;
  }
}