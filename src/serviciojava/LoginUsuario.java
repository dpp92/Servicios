package serviciojava;
import java.awt.Toolkit;
import java.sql.*;
import java.sql.PreparedStatement;
import javax.swing.DefaultListModel;

/**
 * @author DAVID PUC POOT
 */
public class LoginUsuario {
    VentanaModal modal = new VentanaModal();
    ServicioConexionFB con;
    private int TIEMPO_MENSAJES;

    public int getTIEMPO_MENSAJES() {
        return TIEMPO_MENSAJES;
    }

    public void setTIEMPO_MENSAJES(int TIEMPO_MENSAJES) {
        this.TIEMPO_MENSAJES = TIEMPO_MENSAJES;
    }
    Toolkit t;
    boolean OK;
     
    public LoginUsuario(){
        con = new ServicioConexionFB();
    }
    /*******************************************************************************************************/
    public  boolean LoginUser(String user){
        DefaultListModel nombres = new DefaultListModel();
        DefaultListModel observ  = new DefaultListModel();
        DefaultListModel folio_inst   = new DefaultListModel();
        DefaultListModel fecha_creacion   = new DefaultListModel();
        DefaultListModel fecha_asignacion   = new DefaultListModel();
        try {
                PreparedStatement pstm = con.getConexion().prepareStatement("SELECT * FROM SEC_USERS"
                + " WHERE LOGIN='"+user+"'");
                ResultSet res = pstm.executeQuery();
                while(res.next()){
                    setTIEMPO_MENSAJES(Integer.valueOf(res.getString("tiempo")));
                    if (res.getString("LOGIN").equals(user)) {
                        Statement stmt = con.getConexion().createStatement();
                        String check_query = "SELECT ins.INTID,ins.INTFECHA"
                        + " ,ins.INTNOMCLIENTE,ins.INTOBSERVACION "
                        + " ,bit.BITFECHA,bit.BITID,bit.BITALERTAREC"
                        + " FROM INSTRUCCIONES ins, BITACORA bit"
                        + " WHERE ins.INTID=bit.INTID"
                        + " AND bit.BITSTATUS='A'"
                        + " AND bit.BITUSERDESTINO='"+user+"'";

            ResultSet n_message = stmt.executeQuery(check_query);
            boolean mostrar = false;
            while(n_message.next()){
            //haz todo
              
             nombres.addElement(n_message.getString("INTNOMCLIENTE"));
             observ.addElement(n_message.getString("INTOBSERVACION"));
             fecha_creacion.addElement(n_message.getString("INTFECHA"));
             fecha_asignacion.addElement(n_message.getString("BITFECHA"));
             folio_inst.addElement(n_message.getString("INTID"));

            modal.recolector(nombres, observ, fecha_creacion,fecha_asignacion,folio_inst);
            modal.nombreUsr=user;
            modal.bitid=n_message.getString("BITID");
            t = Toolkit.getDefaultToolkit();
            t.beep();
            mostrar=true;
            }
            if(mostrar==true){
            modal.setVisible(true);
            }
            this.OK=true;
            }
            }
            }catch (Exception e) {
            System.out.println(e);
            }
        
     
        return this.OK;
    }
}
    
