/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarynew;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class koneksi {
    Connection koneksi=null;
    public static Connection koneksiDb() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_library", "root", "");
            return koneksi;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        return null;
        }
        
    }
}
