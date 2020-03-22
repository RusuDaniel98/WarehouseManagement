package model;

/**
 * Class used to represent the product.
 * It contains constructors, setters and getters and a toString() method
 *
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class Product {

    private int idProduct;
    private String name;
    private int price;
    private int stock;

    public Product(){}

    public Product(int idProduct, String name, int price, int stock){
        super();
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, int price, int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getIdProduct(){
        return this.idProduct;
    }

    public void setIdProduct(int idProduct){
        this.idProduct = idProduct;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPrice(){
        return this.price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getStock(){
        return this.stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * This is the toString method, used to create a nice String out of the class fields.
     * @return The fields of the Product object it is called on, displayed nicely in a String.
     */
    @Override
    public String toString(){
        return "Product [idProduct=" + idProduct + ", name=" + name + ", price" + price + ", stock=" + stock + "]";
    }

}
