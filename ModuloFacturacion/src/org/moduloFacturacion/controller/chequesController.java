package org.moduloFacturacion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.moduloFacturacion.bean.Animations;
import org.moduloFacturacion.bean.AutoCompleteComboBoxListener;
import org.moduloFacturacion.bean.CambioScene;
import org.moduloFacturacion.bean.ChequeBuscado;
import org.moduloFacturacion.bean.ChequeEncabezadoBuscado;
import org.moduloFacturacion.bean.Chequedetalle;
import org.moduloFacturacion.bean.Letras;
import org.moduloFacturacion.bean.ValidarStyle;
import org.moduloFacturacion.bean.imprimirCheque;
import org.moduloFacturacion.bean.imprimirCheque2;
import org.moduloFacturacion.db.Conexion;


public class chequesController implements Initializable {

    int detalleEditarId =0;
    Letras letras = new Letras();
    DecimalFormat twoDForm = new DecimalFormat("#.00");
    @FXML
    private JFXButton btnEditarCheque;
    @FXML
    private JFXButton btnEliminarCheque;
    @FXML
    private Label nochequeText;
    @FXML
    private Label pagueseText;
    @FXML
    private Label descText;
    @FXML
    private Label digitosText;
    @FXML
    private Label sumadeText;
    @FXML
    private Label fechaText;
    @FXML
    private AnchorPane comprobanteChequeIma;

    
    
    
    public enum Operacion{AGREGAR,GUARDAR,ELIMINAR,BUSCAR,ACTUALIZAR,CANCELAR,NINGUNO, VENDER,FILTRAR,CARGAR};
    public Operacion cancelar = Operacion.NINGUNO;
    public Operacion tipoOperacionChequeDetalle = Operacion.NINGUNO; 
    Image imgError = new Image("org/moduloFacturacion/img/error.png");
    Image imgCorrecto= new Image("org/moduloFacturacion/img/correcto.png");
    Image imgWarning = new Image("org/moduloFacturacion/img/warning.png");
    CambioScene cambioScene = new CambioScene();  
    LoginViewController login = new LoginViewController();
    @FXML
    private Pane buttonInicio;
    @FXML
    private AnchorPane anchor1;
    @FXML
    private JFXTextField numeroCheque;
    @FXML
    private JFXTextField chequeFecha;
    @FXML
    private JFXTextField pagoOrden;
    @FXML
    private TextField sumaLetras;
    @FXML
    private TextArea descripcionPago;
    @FXML
    private AnchorPane anchor2;
   
    @FXML
    private TextField totalValor;
    @FXML
    private Button btnImprimir;
    @FXML
    private AnchorPane anchor3;
    @FXML
    private JFXButton btnBuscarFiltro;
    @FXML
    private JFXButton btnFiltrarCheque;
    @FXML
    private JFXDatePicker txtFechaFinal;
    @FXML
    private JFXDatePicker txtFechaInicio;
    @FXML
    private AnchorPane anchor4;
    
    /* ENTIDADES DE CONTROL DE CHEQUES*/
    @FXML
    private TableView<ChequeBuscado> tblResultadoCheque;
    @FXML
    private TableColumn<ChequeBuscado, Integer> colNoChequeBuscado;
    @FXML
    private TableColumn<ChequeBuscado, Date> colFechaChequeBuscado;
    @FXML
    private TableColumn<ChequeBuscado, Double> colTotalChequeBuscado;
    @FXML
    private TableColumn<ChequeBuscado, String> colReferenteChequeBuscado;
    @FXML
    private JFXComboBox<String> txtBusquedaCodigoChe;
    @FXML
    private TableView<ChequeEncabezadoBuscado> tblResultadoEncabezado;
    @FXML
    private TableColumn<ChequeEncabezadoBuscado, String> colCuentaBuscado;
    @FXML
    private TableColumn<ChequeEncabezadoBuscado, String> colDescBuscada;
    @FXML
    private TableColumn<ChequeEncabezadoBuscado, Double> colValorBuscado;
    
    
    
