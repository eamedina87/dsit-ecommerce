/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entity.Category;
import entity.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author juanluis
 */
public class ProductModel {

    UserTransaction utx;
    EntityManager em;

    public ProductModel(EntityManager em, UserTransaction utx) {
        this.utx = utx;
        this.em = em;
    }

    public Product retrieveById(int id){
        Query query = em.createQuery("select o from Product as o where o.id=:id");
        query.setParameter("id", id);
        return (Product) query.getSingleResult();
    }
    
    public List<Product> retrieveAllForCategory(Category category) {
        Query query = em.createQuery("select o from Product as o where o.category=:category");
        query.setParameter("category", category);
        return query.getResultList();
    }

}
