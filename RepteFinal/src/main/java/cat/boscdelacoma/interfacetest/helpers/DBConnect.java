/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.interfacetest.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cat.boscdelacoma.interfacetest.parc.Dinosaure;
//import java.util.ArrayList;
//import java.util.List;
/**
 *
 * @author jonar
 */
public class DBConnect {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String DB_NAME = "jurassicpark";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    
    public static Connection getConnect(){
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT,DB_NAME), USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Excepció" +ex.getMessage());
        }
        return connection;
    }
    
    public static ObservableList<Dinosaure> getDinos(){
        
        Connection conn = getConnect();
        ObservableList<Dinosaure> list = FXCollections.observableArrayList();
        try{
        
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM dinos");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
            list.add(new Dinosaure(
                rs.getString("nom"), 
                Integer.parseInt(rs.getString("edat")), 
                rs.getString("tipus"), 
                rs.getDouble("pes")
            ));
            }
        }catch(Exception e){
                System.out.println("Excepció" + e.getMessage());
        }
        return list;
    }
    
    public static void deleteDino(String nom) throws SQLException{
        
      
        try{
        Connection conn = getConnect();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM dinos WHERE nom = ?");
        
        ps.setString(1, nom);
        ps.executeUpdate();
        }catch(SQLException e){
            throw e;
        }
    }
    
   public static void canviarDino(String nom, int edat, String tipus, double pes, String oldNom) throws SQLException {
        try (Connection conn = getConnect()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE dinos SET nom = ?, edat = ?, tipus = ?, pes = ? WHERE nom = ?");
            ps.setString(1, nom);
            ps.setInt(2, edat);
            ps.setString(3, tipus);
            ps.setDouble(4, pes);
            ps.setString(5, oldNom);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}