    double totalChequeDetalle=0;
    ObservableList<Chequedetalle> listaCheque;
    LocalDate fechaActual = LocalDate.now();
    @FXML
    private AnchorPane anchor;
    ValidarStyle validar = new ValidarStyle();
    MenuPrincipalContoller menu = new MenuPrincipalContoller();
    
    
    @FXML
    private void previewEvent(MouseEvent event) {
        
        Stage stage = (Stage) anchor.getScene().getWindow();
        
        if(numeroCheque.getText().isEmpty() || pagoOrden.getText().isEmpty() || totalValor.getText().isEmpty() || sumaLetras.getText().isEmpty() || descripcionPago.getText().isEmpty()){
             Notifications noti = Notifications.create();
            noti.graphic(new ImageView(imgError));
            noti.title("ERROR");
            noti.text("HAY CAMPOS VACÍOS");
            noti.position(Pos.BOTTOM_RIGHT);
            noti.hideAfter(Duration.seconds(4));
            noti.darkStyle();   
            noti.show();
        }else{  
            if(stage.isMaximized()){
                nochequeText.setLayoutY(160);
                nochequeText.setLayoutX(705);
                nochequeText.setText(numeroCheque.getText());
                nochequeText.setFont(new Font("System",20));
                
                fechaText.setLayoutY(195);
                fechaText.setLayoutX(280);
                fechaText.setText(chequeFecha.getText());
                fechaText.setFont(new Font("System",20));
                
                
                pagueseText.setLayoutY(235);
                pagueseText.setLayoutX(200);
                pagueseText.setText(pagoOrden.getText());
                pagueseText.setFont(new Font("System",20));
                
                
                digitosText.setLayoutY(235);
                digitosText.setLayoutX(705);
                digitosText.setText(totalValor.getText());
                digitosText.setFont(new Font("System",20));

                sumadeText.setLayoutY(275);
                sumadeText.setLayoutX(200);
                sumadeText.setText(sumaLetras.getText());
                sumadeText.setFont(new Font("System",20));

                descText.setLayoutY(450);
                descText.setLayoutX(290);
                descText.setText(descripcionPago.getText());
                descText.setFont(new Font("System",20));
            }else{
                nochequeText.setLayoutY(68);
                nochequeText.setLayoutX(380);
                nochequeText.setText(numeroCheque.getText());
                nochequeText.setFont(new Font("System",12));

                fechaText.setLayoutX(135);
                fechaText.setLayoutY(78);
                fechaText.setText(chequeFecha.getText());
                fechaText.setFont(new Font("System",12));
                 
                
                pagueseText.setLayoutY(100);
                pagueseText.setLayoutX(105);
                pagueseText.setText(pagoOrden.getText());
                pagueseText.setFont(new Font("System",12));
                
                
                digitosText.setLayoutY(100);
                digitosText.setLayoutX(385);
                digitosText.setText(totalValor.getText());
                digitosText.setFont(new Font("System",12));
                
                sumadeText.setLayoutY(126);
                sumadeText.setLayoutX(105);
                sumadeText.setText(sumaLetras.getText());
                sumadeText.setFont(new Font("System",10));

                descText.setLayoutY(200);
                descText.setLayoutX(140);
                descText.setText(descripcionPago.getText());
                descText.setFont(new Font("System",12));
            }
            
        }
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validar.validarView(menu.prefs.get("dark", "root"), anchor);
        btnEditarCheque.setDisable(true);
        btnEliminarCheque.setDisable(true);
        limpiarTextChequeDetalle();
        descripcionPago.setWrapText(true);
     
    }    
    public void limpiarTextChequeDetalle(){
        numeroCheque.setText("");
        pagoOrden.setText("");
        descripcionPago.setText("");
    }
    public void limpiarDetalle(){
        descripcionPago.setText("");
    }
   
    
   
