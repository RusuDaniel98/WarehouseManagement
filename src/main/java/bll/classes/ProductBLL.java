package bll.classes;

import dao.ProductDAO;
import model.Product;

import java.util.NoSuchElementException;

/**
 * Business logic layer class. This type of class should contain methods that interact with the database,
 * as well as methods that are not related to the database at all (none implemented at this moment)
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class ProductBLL {

    /**
     * Method intended to work with the database. It calls the DAO package method responsible with this task.
     * @param id This is the id searched in the database.
     * @return Returns an Product object (with the id specified as the method parameter)
     */
    public Product findProductById(int id){
        Product product = ProductDAO.findById(id);
        if (product == null){
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return product;
    }

    /**
     * Method intended to work with the database. It calls the DAO package method responsible with this task.
     * @param product This is the Product object to be inserted into the database 'product' table
     * @return The id assigned to the inserted object.
     */
    public int insertProduct(Product product){
        //TODO validate product here
        return ProductDAO.insert(product);
    }

}
