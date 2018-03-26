/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import entity.Category;
import entity.Product;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductModel;
import web.ViewManager;

/**
 *
 * @author Erick
 */
public class updatecartAction extends Action{

    private final ProductModel productModel;

    public updatecartAction(ProductModel productModel) {
        this.productModel = productModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        int productid = Integer.parseInt(req.getParameter("productid"));
        String quantity = req.getParameter("number");
        
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
        Product product = productModel.retrieveById(productid);
        
        try {
            cart.update(product, quantity);
            req.getSession().setAttribute("cart", cart);
        } catch (Exception e){
        
        } finally {
            ViewManager.nextView(req, resp, "/view/shoppingcart.jsp");
        }
        
        
    }
    
}
