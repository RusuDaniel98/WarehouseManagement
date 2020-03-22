package model;

/**
 * Class used to represent the client.
 * It contains constructors, setters and getters and a toString() method
 *
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class Client {

    private int idClient;
    private String name;
    private String address;
    private String email;
    private String phoneNo;

    public Client(){}

    public Client(int idClient, String name, String address, String email, String phoneNo){
        super();
        this.idClient = idClient;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    /**
     * This method is one of the constructors of this class.
     * @param name This is the name of the client
     * @param address This is the address of the client
     * @param email This is the email of the client
     * @param phoneNo This is the phone number of the client
     */
    public Client(String name, String address, String email, String phoneNo){
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public int getIdClient(){
        return this.idClient;
    }

    public void setIdClient(int idClient){
        this.idClient = idClient;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNo(){
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }

    /**
     * This is the toString method, used to create a nice String out of the class fields.
     * @return The fields of the Client object it is called on, displayed nicely in a String.
     */
    @Override
    public String toString(){
        return "Client [idClient=" + idClient + ", name=" + name + ", address=" + address + ", email=" + email
                + ", phoneNo=" + phoneNo + "]";
    }


}
