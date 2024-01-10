/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import library.management.system.LibraryManagementSystem;

/**
 *
 * @author admin
 */
public class DBConnection {
    
    static Connection con = null;
    
    public static Connection getConnection(){
          try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
              
          }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LibraryManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
