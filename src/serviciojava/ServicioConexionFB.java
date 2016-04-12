package serviciojava;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAVID PUC POOT
 */
public class ServicioConexionFB {
     private Connection connection = null;
     private ResultSet resultSet = null;
     private Statement statement = null;
     private String db= "jdbc:firebirdsql:pse-aplica.dyndns.org:D:/operacion/Pse/Instrucciones/INSTRUCCIONES.FDB";
     private String user = "SYSDBA";
     private String password = "masterkey";
     
     public ServicioConexionFB(){
       try{
          Class.forName("org.firebirdsql.jdbc.FBDriver");
          connection = DriverManager.getConnection(db,this.user, this.password);
       }catch(ClassNotFoundException | SQLException e){
          System.out.println("Servicio conexionFB:\n"+e);
       }
     }
     
     public Connection getConexion(){
        return connection;
     }
     
     public void salid (){
         try {
             connection.close();
         } catch (SQLException ex) {
             Logger.getLogger(ServicioConexionFB.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
   
}
