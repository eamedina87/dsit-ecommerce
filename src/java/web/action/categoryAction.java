/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import entity.Category;
import entity.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CategoryModel;
import model.ProductModel;
import web.ViewManager;

/**
 *
 * @author Erick
 */
public class categoryAction extends Action{

    private final CategoryModel categoryModel;
    private final ProductModel productModel;
 
    public categoryAction(CategoryModel categoryModel, ProductModel productModel) {
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //TODO how to retrieve the selected item 
        int categoryid = Integer.parseInt(req.getParameter("categoryid"));
        Category category = categoryModel.retrieveById(categoryid);
        
        HttpSession session = req.getSession();
        
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart==null){
            cart = new ShoppingCart();
        }
        
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("selectedCategory", category);
        req.setAttribute("products", productModel.retrieveAllForCategory(category));
        session.setAttribute("cart", cart);
        
        
        ViewManager.nextView(req, resp, "/view/category.jsp");
        
    }
    
}