    public void accion(String sql){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        PreparedStatement ps;
        ResultSet rs;
        Notifications noti = Notifications.create();
        ButtonType buttonTypeSi = new ButtonType("Si");
        ButtonType buttonTypeNo = new ButtonType("No");
        
        switch(tipoOperacionChequeDetalle){
            case ACTUALIZAR:
                try{
                    ps = Conexion.getIntance().getConexion().prepareCall(sql);
                    ps.execute();
                    noti.graphic(new ImageView(imgCorrecto));
                    noti.title("OPERACIÓN EXITOSA");
                    noti.text("SE HA ACTUALIZADO EXITOSAMENTE EL REGISTRO");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacionChequeDetalle = Operacion.CANCELAR;
                    btnEditarCheque.setDisable(true);
                    btnEliminarCheque.setDisable(true);
                }catch(SQLException ex){
                    ex.printStackTrace();
                    noti.graphic(new ImageView(imgError));
                    noti.title("ERROR AL ACTUALIZAR");
                    noti.text("HA OCURRIDO UN ERROR AL ACTUALIZAR EL REGISTRO");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacionChequeDetalle = Operacion.NINGUNO;
                }
                break;
            case ELIMINAR:
                try{
                    ps = Conexion.getIntance().getConexion().prepareCall(sql);
                    ps.execute();
                    noti.graphic(new ImageView(imgCorrecto));
                    noti.title("OPERACIÓN EXITOSA");
                    noti.text("SE HA ELIMINADO EXITOSAMENTE EL REGISTRO");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacionChequeDetalle = Operacion.CANCELAR;
                    btnEditarCheque.setDisable(true);
                    btnEliminarCheque.setDisable(true);
                }catch(SQLException ex){
                    ex.printStackTrace();
                    noti.graphic(new ImageView(imgError));
                    noti.title("ERROR AL ELIMINADO");
                    noti.text("HA OCURRIDO UN ERROR AL ELIMINADO EL REGISTRO");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacionChequeDetalle = Operacion.NINGUNO;
                }
                break;
        }
    }   
    @FXML
    private void regresar(MouseEvent event) throws IOException {
         String menu = "org/moduloFacturacion/view/menuPrincipal.fxml";
        cambioScene.Cambio(menu,(Stage) anchor.getScene().getWindow());
    }
    
    

 
     @FXML
    private void imprimirCheque(MouseEvent event) {
        if(numeroCheque.getText().equals("") || chequeFecha.getText().equals("") || pagoOrden.getText().equals("")){
            Notifications noti = Notifications.create();
            noti.graphic(new ImageView(imgError));
            noti.title("ERROR");
            noti.text("HAY CAMPOS VACÍOS");
            noti.position(Pos.BOTTOM_RIGHT);
            noti.hideAfter(Duration.seconds(4));
            noti.darkStyle();   
            noti.show();
            
        }else{
            imprimirCheque imprimirC = new imprimirCheque();
            imprimirCheque2 imprimirch = new imprimirCheque2();
          
//            imprimirch.imprima(chequeFecha.getText(), Double.parseDouble(totalValor.getText()), pagoOrden.getText(), sumaLetras.getText(), totalValor.getText());
             imprimirC.imprima(numeroCheque.getText(), chequeFecha.getText(), pagoOrden.getText(), sumaLetras.getText(), totalValor.getText(),descripcionPago.getText());
          //  guardarCheque();
        }
       
    }
     public void guardarCheque(){
       String sql = "{call SpTransferirCheque()}";
       String sqlEliminar = "{call SpEliminarBackupCheque()}";
       String sqlCheque = "{call SpAgregarCheque('"+numeroCheque.getText()+"','"+chequeFecha.getText()+"','"+fechaActual+"','"+pagoOrden.getText()+"','"+totalValor.getText()+"','"+getUsuarioId()+"')}";
       try{

           PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
           ps.execute();
           
           PreparedStatement psCheque = Conexion.getIntance().getConexion().prepareCall(sqlCheque);
           psCheque.execute();
           PreparedStatement psEliminar = Conexion.getIntance().getConexion().prepareCall(sqlEliminar);
           psEliminar.execute();
           
            Notifications noti = Notifications.create();
            noti.graphic(new ImageView(imgCorrecto));
            noti.title("OPERACIÓN EXITOSA");
            noti.text("SE HA IMPRESO Y REGISTRADO CON ÉXITO");
            noti.position(Pos.BOTTOM_RIGHT);
            noti.hideAfter(Duration.seconds(4));
            noti.darkStyle();
            noti.show();
       }catch(SQLException ex){
           ex.printStackTrace();
           Notifications noti = Notifications.create();
            noti.graphic(new ImageView(imgError));
            noti.title("ERROR");
            noti.text("HUBO UN ERROR AL REGISTRAR EN LA BASE DE DATOS");
            noti.position(Pos.BOTTOM_RIGHT);
            noti.hideAfter(Duration.seconds(4));
            noti.darkStyle();
            noti.show();
       }
    }
     
