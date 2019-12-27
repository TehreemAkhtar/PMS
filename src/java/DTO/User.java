/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tehreem
 */
public class User {

    private String id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String password;
    private String joinDate;
    private int accountType;

    public User() {
        id = name = address = email = password = phone = joinDate = null;
        accountType = -1;
    }

    public User(String id, String name, String phone, String address, String email, String password, String joinDate, int accountType) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.joinDate = joinDate;
        this.accountType = accountType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public static User isUserExist(String email, String password) {
        System.out.println("user exist");
        User user = null;
        jDBCConnection jDBCconn = new jDBCConnection();
        Connection conn = jDBCconn.createConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("select * from User where lower(email)=lower(?)"
                    + "and password=?");
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {

                user = new User();
                user.setId(rst.getString("user_id"));
                user.setName(rst.getString("name"));
                user.setPassword(rst.getString("password"));
                user.setAddress(rst.getString("address"));
                user.setPhone(rst.getString("phone"));
                user.setJoinDate(rst.getString("join_date"));
                user.setAccountType(rst.getInt("account_type"));
                user.setEmail(rst.getString("email"));
            }
//                   
//            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException npe) {
        }

        return user;
    }

    public static Boolean isUserExistInDB(String email, String phoneNo) {
        User user = null;
        jDBCConnection jDBCconn = new jDBCConnection();
        Connection conn = jDBCconn.createConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("select * from User where lower(email)=lower(?)"
                    + "and phone_no=?");
            pst.setString(1, email);
            pst.setString(2, phoneNo);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                return true;
            }
//                   
//            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException npe) {
        }
        return false;
    }

    public static User createUser(User user) {
        jDBCConnection jDBCconn = new jDBCConnection();
        Connection conn = jDBCconn.createConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO `User`(`name`, `phone_no`, `address`, `email`, `password`)"
                    + " VALUES(?,?,?,?,?)");
            pst.setString(1, user.getName());
            pst.setString(2, user.getPhone());
            pst.setString(3, user.getAddress());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPassword());
            int rst = pst.executeUpdate();
            if (rst != 0) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from User");
                if (rs.last()) {
                    User usr1 = new User();

                    usr1.setId(rs.getString("user_id"));
                    usr1.setName(rs.getString("name"));
                    usr1.setPassword(rs.getString("password"));
                    usr1.setAddress(rs.getString("address"));
                    usr1.setPhone(rs.getString("phone"));
                    usr1.setJoinDate(rs.getString("join_date"));
                    usr1.setAccountType(rs.getInt("account_type"));
                    usr1.setEmail(rs.getString("email"));
                    return usr1;
                }
            }
//                   
//            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException npe) {
        }
        return null;
    }
}
