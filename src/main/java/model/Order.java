package model;

/**
 * Class used to represent the order.
 * It contains constructors, setters and getters and a toString() method
 *
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class Order {

    private int idOrder;
    private int idClient;
    private int idProduct;
    private int totalPrice;
    private int idPayment;

    public Order(int idOrder, int idClient, int idProduct, int totalPrice, int idPayment){
        super();
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.totalPrice = totalPrice;
        this.idPayment = idPayment;
    }

    /**
     * This is the constructor of Order class.
     * @param idClient This is the id of the client added into the database
     * @param idProduct This is the id of the product added into the database
     * @param totalPrice This is the amount to be paid for a certain order
     * @param idPayment This is the id of the payment added into the database
     */
    public Order(int idClient, int idProduct, int totalPrice, int idPayment){
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.totalPrice = totalPrice;
        this.idPayment = idPayment;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * This is the toString method, used to create a nice String out of the class fields.
     * @return The fields of the Order object it is called on, displayed nicely in a String.
     */
    @Override
    public String toString(){
        return "Order [idOrder=" + idOrder + ", idClient=" + idClient + ", idProduct=" + idProduct
                + ", totalPrice=" + totalPrice + "]";
    }
}
