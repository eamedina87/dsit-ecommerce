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
        <div id="paypal-button">Paypal</div><br><br>
        <a href="#">Proceed to checkout</a>
        
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
                                    name="number"
                                    value="<%=item.getQuantity()%>">
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
        <script>
            paypal.Button.render({
              env: 'sandbox', // Or 'sandbox',production
              client: {
                    sandbox:    'AUvo-OBNYTS37vHBvPcjK6UwiG5oVP3D__iXbrI4eoebdQnHBCAFw-_w0oOG8eQbGWDP5bm5qh-KCjWr',
                    production: 'xxxxxxxxx'
                },
              commit: true, // Show a 'Pay Now' button

              style: {
                color: 'silver',
                size: 'small',
                shape: 'pill'
              },

              payment: function(data, actions) {
                /* 
                 * Set up the payment here 
                 */
                return actions.payment.create({
                payment: {
                    transactions: [
                        {
                            amount: { total: '100.00', currency: 'USD' }
                        }
                    ]
                }
            });
              },

              onAuthorize: function(data, actions) {
                /* 
                 * Execute the payment here 
                 */
                return actions.payment.execute().then(function(payment) {

                // The payment is complete!
                // You can now show a confirmation message to the customer
            });
              },

              onCancel: function(data, actions) {
                /* 
                 * Buyer cancelled the payment 
                 */
              },

              onError: function(err) {
                /* 
                 * An error occurred during the transaction 
                 */
              }
            }, '#paypal-button');
          </script>
    </body>
</html>
