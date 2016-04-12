package serviciojava;

/*
 *@author DAVID PUC POOT
 */
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class VentanaModal extends JFrame implements ActionListener{
    ServicioConexionFB con;
    private JList<String> nombresList;
    private JList<String> instrList;
    private JList<String> fechaCreaList;
    private JList<String> fechAsignaList;
    private JList<String> folioList;
    private JLabel titleFechaAsigna;
    private JLabel titleFolio;
    private JLabel  titleNombres;
    private JLabel titleObservaciones;
    private JLabel titleFecha;
    private JButton boton;          // boton con una determinada accion
    public String nombreUsr;
    String bitid;

    public VentanaModal() {
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
        URL pathIcon = this.getClass().getClassLoader().getResource("serviciojava/icon/logoapp.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(pathIcon);
        this.setIconImage(img);
        con = new ServicioConexionFB();
    }

   
    private void configurarVentana() {
        this.setTitle("Notificaciones"+ nombreUsr);                   // colocamos titulo a la ventana
        this.setSize(850,380);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(true);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }

    private void inicializarComponentes() {
        // creamos los componentes
        nombresList = new JList();
        instrList   = new JList();
        fechaCreaList= new JList();
        boton       = new JButton();
        titleNombres= new JLabel();
        titleFecha  = new JLabel();
        titleObservaciones = new JLabel();
        titleFechaAsigna = new JLabel();
        titleFolio  = new JLabel();
        //NO SE HA CONFIGURADO
        fechAsignaList  = new JList();
        folioList       = new JList();
        
         // configuramos los componentes
        titleFolio.setBounds(10,0,70,30);
        folioList.setBounds(10,40,70,200);
        
        titleNombres.setBounds(90,0,180,30);
        nombresList.setBounds(90,40,180,200);
        
        titleObservaciones.setBounds(280,0,220,30);
        instrList.setBounds(280,40,220,200);
        
        titleFecha.setBounds(510,0,140,30);  
        fechaCreaList.setBounds(510,40,140,200);
        
        titleFechaAsigna.setBounds(670,0,140,30);
        fechAsignaList.setBounds(670,40,140,200);
        
      
        titleNombres.setText("Nombres");
        titleObservaciones.setText("Observaciones");
        titleFecha.setText("Fecha Creación");
        titleFolio.setText("Folio Instruc.");
        titleFechaAsigna.setText("Fecha Asigna");

//      
        
//      
        
        // colocamos un texto al boton
        boton.setText("Aceptar"); 
        boton.setBounds(250, 300, 100, 20);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
        boton.addActionListener(this);
        // adicionamos los componentes a la ventana
        this.setResizable(false);
        this.add(nombresList);
        this.add(instrList);
        this.add(fechaCreaList);
        this.add(folioList);
        this.add(fechAsignaList);
        this.add(titleFecha);
        this.add(titleNombres);
        this.add(titleObservaciones);
        this.add(titleFolio);
        this.add(titleFechaAsigna);
        this.add(boton);
    }
    public void recolector(DefaultListModel nombres, DefaultListModel observa,DefaultListModel fecha_crea,DefaultListModel fecha_asigna,DefaultListModel folio){

        try{
        folioList.setModel(folio);
        nombresList.setModel(nombres);
        instrList.setModel(observa);
        fechaCreaList.setModel(fecha_crea);
        fechAsignaList.setModel(fecha_asigna);
            
        }catch(Exception e){
        System.out.println("Error de modelos de lista "+  e);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        updateFecha();
    }
    public void updateFecha(){
     java.util.Date utilDate = new java.util.Date(); //fecha actual
        long lnMilisegundos = utilDate.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
            try {   
                PreparedStatement pstm;
                    pstm= con.getConexion().prepareStatement("UPDATE BITACORA SET BITALERTAREC='"+sqlTimestamp+"'"
                            + " WHERE BITUSERDESTINO='"+nombreUsr+"' AND BITALERTAREC is null AND BITID='"+bitid+"'");
                System.out.println(nombreUsr);
                int resp = pstm.executeUpdate();
            }catch(SQLException sqlEx){
                System.out.println("Error updateFecha"+sqlEx);
            }
            
    }
 }
