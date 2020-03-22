package model;

/**
 * Class used to represent the payment.
 * It contains constructors, setters and getters and a toString() method
 *
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class Payment {
    private int idPayment;
    private String method;

    public Payment(int idPayment, String method){
        super();
        this.idPayment = idPayment;
        this.method = method;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Payment(String method){
        this.method = method;
    }

    /**
     * This is the toString method, used to create a nice String out of the class fields.
     * @return The fields of the Payment object it is called on, displayed nicely in a String.
     */
    @Override
    public String toString(){
        return "Payment [idPayment=" + idPayment + ", method=" + method + "]";
    }


}
