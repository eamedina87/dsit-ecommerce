<%-- 
    Document   : category
    Created on : Mar 8, 2018, 11:42:36 AM
    Author     : Erick
--%>

<%@page import="cart.ShoppingCartItem"%>
<%@page import="cart.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://www.paypalobjects.com/api/checkout.js"></script>
        <title>Shopping Cart</title>
    </head>
    <body>
        <br>
        <img src="./img/cart.gif" alt="Cart"/> <%=cart.getSize()%> item(s)
        
        <% if (cart.isEmpty()) {%>
        <h1>Your shopping cart is empty</h1>
        <a href="init.do">Continue shopping</a><br><br>
        <% } else {%>
        <h1>Your shopping cart contains <%=cart.getSize()%> items.</h1>
        <a href="clearcart.do">Clear cart</a><br><br>
        <a href="init.do">Continue shopping</a><br><br>
        
        <input type="hidden" name="cmd" value="_ext-enter">
        <form action="https://www.paypal.com/us/cgi-bin/webscr" method="post">
           <input type="hidden" name="cmd" value="_xclick">
           <input type="hidden" name="business" value="eamedina@protonmail.com">
           <input type="hidden" name="item_name" value="Affable Bean Green Grocer order">
           <input type="hidden" name="currency_code" value="EUR">
           <input type="hidden" name="amount" value="<%=cart.getTotalAmount()%>">
           <input type="image" src="http://www.paypal.com/en_US/i/btn/x-click-but01.gif" name="submit" alt="Make payments with PayPal - it's fast, free and secure!">
        </form>

        
       <% }%>
        
        
        <table style="width:100%">
            <tr>
                <td>
                    <table>
                        
                        <% for (ShoppingCartItem item:cart.getItems()){ %>
                        <tr>
                            <td><img src="img/products/<%=item.getName()%>.png" alt="<%=item.getName()%>"></td>    
                            <td> <b><%=item.getName()%></b> <br> <%=item.getDescription()%> </td>
                            <td> <%=item.getPrice()%>€/unit</td>
                            <td> <form action="updatecart.do" method="post">
                                    <input type="number"
                                           min="0"
                                           max="99"
                                            name="number"
                                            value="<%=item.getQuantity()%>">
                                    <input type="hidden"
                                            name="productid"
                                            value="<%=item.getProductId()%>">
                                    <input type="submit"
                                            name="update"
                                            value="update">
                                  </form>
                            </td>
                        </tr>
                        <%  }   %>
                        
                    </table>
                </td>
                
            </tr>
        </table>    
                        <b> Total amount: <%=String.format("%.2f", cart.getTotalAmount())%></b>                                
                        
        
    </body>
</html>
