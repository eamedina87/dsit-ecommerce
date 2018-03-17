/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.ViewManager;
import web.action.Action;

/**
 *
 * @author Erick
 */
public class viewcartAction extends Action{

    public viewcartAction() {
        
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ViewManager.nextView(req, resp, "/view/shoppingcart.jsp");
    }
    
}
