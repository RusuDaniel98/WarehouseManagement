package bll.classes;

import dao.PaymentDAO;
import model.Payment;

import java.util.NoSuchElementException;

/**
 * Business logic layer class. This type of class should contain methods that interact with the database,
 * as well as methods that are not related to the database at all (none implemented at this moment)
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class PaymentBLL {

    /**
     * Method intended to work with the database. It calls the DAO package method responsible with this task.
     * @param id This is the id searched in the database.
     * @return Returns an Payment object (with the id specified as the method parameter)
     */
    public Payment findPaymentById(int id){
        Payment payment = PaymentDAO.findById(id);
        if (payment == null){
            throw new NoSuchElementException("The payment with id =" + id + " was not found!");
        }
        return payment;
    }

    /**
     * Method intended to work with the database. It calls the DAO package method responsible with this task.
     * @param payment This is the Payment object to be inserted into the database 'payment' table
     * @return The id assigned to the inserted object.
     */
    public int insertPayment(Payment payment){
        //TODO validate payment here
        return PaymentDAO.insert(payment);
    }

}
