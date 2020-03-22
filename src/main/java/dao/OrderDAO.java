package dao;

import connection.ConnectionFactory;
import model.Order;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>DAO Package</h1>
 * Class used to interact with the database 'order' table.
 * It contains methods to insert, delete and find the data in this table.
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class OrderDAO {

    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO `order` (idClient,idProduct,totalPrice,idPayment)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM order where idOrder = ?";
    private final static String deleteStatementString = "DELETE FROM order WHERE idOrder = ?";

    /**
     * Method used to find the row in the database table by id.
     * It uses a select sql statement.
     * @param idOrder This is the id that should be found in the database 'order' table.
     * @return An object of type Order, representing the one found by id in the database.
     */
    public static Order findById(int idOrder){
        Order toReturn = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, idOrder);
            rs = findStatement.executeQuery();
            rs.next();
            int idClient = rs.getInt("idClient");
            int idProduct = rs.getInt("idProduct");
            int totalPrice = rs.getInt("totalPrice");
            int idPayment = rs.getInt("idPayment");
            toReturn = new Order(idClient, idProduct, totalPrice, idPayment);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:findById " + e.getMessage());
        } finally{
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * This method is used to insert an order in the database table.
     * It uses a insert sql statement.
     * @param order This is the object to be inserted in the database table.
     * @return The id of the row it was inserted at.
     */
    public static int insertOrder(Order order){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try{
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getIdClient());
            insertStatement.setInt(2, order.getIdProduct());
            insertStatement.setInt(3, order.getTotalPrice());
            insertStatement.setInt(4, order.getIdPayment());
            insertStatement.executeUpdate();
            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()){
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e){
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * Method used to delete a row (entry) in the database 'order' table.
     * It uses a delete sql statement.
     * @param id This is the id of the item to be deleted.
     * @return The id if it worked, or a '-1' otherwise.
     */
    public static int removeById(int id){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int removedId = -1;
        try{
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()){
                removedId = rs.getInt(1);
            }
        } catch (SQLException e){
            LOGGER.log(Level.WARNING, "OrderDAO:remove " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

        return removedId;
    }

}
