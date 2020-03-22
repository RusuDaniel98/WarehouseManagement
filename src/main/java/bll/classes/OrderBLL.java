package bll.classes;

import dao.OrderDAO;
import model.Order;

import java.util.NoSuchElementException;

/**
 * Business logic layer class. This type of class should contain methods that interact with the database,
 * as well as methods that are not related to the database at all (none implemented at this moment)
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class OrderBLL {

    /**
     * Method intended to work with the database. It calls the DAO package method responsible with this task.
     * @param id This is the id searched in the database.
     * @return Returns an Order object (with the id specified as the method parameter)
     */
    public Order findOrderById(int id){
        Order order = OrderDAO.findById(id);
        if (order == null){
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return order;
    }

    /**
     * Method intended to work with the database. It calls the DAO package method responsible with this task.
     * @param order This is the Order object to be inserted into the database 'order' table
     * @return The id assigned to the inserted object.
     */
    public int insertOrder(Order order){
        //TODO validate order here
        return OrderDAO.insertOrder(order);
    }

}
