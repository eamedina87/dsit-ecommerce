package cart;

import entity.Product;
import java.text.NumberFormat;
import java.util.*;

/**
 *
 * @author juanluis
 */
public class ShoppingCart {

    private List<ShoppingCartItem> items;
    private float totalAmount;

    public ShoppingCart(){
        items = new ArrayList<ShoppingCartItem>();
        totalAmount = 0;
    }
    
    public synchronized List<ShoppingCartItem> getItems() {
        return items;
    }

    public synchronized  void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public synchronized float getTotalAmount() {
        return totalAmount;
    }

    public synchronized void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public synchronized void addItem(ShoppingCartItem item) {
        if (items.contains(item)){
            ShoppingCartItem scitem = items.get(items.indexOf(item));
            scitem.increaseQuantity();
            items.add(item);
        } else {
            items.add(item);
        }
        
    }
    
    

    public synchronized int getSize(){
        return items.size();
    }

    public synchronized void clear() {
        items = new ArrayList<ShoppingCartItem>();
        totalAmount = 0;
    }
    
    public boolean isEmpty(){
        return items.isEmpty();
    }
    
    

}