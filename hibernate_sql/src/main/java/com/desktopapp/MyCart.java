package com.desktopapp;

import java.util.ArrayList;

public class MyCart {

  ArrayList<Products> cart = new ArrayList<>();

  public MyCart() {}

  public ArrayList<Products> addProduct( Products product) {

    cart.add(product);
    return cart;

  }

  public int len()
  {
    return cart.size();
  }

  public ArrayList<Products> getProducts() {
    return cart;
  }
  
}
