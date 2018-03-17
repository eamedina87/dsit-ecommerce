<%-- 
    Document   : category
    Created on : Mar 8, 2018, 11:42:36 AM
    Author     : Erick
--%>

<%@page import="cart.ShoppingCart"%>
<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="entity.Category"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%
        List<Category> categories = (List<Category>) request.getAttribute("categories");
        Category selectedCategory = (Category) request.getAttribute("selectedCategory");
        List<Product> products = (List<Product>) request.getAttribute("products");
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");

        %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title><%=selectedCategory.getName()%></title>
    </head>
    <body>
        
        <h1>Products of <%=selectedCategory.getName()%></h1>
       
        <% if (cart.isEmpty()) { %>
        <img src="./img/cart.gif" alt="Cart"/> <%=cart.getSize()%> items
        <% } else {%>
        <a href="viewcart.do" ><img src="./img/cart.gif" alt="Cart"/> <%=cart.getSize()%> items</a>
        <% }%>
        
        <table style="width:100%">
            <tr>
                <!-- INCLUDE CATEGORIES HERE-->
                <td>
                    <table>
                        <% for (Category mCat:categories){ %>
                        <tr> 
                            <td>
                                <a href="category.do?categoryid=<%=mCat.getId()%>">
                                <%=mCat.getName()%>
                                </a>
                            </td> 
                        </tr>
                        <%  }   %>
                    </table>
                </td>
                
                
                <td>
                <!-- INCLUDE PRODUCTS HERE-->
                    <table>
                        
                        <% for (Product mProd:products){ %>
                        <tr>
                            <td><img src="img/products/<%=mProd.getName()%>.png" alt="<%=mProd.getName()%>"></td>    
                            <td> <b><%=mProd.getName()%></b> <br> <%=mProd.getDescription()%> </td>
                            <td> <%=mProd.getPrice()%>€ </td>
                            <td> <form action="neworder.do" method="post">
                                    <input type="hidden"
                                    name="productid"
                                    value="<%=mProd.getId()%>">
                                    <input type="hidden"
                                    name="categoryid"
                                    value="<%=selectedCategory.getId()%>">
                                    <input type="submit"
                                    name="submit"
                                    value="add to cart">
                                  </form>
                            </td>
                        </tr>
                        <%  }   %>
                        
                    </table>
                </td>
                
            </tr>
        </table>    
        
    </body>
</html>