    public int getUsuarioId(){
     int codigoUsuario=0;
     System.out.println(login.prefsUsuario.get("usuario", "root"));
     String sql = "{call SpBuscarUsuarioId('"+login.prefsUsuario.get("usuario", "root")+"')}";

     try{
         PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
         ResultSet rs = ps.executeQuery();

         while(rs.next()){
             codigoUsuario = rs.getInt("usuarioId");
         }
     }catch(SQLException ex){
         ex.printStackTrace();
     }
     System.out.println(codigoUsuario);
     return codigoUsuario;
    }
    @FXML
    private void btnEliminarCheque(MouseEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType buttonTypeSi = new ButtonType("Si");
        ButtonType buttonTypeNo = new ButtonType("No");
        
        alert.setTitle("WARNING");
        alert.setHeaderText("ELIMINAR REGISTRO DE CHEQUE");
        alert.setContentText("¿Está seguro que desea eliminar este registro?");

        alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == buttonTypeSi){
             String sql = "{call SpEliminarChequeEncabezado('"+detalleEditarId+"')}";
            tipoOperacionChequeDetalle = Operacion.ELIMINAR;
            accion(sql);
        }
        
    }
    
    @FXML
    private void validarNoCheque(KeyEvent event) {
        char letra = event.getCharacter().charAt(0);
        
        if(Character.isDigit(letra)){
            
        }else{
            event.consume();
        }
    }

    
    @FXML
    private void convertirLetras(KeyEvent event) {
        if(!totalValor.getText().equals("")){
            sumaLetras.setText(letras.Convertir(twoDForm.format(Double.parseDouble(totalValor.getText())), true));
        }else{
            totalValor.setText("");
        }
    }
    public void letras(){
        if(!totalValor.getText().equals("")){
            sumaLetras.setText(letras.Convertir(twoDForm.format(Double.parseDouble(totalValor.getText())), true));
        }else{
            totalValor.setText("hola");
        }
    }
    

    
    @FXML
    private void btnEditarChequeDetalle(MouseEvent event) {
        if(numeroCheque.getText().equals("") || chequeFecha.getText().equals("") || pagoOrden.getText().equals("")|| descripcionPago.getText().equals("")){
            Notifications noti = Notifications.create();
            noti.graphic(new ImageView(imgError));
            noti.title("ERROR");
            noti.text("HAY CAMPOS VACÍOS");
            noti.position(Pos.BOTTOM_RIGHT);
            noti.hideAfter(Duration.seconds(4));
            noti.darkStyle();   
            noti.show();
            
        }else{
            Chequedetalle chequeNuevo = new Chequedetalle();
            chequeNuevo.setChequeDetalleDesc(descripcionPago.getText());
            
            String sql = "{call SpEditarChequeEncabezado('"+detalleEditarId+"','"+chequeNuevo.getChequeDetalleCuenta()+"','"+chequeNuevo.getChequeDetalleDesc()+"','"+chequeNuevo.getChequeDetalleValor()+"')}";
            tipoOperacionChequeDetalle = Operacion.ACTUALIZAR;
            accion(sql);
            
        }
    }

   

    @FXML
    private void atajosProductos(KeyEvent event) {
    }


    @FXML
    private void fechaInicio(ActionEvent event) {
    }

    
    
    /* CODIGO CONTROL DE CHEQUES*/
    
    public Operacion tipoOperacionBusquedaCheques = Operacion.NINGUNO; 

    ObservableList<String> listaNumeroCheque;
    ObservableList<ChequeBuscado> listaChequeBuscadas;
    ObservableList<ChequeEncabezadoBuscado> listaEncabezadoBuscado;

     Animations animacion = new Animations();
    
    Integer noCheque = 0;
    
    /*OBTENER DATOS DE TABLA CHEQUES*/
    
    /*listar todos los cheques */
        public ObservableList<ChequeBuscado> getControlCheque(){
        ArrayList<ChequeBuscado> lista = new ArrayList();
        ArrayList<String> comboNumeroCheques = new ArrayList();
        String sql = "{call SpListarCheque()}";
        int x=0;
        
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(new ChequeBuscado(
                            rs.getInt("chequeNo"),
                            rs.getDate("chequeFecha"),
                            rs.getDouble("chequeMonto"),
                            rs.getString("chequePagoAlaOrdenDe")
                ));
                comboNumeroCheques.add(x, rs.getString("chequeNo"));
                x++;
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        listaNumeroCheque = FXCollections.observableList(comboNumeroCheques);
        txtBusquedaCodigoChe.setItems(listaNumeroCheque);
        return listaChequeBuscadas = FXCollections.observableList(lista);
    }
    
        
    @FXML
    public void cargarChequesBuscadas(){
        animacion.animacion(anchor3, anchor4);
        tblResultadoCheque.setItems(getControlCheque());
        colNoChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequeNo"));
        colFechaChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequeFecha"));  
        colTotalChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequeMonto"));
        colReferenteChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequePagoAlaOrdenDe"));

        new AutoCompleteComboBoxListener(txtBusquedaCodigoChe);
        
        txtBusquedaCodigoChe.setValue("");
        txtFechaInicio.setValue(null);
        txtFechaFinal.setValue(null);
        tblResultadoEncabezado.setItems(null);
    }  
    
    /*listar cheque buscados por id*/
    
        public ObservableList<ChequeBuscado> getChequeBuscadasPorId(){
        ArrayList<ChequeBuscado> lista = new ArrayList();
        String sql = "{call SpBuscarCheque('"+txtBusquedaCodigoChe.getValue()+"')}";
        int x=0;
        
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(new ChequeBuscado(
                            rs.getInt("chequeNo"),
                            rs.getDate("chequeFecha"),
                            rs.getDouble("chequeMonto"),
                            rs.getString("chequePagoAlaOrdenDe")
                ));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return listaChequeBuscadas = FXCollections.observableList(lista);
    }
        
        public void cargarChequesBuscadasPorId(){
        tblResultadoCheque.setItems(getChequeBuscadasPorId());
        
        colNoChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequeNo"));
        colFechaChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequeFecha"));  
        colTotalChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequeMonto"));
        colReferenteChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequePagoAlaOrdenDe"));
        buscarEncabezado();
        txtBusquedaCodigoChe.setValue("");
    }  
        
   /* listar los cheques segun su fecha*/
       
        public ObservableList<ChequeBuscado> getChequeBuscadasPorFecha(){
        ArrayList<ChequeBuscado> lista = new ArrayList();
        String sql = "{call SpBuscarChequePorFecha('"+txtFechaInicio.getValue()+"','"+txtFechaFinal.getValue()+"')}";
        int x=0;
        
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(new ChequeBuscado(
                            rs.getInt("chequeNo"),
                            rs.getDate("chequeFecha"),
                            rs.getDouble("chequeMonto"),
                            rs.getString("chequePagoAlaOrdenDe")
                ));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return listaChequeBuscadas = FXCollections.observableList(lista);
    }
        
       public void cargarChequesBuscadasPorFecha(){
        tblResultadoCheque.setItems(getChequeBuscadasPorFecha());
        
        colNoChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequeNo"));
        colFechaChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequeFecha"));  
        colTotalChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequeMonto"));
        colReferenteChequeBuscado.setCellValueFactory(new PropertyValueFactory("chequePagoAlaOrdenDe"));
        
        txtBusquedaCodigoChe.setValue("");
    }  
       
    /*OBTENER INFORMACION DEL CHEQUE SEGUN ID */
    public ObservableList<ChequeEncabezadoBuscado> getEncabezadoBuscado(){
        ArrayList<ChequeEncabezadoBuscado> listaEnzacezado = new ArrayList();
        
        String sql = "{call SpBuscarDetalleCheque('"+txtBusquedaCodigoChe.getValue()+"')}";
        
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listaEnzacezado.add(new ChequeEncabezadoBuscado(
                            rs.getString("chequeDetalleCuenta"),
                            rs.getString("chequeDetalleDesc"),
                            rs.getDouble("chequeDetalleValor")
                ));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return listaEncabezadoBuscado = FXCollections.observableList(listaEnzacezado);
    }
    
    public void cargarEnzabezadoBuscados(){
        tblResultadoEncabezado.setItems(getEncabezadoBuscado());
        
        colCuentaBuscado.setCellValueFactory(new PropertyValueFactory("chequeDetalleCuenta"));
        colDescBuscada.setCellValueFactory(new PropertyValueFactory("chequeDetalleDesc"));  
        colValorBuscado.setCellValueFactory(new PropertyValueFactory("chequeDetalleValor"));
    }  
    
     public void accionBusqueda(String sql){
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        PreparedStatement ps;
        ResultSet rs;
        Notifications noti = Notifications.create();
        ButtonType buttonTypeSi = new ButtonType("Si");
        ButtonType buttonTypeNo = new ButtonType("No");
        switch(tipoOperacionBusquedaCheques){
            case FILTRAR:
                 try{
                    ps = Conexion.getIntance().getConexion().prepareCall(sql);
                    rs = ps.executeQuery();
                    int numero=0;
                                     
                    if(rs.first()){
                        for(int i=0; i<tblResultadoCheque.getItems().size(); i++){
                            if(colNoChequeBuscado.getCellData(i) == noCheque){
                                tblResultadoCheque.getSelectionModel().select(i);
                                break;
                            }
                        }
                        noti.graphic(new ImageView(imgCorrecto));
                        noti.title("OPERACIÓN EXITOSA");
                        noti.text("SU OPERACIÓN SE HA REALIZADO CON EXITO");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                    }else{
                        noti.graphic(new ImageView(imgError));
                        noti.title("ERROR AL BUSCAR");
                        noti.text("NO SE HA ENCONTRADO EN LA BASE DE DATOS");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                        tipoOperacionBusquedaCheques = Operacion.CANCELAR;
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                    noti.graphic(new ImageView(imgError));
                    noti.title("ERROR AL BUSCAR");
                    noti.text("HA OCURRIDO UN ERROR EN LA BASE DE DATOS");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacionBusquedaCheques = Operacion.CANCELAR;
                }
                break;
            case CARGAR:
                 alert.setTitle("VOLVER A CARGAR DATOS");
                alert.setHeaderText("VOLVER A CARGAR DATOS");
                alert.setContentText("¿Está seguro que desea cargar todos los datos de nuevo?");
               
                alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);
                
                Optional<ButtonType> resultactualizar = alert.showAndWait();
                if(resultactualizar.get() == buttonTypeSi ){
                    try {
                        ps = Conexion.getIntance().getConexion().prepareCall(sql);
                        ps.execute();
                        
                        noti.graphic(new ImageView(imgCorrecto));
                        noti.title("OPERACIÓN EXITOSA");
                        noti.text("SE HAN CARGADO EXITOSAMENTE SUS REGISTROS");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                        tipoOperacionBusquedaCheques = Operacion.CANCELAR;
                        cargarChequesBuscadas();
                        txtFechaInicio.setValue(null);
                        txtFechaFinal.setValue(null);
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                        noti.graphic(new ImageView(imgError));
                        noti.title("ERROR AL CARGAR SUS REGISTROS");
                        noti.text("HA OCURRIDO UN ERROR AL MOMENTO DE CARGAR TODOS LOS REGISTROS");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                        tipoOperacionBusquedaCheques = Operacion.CANCELAR;

                    }
                }else{  
                    noti.graphic(new ImageView(imgError));
                    noti.title("OPERACIÓN CANCELADA");
                    noti.text("NO SE HAN PODIDO CARGAR TODOS LOS REGISTROS");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacionBusquedaCheques = Operacion.CANCELAR;
                }
                break;
            case BUSCAR:
                try{
                    ps = Conexion.getIntance().getConexion().prepareCall(sql);
                    rs = ps.executeQuery();
                    int numero=0;
                                        
                    if(rs.first()){
                        for(int i=0; i<tblResultadoCheque.getItems().size(); i++){
                            if(colNoChequeBuscado.getCellData(i) == noCheque){
                                tblResultadoCheque.getSelectionModel().select(i);
                                break;
                            }
                        }
                        noti.graphic(new ImageView(imgCorrecto));
                        noti.title("OPERACIÓN EXITOSA");
                        noti.text("SU OPERACIÓN SE HA REALIZADO CON EXITO");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                    }else{
                        noti.graphic(new ImageView(imgError));
                        noti.title("ERROR AL BUSCAR");
                        noti.text("NO SE HA ENCONTRADO EN LA BASE DE DATOS");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                        tipoOperacionBusquedaCheques = Operacion.CANCELAR;
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                    noti.graphic(new ImageView(imgError));
                    noti.title("ERROR AL BUSCAR");
                    noti.text("HA OCURRIDO UN ERROR EN LA BASE DE DATOS");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacionBusquedaCheques = Operacion.CANCELAR;
                }
                        txtFechaInicio.setValue(null);
                        txtFechaFinal.setValue(null);
                break;     
        }
    }
    
     /* Proceso para buscar cheque id*/
    @FXML
     public void buscarPorId(){
      if(txtBusquedaCodigoChe.getValue().equals("")){
                    Notifications noti = Notifications.create();
                    noti.graphic(new ImageView(imgError));
                    noti.title("ERROR");
                    noti.text("El CAMPO DE BUSQUEDA ESTA VACÍO");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();   
                    noti.show();
        }else{
            tipoOperacionBusquedaCheques = Operacion.BUSCAR;
            cargarChequesBuscadasPorId();
        }  
    }
    
    /* proceso para buscar cheque por fechas*/
    @FXML
    public void buscarPorFechas(){
        try{
            if(txtFechaInicio.getValue() == null || txtFechaFinal.getValue()==null){
                          Notifications noti = Notifications.create();
                          noti.graphic(new ImageView(imgError));
                          noti.title("ERROR");
                          noti.text("SELECCIONE FECHAS PARA PODER FILTRAR");
                          noti.position(Pos.BOTTOM_RIGHT);
                          noti.hideAfter(Duration.seconds(4));
                          noti.darkStyle();   
                          noti.show();
              }else{
                  tipoOperacionBusquedaCheques = Operacion.FILTRAR;
                  cargarChequesBuscadasPorFecha();
                }  
            }catch(Exception e){
               e.printStackTrace();
            }
    }
    
   
    public void buscarEncabezado(){
        for(int i=0; i<tblResultadoCheque.getItems().size(); i++){
            if(colNoChequeBuscado.getCellData(i) == noCheque){
                tblResultadoCheque.getSelectionModel().select(i);
                 break;
             }
        }
        cargarEnzabezadoBuscados();
    }      
               
    
        @FXML
    private void seleccionarElementosChequeBuscadas(MouseEvent event) {
        int index = tblResultadoCheque.getSelectionModel().getSelectedIndex();
        try{

            txtBusquedaCodigoChe.setValue(colNoChequeBuscado.getCellData(index).toString());
            
            cargarEnzabezadoBuscados();
            
        }catch(Exception ex){
              ex.printStackTrace();
        }
    }
    
    private void btnBuscarCheque(MouseEvent event) {
        buscarPorId();
    }
    
    
    private void btnFiltrarCheque(MouseEvent event) {
        buscarPorFechas();
    }
    
    @FXML
    private void btnCargarCheque(MouseEvent event) {
        cargarChequesBuscadas();
    }
        
}
