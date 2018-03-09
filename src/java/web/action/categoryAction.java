/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import entity.Category;
import entity.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("selectedCategory", category);
        req.setAttribute("products", productModel.retrieveAllForCategory(category));
        
        ViewManager.nextView(req, resp, "/view/category.jsp");
        
    }
    
}
