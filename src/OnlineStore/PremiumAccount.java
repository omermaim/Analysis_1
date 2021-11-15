/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package OnlineStore;

import java.util.*;
import java.sql.Date;

// line 37 "model.ump"
// line 139 "model.ump"
public class PremiumAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PremiumAccount Associations
  private List<Product> products;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PremiumAccount(String aId, String aBilling_address, boolean aIs_closed, int aBalance, Customer aCustomer, ShopingCart aShopingCart)
  {
    super(aId, aBilling_address, aIs_closed, aBalance, aCustomer, aShopingCart);
    products = new ArrayList<Product>();
  }
  public PremiumAccount(String aId, String aBilling_address, boolean aIs_closed, Date aOpen, Date aClosed, int aBalance, String aIdForCustomer, Address aAddressForCustomer, String aPhoneForCustomer, String aEmailForCustomer, Date aCreatedForShopingCart, User aUserForShopingCart)
  {
    super(aId, aBilling_address, aIs_closed, aOpen, aClosed, aBalance,aIdForCustomer ,aAddressForCustomer,aPhoneForCustomer,aEmailForCustomer,aCreatedForShopingCart,aUserForShopingCart );
    products = new ArrayList<Product>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Product getProduct(int index)
  {
    Product aProduct = products.get(index);
    return aProduct;
  }

  public List<Product> getProducts()
  {
    List<Product> newProducts = Collections.unmodifiableList(products);
    return newProducts;
  }

  public int numberOfProducts()
  {
    int number = products.size();
    return number;
  }

  public boolean hasProducts()
  {
    boolean has = products.size() > 0;
    return has;
  }

  public int indexOfProduct(Product aProduct)
  {
    int index = products.indexOf(aProduct);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfProducts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addProduct(Product aProduct,int quantity,int price)
  {
    boolean wasAdded = false;
    if (products.contains(aProduct)) { return false; }
    PremiumAccount existingPremiumAccount = aProduct.getPremiumAccount();
    if (existingPremiumAccount == null)
    {
      aProduct.setPremiumAccount(this,quantity,price);
    }
    else if (!this.equals(existingPremiumAccount))
    {
      existingPremiumAccount.removeProduct(aProduct);
      addProduct(aProduct,quantity,price);
    }
    else
    {
      aProduct.setPrice(price);
      aProduct.setQuantity(quantity);
      products.add(aProduct);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeProduct(Product aProduct)
  {
    boolean wasRemoved = false;
    if (products.contains(aProduct))
    {
      products.remove(aProduct);
      aProduct.setPremiumAccount(null,0,0);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */




  @Override
  public String toString() {
    String str = super.toString() + "\n" + "Associated Products: " + "\n" + "****************" + "\n";
    for (int i = 0; i <getProducts().size() ; i++) {
      str = str  + getProducts().get(i).printObject() +", quantity remaining : " + getProducts().get(i).getQuantity() + ", the price of product for this account : " + getProducts().get(i).getPrice() + "\n";

    }
    return str;
  }

  public void delete()
  {
    while( !products.isEmpty() )
    {
      products.get(0).setPremiumAccount(null,0,0);
    }
    super.delete();
  }

}