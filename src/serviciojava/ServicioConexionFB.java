package serviciojava;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
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
     private String db= "";
     private String user = "";
     private String password = "";
     
     public ServicioConexionFB(){
        Properties nuevo = new leerPro().leer();
        db=nuevo.getProperty("root")+nuevo.getProperty("servidor")+":"+nuevo.getProperty("db");
        System.out.println(db);
        user=nuevo.getProperty("user");
        password = nuevo.getProperty("password");
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
