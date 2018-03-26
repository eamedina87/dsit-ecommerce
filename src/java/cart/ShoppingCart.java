package cart;

import entity.Product;
import java.text.NumberFormat;
import java.util.*;

/**
 *
 * @author juanluis
 */
public class ShoppingCart {

    private Map<Product, ShoppingCartItem> items;
    private float totalAmount;

    public ShoppingCart(){
        items = new HashMap<Product, ShoppingCartItem>();
        totalAmount = 0;
    }
    
    public synchronized List<ShoppingCartItem> getItems() {
        return new ArrayList<ShoppingCartItem>(items.values());
    }


    public synchronized float getTotalAmount() {
        float amount = 0;
        Iterator<Map.Entry<Product, ShoppingCartItem>> iterator = items.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Product, ShoppingCartItem> entry = iterator.next();
            ShoppingCartItem item = entry.getValue();
            amount = amount + (item.getPrice() * item.getQuantity());
        }
        return amount;
    }

    public synchronized void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public synchronized void addItem(Product product) {
        if (items.containsKey(product)){
            ShoppingCartItem scitem = items.get(product);
            scitem.increaseQuantity();
            items.replace(product, scitem);
        } else {
            items.put(product, new ShoppingCartItem(product));
        }
        
    }
    
    public synchronized void update(Product product, String quantity) {
        int quantityInt = Integer.parseInt(quantity);
        if (quantityInt>0){
            ShoppingCartItem scitem = items.get(product);
            scitem.setQuantity(quantityInt);
            items.replace(product, scitem);
        } else {
            items.remove(product);
        }
        
    }

    public synchronized int getSize(){
        int counter = 0;
        Iterator<Map.Entry<Product, ShoppingCartItem>> iterator = items.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Product, ShoppingCartItem> entry = iterator.next();
            ShoppingCartItem item = entry.getValue();
            counter = counter +item.getQuantity();
        }
        return counter;
    }

    public synchronized void clear() {
        items = new HashMap<Product, ShoppingCartItem>();
        totalAmount = 0;
    }
    
    public boolean isEmpty(){
        return items.isEmpty();
    }
    
    

}