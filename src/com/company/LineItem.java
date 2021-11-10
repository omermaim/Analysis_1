/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package com.company;

import java.util.*;

// line 55 "model.ump"
// line 151 "model.ump"
public class LineItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LineItem Attributes
  private int quantity;
  private int price;

  //LineItem Associations
  private List<ShopingCart> shopingCarts;
  private Order order;
  private Product product;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LineItem(int aQuantity, int aPrice, Order aOrder, Product aProduct)
  {
    quantity = aQuantity;
    price = aPrice;
    shopingCarts = new ArrayList<ShopingCart>();
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create lineItem due to order. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddProduct = setProduct(aProduct);
    if (!didAddProduct)
    {
      throw new RuntimeException("Unable to create lineItem due to product. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public int getPrice()
  {
    return price;
  }
  /* Code from template association_GetMany */
  public ShopingCart getShopingCart(int index)
  {
    ShopingCart aShopingCart = shopingCarts.get(index);
    return aShopingCart;
  }

  public List<ShopingCart> getShopingCarts()
  {
    List<ShopingCart> newShopingCarts = Collections.unmodifiableList(shopingCarts);
    return newShopingCarts;
  }

  public int numberOfShopingCarts()
  {
    int number = shopingCarts.size();
    return number;
  }

  public boolean hasShopingCarts()
  {
    boolean has = shopingCarts.size() > 0;
    return has;
  }

  public int indexOfShopingCart(ShopingCart aShopingCart)
  {
    int index = shopingCarts.indexOf(aShopingCart);
    return index;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_GetOne */
  public Product getProduct()
  {
    return product;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShopingCarts()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addShopingCart(ShopingCart aShopingCart)
  {
    boolean wasAdded = false;
    if (shopingCarts.contains(aShopingCart)) { return false; }
    shopingCarts.add(aShopingCart);
    if (aShopingCart.indexOfLineItem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aShopingCart.addLineItem(this);
      if (!wasAdded)
      {
        shopingCarts.remove(aShopingCart);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeShopingCart(ShopingCart aShopingCart)
  {
    boolean wasRemoved = false;
    if (!shopingCarts.contains(aShopingCart))
    {
      return wasRemoved;
    }

    int oldIndex = shopingCarts.indexOf(aShopingCart);
    shopingCarts.remove(oldIndex);
    if (aShopingCart.indexOfLineItem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aShopingCart.removeLineItem(this);
      if (!wasRemoved)
      {
        shopingCarts.add(oldIndex,aShopingCart);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addShopingCartAt(ShopingCart aShopingCart, int index)
  {  
    boolean wasAdded = false;
    if(addShopingCart(aShopingCart))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShopingCarts()) { index = numberOfShopingCarts() - 1; }
      shopingCarts.remove(aShopingCart);
      shopingCarts.add(index, aShopingCart);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShopingCartAt(ShopingCart aShopingCart, int index)
  {
    boolean wasAdded = false;
    if(shopingCarts.contains(aShopingCart))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShopingCarts()) { index = numberOfShopingCarts() - 1; }
      shopingCarts.remove(aShopingCart);
      shopingCarts.add(index, aShopingCart);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShopingCartAt(aShopingCart, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    if (aOrder == null)
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeLineItem(this);
    }
    order.addLineItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setProduct(Product aProduct)
  {
    boolean wasSet = false;
    if (aProduct == null)
    {
      return wasSet;
    }

    Product existingProduct = product;
    product = aProduct;
    if (existingProduct != null && !existingProduct.equals(aProduct))
    {
      existingProduct.removeLineItem(this);
    }
    product.addLineItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<ShopingCart> copyOfShopingCarts = new ArrayList<ShopingCart>(shopingCarts);
    shopingCarts.clear();
    for(ShopingCart aShopingCart : copyOfShopingCarts)
    {
      aShopingCart.removeLineItem(this);
    }
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeLineItem(this);
    }
    Product placeholderProduct = product;
    this.product = null;
    if(placeholderProduct != null)
    {
      placeholderProduct.removeLineItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "product = "+(getProduct()!=null?Integer.toHexString(System.identityHashCode(getProduct())):"null");
  }
}