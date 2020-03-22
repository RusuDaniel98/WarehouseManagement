package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * <h1>Presentation Package</h1>
 * This is the main class of the application. It is used to instantiate a new window, that starts the application.
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class Window extends JFrame {

    /**
     * Constructor method that calls the windowProduct() method.
     */
    public Window(){
        windowProduct();
    }

    /**
     * Method that calls the constructor of TableProduct and initialises the Frame of that class.
     */
    public void windowProduct(){
        Frame frameProduct = new TableProduct();
        frameProduct.setTitle("Product Window");
        frameProduct.setSize(500, 650);
        frameProduct.setVisible(true);
        ((JFrame) frameProduct).setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Main method of the project. Used to start the application, by calling the constructor of Window.
     * @param args Not used.
     */
    public static void main (String[] args){
        new Window();
    }

}
