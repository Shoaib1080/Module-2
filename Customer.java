/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Customer {
    private int id;
    private String fullname;
    private String birthdate;
    private String  phone;
    private String email;
    private String address;

    public Customer() {
    }

    public Customer(int _id, String _fullname, String _birthdate, String _phone, String _email, String _address) {
        this.id = _id;
        this.fullname = _fullname;
        this.birthdate = _birthdate;
        this.phone = _phone;
        this.email = _email;
        this.address = _address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
     public void addCustomer(int _id,String _fullname,String _birthdate,String _phone,String _email,String _address)
    {
        String insertQuery = "INSERT INTO `customer`(`id`, `fullname`, `birthdate`, `phone`, `email`, `address`) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps;
        
        
        try {
            ps=DataBase.getConnection().prepareStatement(insertQuery);
              
            ps.setInt(1, _id);
            ps.setString(2, _fullname);
               ps.setString(3, _birthdate);
               ps.setString(4, _phone);
               ps.setString(5, _email);
               ps.setString(6, _address);
               
               if(ps.executeUpdate() != 0)
               {
                     JOptionPane.showMessageDialog(null, "Customer  Added  Successfully");
               }
               else
               {
                   JOptionPane.showMessageDialog(null, "Customer Not Added");
               }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void editCustomer(int _id,String _fullname,String _birthdate,String _phone,String _email,String _address)
    {
        String editQuery = "UPDATE `customer` SET `fullname`=?,`birthdate`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        PreparedStatement ps;
    
        
        try {
            ps=DataBase.getConnection().prepareStatement(editQuery);
            
            
           ps.setInt(1, _id);
            ps.setString(2, _fullname);
               ps.setString(3, _birthdate);
               ps.setString(4, _phone);
               ps.setString(5, _email);
               ps.setString(6, _address);
               
               
               
               if(ps.executeUpdate() != 0)
               {
                     JOptionPane.showMessageDialog(null, "Location Edited  Successfully");
               }
               else
               {
                   JOptionPane.showMessageDialog(null, "Location Not Edited");
               }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void removeCustomer(int _id)
    {
         String removeQuery = "DELETE FROM `customer` WHERE `id`=?";
        PreparedStatement ps;
        
        
        try {
            ps=DataBase.getConnection().prepareStatement(removeQuery);
            
            
               ps.setInt(1, _id);
               
               if(ps.executeUpdate() != 0)
               {
                     JOptionPane.showMessageDialog(null, "Customer Removed  Successfully");
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Customer not Removed");
               }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public ResultSet getData(String query)
      {
           PreparedStatement ps;
            ResultSet rs = null;
        try {  
            ps = DataBase.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(brand_cls.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
      }
    //funtion to get all locations and return  an arraylist
      public ArrayList<Customer> customerlist()
      {
          ArrayList<Customer> cuList = new ArrayList<>();
          ResultSet rs = getData("SELECT * FROM `locations` ");
        try {
            while(rs.next())
            {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));

                Customer cuo = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                cuList.add(cuo);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuList;
      }
      public Customer getCustomerById(int id)
    {
        
        String query1 = "SELECT * FROM `customer` WHERE `id`= "+id;
         ResultSet rs = getData(query1);
         Customer cuto=null;
        try {
            rs.next();
            cuto = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuto;
    }
   
}
