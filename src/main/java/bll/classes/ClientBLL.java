package bll.classes;

import dao.ClientDAO;
import model.Client;

import java.util.NoSuchElementException;

/**
 * Business logic layer class. This type of class should contain methods that interact with the database,
 * as well as methods that are not related to the database at all (none implemented at this moment)
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class ClientBLL {

    /**
     * Method intended to work with the database. It calls the DAO package method responsible with this task.
     * @param id This is the id to be found in the database 'client' table
     * @return Client object found by id specified as parameter
     */
    public Client findClientById(int id){
        Client client = ClientDAO.findById(id);
        if (client == null){
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client;
    }

    /**
     * Method intended to work with the database. It calls the DAO package method responsible with this task.
     * @param client Client object to be inserted in the database
     * @return The id of the inserted Client object.
     */
    public int insertClient(Client client){
        return ClientDAO.insert(client);
    }

}
