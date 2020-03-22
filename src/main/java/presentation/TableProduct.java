package presentation;

import connection.ConnectionFactory;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static dao.OrderDAO.insertOrder;
import static dao.ProductDAO.insert;
import static dao.ProductDAO.removeById;

/**
 * <h1>Presentation Package</h1>
 * This is a UI class used to build the window responsible with Product CRUD operations, quantity selection and order placement.
 * It also contains the action listeners for all the buttons in the Product window.
 * This class is also used to call the constructor of TableClient class.
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class TableProduct extends JFrame implements ActionListener {

    //-------------WindowClient
    TableClient frameClient = new TableClient();

    DefaultTableModel model = new DefaultTableModel();
    JTable jtbl = new JTable(model);

    JTextField nameField;
    JTextField priceField;
    JTextField stockField;
    JTextField qtyField;

    JButton createButton;
    JButton removeButton;
    JButton updateButton;
    JButton placeOrderButton;

    ArrayList<String> columnNames;

    /**
     * Constructor method used to build the UI frame: add the JTable, all the labels, text fields and buttons.
     */
    public TableProduct(){


        JPanel actionsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //Generate header------------------------------------------
        Product product = new Product();
        columnNames = getFields(product);
        for(int i=0; i<columnNames.size(); i++){
            model.addColumn(columnNames.get(i));
        }
        //---------------------------------------------------------

        try{
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Product");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4)});
            }
        } catch (Exception e) {
            System.out.println("table product first try ::: " + e.getMessage());
        }
        JScrollPane pg = new JScrollPane(jtbl);
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 3;
        actionsPanel.add(pg, gc);
        nameField = new JTextField(15);
        priceField = new JTextField(15);
        stockField = new JTextField(15);
        qtyField = new JTextField(15);

        JLabel nameLabel = new JLabel("Name:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel stockLabel = new JLabel("Stock:");
        JLabel qtyLabel = new JLabel("Quantity:");

        createButton = new JButton("Create");
        removeButton = new JButton("Remove");
        updateButton = new JButton("Update");
        placeOrderButton = new JButton("Place Order");

        createButton.addActionListener(this);
        removeButton.addActionListener(this);
        updateButton.addActionListener(this);
        placeOrderButton.addActionListener(this);

        gc.anchor = GridBagConstraints.CENTER;
        //-------Fields
        gc.gridwidth = 1;

        gc.weightx = 0.5;
        gc.gridx=1;
        gc.gridy=1;
        actionsPanel.add(nameField, gc);

        gc.weightx = 0.5;
        gc.gridx=1;
        gc.gridy=2;
        actionsPanel.add(priceField, gc);

        gc.weightx = 0.5;
        gc.gridx=1;
        gc.gridy=3;
        actionsPanel.add(stockField, gc);

        //-------Labels
        gc.weightx = 0.0;
        gc.gridx=0;
        gc.gridy=1;
        actionsPanel.add(nameLabel, gc);

        gc.weightx = 0.0;
        gc.gridx=0;
        gc.gridy=2;
        actionsPanel.add(priceLabel, gc);

        gc.weightx = 0.0;
        gc.gridx=0;
        gc.gridy=3;
        actionsPanel.add(stockLabel, gc);

        //-------Buttons
        gc.weightx = 0.5;
        gc.gridx=0;
        gc.gridy=5;
        actionsPanel.add(createButton, gc);

        gc.weightx = 0.5;
        gc.gridx=1;
        gc.gridy=5;
        actionsPanel.add(removeButton, gc);

        gc.weightx = 0.5;
        gc.gridx=2;
        gc.gridy=5;
        actionsPanel.add(updateButton, gc);

        //Additional label, field and button for Qty. and Place Order
        gc.gridx=0;
        gc.gridy=6;
        actionsPanel.add(qtyLabel, gc);

        gc.gridx=1;
        gc.gridy=6;
        actionsPanel.add(qtyField, gc);

        gc.gridx=2;
        gc.gridy=6;
        actionsPanel.add(placeOrderButton, gc);

        //---------------------------------------------------------

        this.add(actionsPanel);
        this.setVisible(true);
    }

    /**
     * Method used to refresh the content of the JTable.
     * Whenever a button is pressed, the contents of the table are deleted and re-loaded from the database table.
     * This ensures that the contents of the JTable always math the contents of the database.
     */
    public void tableRefresh(){
        try{
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Product");
            ResultSet rs = pstm.executeQuery();
            model.getDataVector().removeAllElements();
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                        rs.getInt(3),rs.getInt(4)});
            }
        } catch (Exception e) {
            System.out.println("table product second try :: " + e.getMessage());
        }
    }

    /**
     * Method used to update a certain row in the database table.
     * @param id This is the id of the row to be edited.
     */
    public void rowRefresh(int id){
        try{
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Product " +
                    " SET name = ?, price = ?, stock = ? WHERE idProduct = ?");
            pstm.setString(1, nameField.getText());
            int price = Integer.parseInt(priceField.getText());
            pstm.setLong(2, price);
            int stock = Integer.parseInt(stockField.getText());
            pstm.setLong(3, stock);
            pstm.setLong(4, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("table product third try :: " + e.getMessage());
        }
    }

    /**
     * Method used to update a certain row in the database table.
     * @param id This is the id of the row to be edited.
     * @param actualStock This is the stock value (int) to be written in the database table 'stock' column.
     */
    public void rowRefresh(int id, int actualStock){
        try{
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Product " +
                    " SET stock = ? WHERE idProduct = ?");
            pstm.setLong(1, actualStock);
            pstm.setLong(2, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method used to validate the used input.
     * @param name The name written in the name text field.
     * @param price The price written in the price text field.
     * @param stock The stock written in the stock text field.
     * @return True if the input is valid, or false otherwise.
     */
    public boolean isValid(String name, int price, int stock){
        if (!name.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "Name not valid");
            return false;
        }
        if(price>10000){
            JOptionPane.showMessageDialog(this, "Price not valid (too high)");
            return false;
        }
        if (stock<=0){
            JOptionPane.showMessageDialog(this, "Stock not valid (too low)");
            return false;
        }
        return true;
    }

    /**
     * Method that checks which button was pressed and performs the corresponding action.
     * @param e The action event.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton){
            int price = Integer.parseInt(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            if (isValid(nameField.getText(), price, stock)) {
                Product product = new Product(nameField.getText(), price, stock);
                insert(product);
            }
        }else{
            if(e.getSource() == removeButton){
                int row = jtbl.getSelectedRow();
                int column = 0;  //=0 because I always work with the product ID
                String result = jtbl.getValueAt(row, column).toString();
                int selectedId = Integer.parseInt(result);
                removeById(selectedId);
            }else{
                if (e.getSource() == updateButton){
                    int row = jtbl.getSelectedRow();
                    int column = 0;  //=0 because I always work with the product ID
                    String result = jtbl.getValueAt(row, column).toString();
                    int selectedId = Integer.parseInt(result);
                    rowRefresh(selectedId);
                }else{
                    if (e.getSource() == placeOrderButton){
                        int qty = Integer.parseInt(qtyField.getText());
                        int row = jtbl.getSelectedRow();
                        String result = jtbl.getValueAt(row, 3).toString();
                        int actualStock = Integer.parseInt(result);
                        String resultId = jtbl.getValueAt(row, 0).toString();
                        int selectedId = Integer.parseInt(resultId);
                        if (qty <= actualStock){
                            actualStock -= qty;
                        }else{
                            JOptionPane.showMessageDialog(this, "Not enough stock!");
                        }
                        rowRefresh(selectedId, actualStock);
                        String resultPrice = jtbl.getValueAt(row, 2).toString();
                        int totalPrice = Integer.parseInt(resultPrice);
                        int clientSelectedRow = frameClient.getRowId();
                        int paymentMethod = Integer.parseInt(frameClient.paymentField.getText());
                        Order order = new Order(clientSelectedRow, selectedId, qty*totalPrice, paymentMethod);
                        insertOrder(order);
                    }
                }
            }
        }
        tableRefresh();
    }

    /**
     * Method that uses reflection to return the names of all declared fields of a given Object.
     * @param object The Object of the class we need info about (all declared fields).
     * @return An array list of String that represent the names of all declared fields of object's class.
     */
    public ArrayList<String> getFields(Object object){
        ArrayList<String> result = new ArrayList<String>();
        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (int i=0; i<fields.length; i++){
            result.add(fields[i].getName());
        }
        return result;
    }

}
