package presentation;

import connection.ConnectionFactory;
import model.Client;

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

import static dao.ClientDAO.insert;
import static dao.ClientDAO.removeById;

/**
 * <h1>Presentation Package</h1>
 * This is a UI class used to build the window responsible with Client CRUD operations, and payment method selection.
 * It also contains the action listeners for all the buttons in the Client window.
 * @author Rusu Daniel
 * @version 1.0
 * @since 2019-04-15
 */

public class TableClient extends JFrame implements ActionListener {

    DefaultTableModel model = new DefaultTableModel();
    JTable jtblClient = new JTable(model);

    //---------------------------------------------------------
    //Buttons and fields for the GUI:
    JTextField nameField;
    JTextField addressField;
    JTextField emailField;
    JTextField phoneNoField;
    JTextField paymentField;

    JButton createButton;
    JButton removeButton;
    JButton updateButton;
    //---------------------------------------------------------
    ArrayList<String> columnNames;

    /**
     * Constructor method used to build the UI frame: add the JTable, all the labels, text fields and buttons.
     */
    public TableClient(){
        this.setTitle("Client Window");
        this.setSize(500, 650);
        this.setVisible(true);
        ((JFrame) this).setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel actionsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //Generate header------------------------------------------
        Client client = new Client();
        columnNames = getFields(client);
        for(int i=0; i<columnNames.size(); i++){
            model.addColumn(columnNames.get(i));
        }
        //---------------------------------------------------------

        try{
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Client");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(jtblClient);

        //Table----------------------------------------------------
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 3;
        actionsPanel.add(pg, gc);

        //Others---------------------------------------------------
        nameField = new JTextField(15);
        addressField = new JTextField(15);
        emailField = new JTextField(15);
        phoneNoField = new JTextField(15);
        paymentField = new JTextField(5);

        JLabel nameLabel = new JLabel("Name:");
        JLabel addressLabel = new JLabel("Address:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneNoLabel = new JLabel("PhoneNo:");
        JLabel paymentLabel = new JLabel("Payment method:");
        JLabel paymentInfoLabel = new JLabel("    Payment method: Insert '1' for Cash or '2' for Card");

        createButton = new JButton("Create");
        removeButton = new JButton("Remove");
        updateButton = new JButton("Update");

        createButton.addActionListener(this);
        removeButton.addActionListener(this);
        updateButton.addActionListener(this);

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
        actionsPanel.add(addressField, gc);

        gc.weightx = 0.5;
        gc.gridx=1;
        gc.gridy=3;
        actionsPanel.add(emailField, gc);

        gc.weightx = 0.5;
        gc.gridx=1;
        gc.gridy=4;
        actionsPanel.add(phoneNoField, gc);

        //-------Labels
        gc.weightx = 0.0;
        gc.gridx=0;
        gc.gridy=1;
        actionsPanel.add(nameLabel, gc);

        gc.weightx = 0.0;
        gc.gridx=0;
        gc.gridy=2;
        actionsPanel.add(addressLabel, gc);

        gc.weightx = 0.0;
        gc.gridx=0;
        gc.gridy=3;
        actionsPanel.add(emailLabel, gc);
        gc.weightx = 0.0;
        gc.gridx=0;
        gc.gridy=4;
        actionsPanel.add(phoneNoLabel, gc);

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

        //Payment-related controls---------------------------------
        gc.gridx=0;
        gc.gridy=6;
        actionsPanel.add(paymentLabel, gc);

        gc.gridx=1;
        gc.gridy=6;
        actionsPanel.add(paymentField, gc);

        gc.anchor = GridBagConstraints.WEST;
        gc.gridx=0;
        gc.gridy=7;
        gc.gridwidth=3;
        actionsPanel.add(paymentInfoLabel, gc);

        //---------------------------------------------------------

        this.add(actionsPanel);
        this.setVisible(true);
    }

    public int getRowId(){
        int row = jtblClient.getSelectedRow();
        String result = jtblClient.getValueAt(row, 0).toString();
        int rowId = Integer.parseInt(result);
        return rowId;
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
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Client");
            ResultSet rs = pstm.executeQuery();
            model.getDataVector().removeAllElements();
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            PreparedStatement pstm = connection.prepareStatement("UPDATE Client " +
                    " SET name = ?, address = ?, email = ?, phoneNo = ? WHERE idClient = ?");
            pstm.setString(1, nameField.getText());
            pstm.setString(2, addressField.getText());
            pstm.setString(3, emailField.getText());
            pstm.setString(4, phoneNoField.getText());
            pstm.setLong(5, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method used to validate the used input.
     * @param name The name written in the name text field.
     * @param email The email written in the email text field.
     * @param phoneNo The phone number written in the phone number text field.
     * @return True if the input is valid, or false otherwise.
     */
    public boolean isValid(String name, String email, String phoneNo){
        if (!name.matches("[a-zA-Z]+")){
            JOptionPane.showMessageDialog(this, "Name not valid");
            return false;
        }
        if(!email.contains("@") || !email.contains(".com")){
            JOptionPane.showMessageDialog(this, "Email not valid");
            return false;
        }
        if(!phoneNo.matches("[0-9]+") || phoneNo.length()!=10){
            JOptionPane.showMessageDialog(this, "Phone number not valid");
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
            if(isValid(nameField.getText(), emailField.getText(), phoneNoField.getText())) {
                Client client = new Client(nameField.getText(), addressField.getText(), emailField.getText(), phoneNoField.getText());
                insert(client);
            }
        }else{
            if(e.getSource() == removeButton){
                int row = jtblClient.getSelectedRow();
                int column = 0;  //=1 because I always work with the product ID
                String result = jtblClient.getValueAt(row, column).toString();
                int selectedId = Integer.parseInt(result);
                removeById(selectedId);
            }else{
                if (e.getSource() == updateButton){
                    int row = jtblClient.getSelectedRow();
                    int column = 0;  //=1 because I always work with the product ID
                    String result = jtblClient.getValueAt(row, column).toString();
                    int selectedId = Integer.parseInt(result);
                    rowRefresh(selectedId);
                }
            }
        }
        tableRefresh();  //updates the content of the table every time after a button is pressed
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
