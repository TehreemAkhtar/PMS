/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tehreem
 */


public class jDBCConnection {

    private String hostName = "localhost";
    private String dbName = "pizza_store";
    private String userName = "root";
    private String password = "";
    private String connectionStr = "jdbc:mysql://localhost:3306/pizza_store?zeroDateTimeBehavior=convertToNull";
    private Connection con=null;
    public jDBCConnection() {

       
    }

    public Connection createConnection(){
     try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionStr,"root","");

        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(jDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(jDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
     return con;
    }
    public Connection getCon() {
        return con;
    }    
    
    public void closeConnection()
    {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(jDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

