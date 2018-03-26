/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

package cart;

import entity.Product;

/**
 *
 * @author juanluis
 */
public class ShoppingCartItem {

    private String name;
    private float price;
    private String description;
    private int quantity;
    private Product product;

    public ShoppingCartItem(){
        setQuantity(1);
    }

    public ShoppingCartItem(Product product) {
        this.product = product;
        setName(product.getName());
        setPrice(product.getPrice());
        setDescription(product.getDescription());
        setQuantity(1);
    }

    public ShoppingCartItem(Product product, int quantity) {
        setName(product.getName());
        setPrice(product.getPrice());
        setDescription(product.getDescription());
        setQuantity(quantity);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        setQuantity(getQuantity()+1);
    }
    
    public double getTotal(){
        return getQuantity()*getPrice();
    }
                    
    public int getProductId(){
        return product.getId();
    }
    

}