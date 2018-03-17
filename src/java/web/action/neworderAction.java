/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.Category;
import entity.Product;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoryModel;
import model.ProductModel;
import web.ViewManager;

/**
 *
 * @author Erick
 */
public class neworderAction extends Action{

    private final ProductModel productModel;
    private final CategoryModel categoryModel;

    public neworderAction(CategoryModel categoryModel, ProductModel productModel) {
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        int productid = Integer.parseInt(req.getParameter("productid"));
        int categoryid = Integer.parseInt(req.getParameter("categoryid"));
        
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
        Category category = categoryModel.retrieveById(categoryid);
        Product product = productModel.retrieveById(productid);
        ShoppingCartItem item = new ShoppingCartItem(product);
        
        cart.addItem(item);
        
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("selectedCategory", category);
        req.setAttribute("products", productModel.retrieveAllForCategory(category));
        req.getSession().setAttribute("cart", cart);
        
        ViewManager.nextView(req, resp, "/view/category.jsp");
    }
    
    
    
}
