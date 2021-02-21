/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.moduloFacturacion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.moduloFacturacion.bean.CambioScene;
import org.moduloFacturacion.bean.Chequedetalle;
import org.moduloFacturacion.bean.Letras;
import org.moduloFacturacion.db.Conexion;

/**
 * FXML Controller class
 *
 * @author davis
 */
public class chequesController implements Initializable {

  
    Letras letras = new Letras();
    DecimalFormat twoDForm = new DecimalFormat("#.00");

    
    
    public enum Operacion{AGREGAR,GUARDAR,ELIMINAR,BUSCAR,ACTUALIZAR,CANCELAR,NINGUNO, VENDER,FILTRAR,CARGAR};
    public Operacion cancelar = Operacion.NINGUNO;
    public Operacion tipoOperacionChequeDetalle = Operacion.NINGUNO; 
    Image imgError = new Image("org/moduloFacturacion/img/error.png");
    Image imgCorrecto= new Image("org/moduloFacturacion/img/correcto.png");
    Image imgWarning = new Image("org/moduloFacturacion/img/warning.png");
    CambioScene cambioScene = new CambioScene();  
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
    private JFXTextField sumaCheque;
    @FXML
    private JFXTextField sumaLetras;
    @FXML
    private JFXTextField numeroCuenta;
    @FXML
    private TextArea descripcionPago;
    @FXML
    private JFXTextField chequeValor;
    @FXML
    private JFXButton btnAgregarDesc;
    @FXML
    private AnchorPane anchor2;
    @FXML
    private TableColumn<Chequedetalle, String> colNumeroCuenta;
    @FXML
    private TableColumn<Chequedetalle, String> colDescripcion;
    @FXML
    private TableColumn<Chequedetalle, Double> colValor;
    @FXML
    private TableView<Chequedetalle> tableChequeDetalle;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private ComboBox<?> cmbFiltroProductos;
    @FXML
    private TextField totalValor;
    @FXML
    private Button btnImprimir;
    @FXML
    private AnchorPane anchor3;
    @FXML
    private TableView<?> tblResultadoFactura;
    @FXML
    private TableColumn<?, ?> colNumeroFacBuscado;
    @FXML
    private TableColumn<?, ?> colTotlalNeto;
    @FXML
    private TableColumn<?, ?> colTotalIva;
    @FXML
    private JFXComboBox<?> txtBusquedaCodigoFac;
    @FXML
    private JFXButton btnBuscarFiltro;
    @FXML
    private JFXButton btnFiltrarCheque;
    @FXML
    private JFXButton btnCargarFactura;
    @FXML
    private JFXDatePicker txtFechaFinal;
    @FXML
    private JFXDatePicker txtFechaInicio;
    @FXML
    private AnchorPane anchor4;
    @FXML
    private TableView<?> tblResultadoProducto;
    @FXML
    private TableColumn<?, ?> colProductoBuscado;
    @FXML
    private TableColumn<?, ?> colCantidadBuscada;
    @FXML
    private TableColumn<?, ?> colPrecioUnitBuscado;

    double totalChequeDetalle=0;
    ObservableList<Chequedetalle> listaCheque;
    LocalDate fechaActual = LocalDate.now();
    @FXML
    private AnchorPane anchor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }    
    public void limpiarTextChequeDetalle(){
        numeroCheque.setText("");
        chequeFecha.setText(fechaActual.toString());
        pagoOrden.setText("");
        sumaCheque.setText("");
        sumaLetras.setText("");
        numeroCuenta.setText("");
        descripcionPago.setText("");
        chequeValor.setText("");
    }
    
    public ObservableList<Chequedetalle>getCheque(){
        ArrayList<Chequedetalle> lista = new ArrayList();
        String sql = "{call SpListarChequeEncabezado()}";
        int x=0;
        double valorTotal=0;
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(new Chequedetalle(rs.getString("chequeDetalleCuenta"),rs.getString("chequeDetalleDesc"),
                rs.getDouble("chequeDetalleValor")));
                valorTotal=rs.getDouble("chequeDetalleValor");
                totalChequeDetalle=totalChequeDetalle+valorTotal;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        totalValor.setText(String.valueOf(totalChequeDetalle));
        return listaCheque = FXCollections.observableList(lista);
    }
    
    public void cargarDatos(){
        tableChequeDetalle.setItems(getCheque());
        colNumeroCuenta.setCellValueFactory(new PropertyValueFactory("chequeDetalleCuenta"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("chequeDetalleDesc"));
        colValor.setCellValueFactory(new PropertyValueFactory("chequeDetalleValor"));
        limpiarTextChequeDetalle();
    }
    public void accion(String sql){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        PreparedStatement ps;
        ResultSet rs;
        Notifications noti = Notifications.create();
        ButtonType buttonTypeSi = new ButtonType("Si");
        ButtonType buttonTypeNo = new ButtonType("No");
        
        switch(tipoOperacionChequeDetalle){
            case AGREGAR:
                try{
                    ps = Conexion.getIntance().getConexion().prepareCall(sql);
                    ps.execute();
                    
                    noti.graphic(new ImageView(imgCorrecto));
                    noti.title("OPERACIÓN EXITOSA");
                    noti.text("SE HA AGREGADO EXITOSAMENTE EL REGISTRO");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacionChequeDetalle = Operacion.CANCELAR;
                    cargarDatos();
                }catch(SQLException ex){
                    ex.printStackTrace();
                    noti.graphic(new ImageView(imgError));
                    noti.title("ERROR AL AGREGAR");
                    noti.text("HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO");
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
    private void btnAgregar(MouseEvent event) {
        if(numeroCheque.getText().equals("") || chequeFecha.getText().equals("") || pagoOrden.getText().equals("") || sumaCheque.getText().equals("") 
            || sumaLetras.getText().equals("") || numeroCuenta.getText().equals("") || descripcionPago.getText().equals("") || chequeValor.getText().equals("")){
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
            chequeNuevo.setChequeDetalleCuenta(numeroCuenta.getText());
            chequeNuevo.setChequeDetalleDesc(descripcionPago.getText());
            chequeNuevo.setChequeDetalleValor(Double.parseDouble(chequeValor.getText()));
            
            String sql = "{call SpAgregarChequeEncabezado('"+chequeNuevo.getChequeDetalleCuenta()+"','"+chequeNuevo.getChequeDetalleDesc()+"','"+chequeNuevo.getChequeDetalleValor()+"')}";
            tipoOperacionChequeDetalle = Operacion.AGREGAR;
            accion(sql);
            
            
        }
    }
      @FXML
    private void sumaEvent(KeyEvent event) {
        char letra = event.getCharacter().charAt(0);
        
        if(Character.isDigit(letra) || letra == '.'){
            
        }else{
            event.consume();
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
    private void validarNoCuenta(KeyEvent event) {
         char letra = event.getCharacter().charAt(0);
        
        if(Character.isDigit(letra)){
            
        }else{
            event.consume();
        }
    }

    
    @FXML
    private void convertirLetras(KeyEvent event) {
        if(!sumaCheque.getText().equals("")){
            sumaLetras.setText(letras.Convertir(twoDForm.format(Double.parseDouble(sumaCheque.getText())), true));
        }else{
            sumaCheque.setText("");
        }
    }
    
    
    @FXML
    private void seleccionarElementosProductos(MouseEvent event) {
    }

    @FXML
    private void btnBuscar(MouseEvent event) {
    }

    @FXML
    private void comboFiltro(ActionEvent event) {
    }

    @FXML
    private void atajosProductos(KeyEvent event) {
    }

    @FXML
    private void cargarProductos(Event event) {
    }

    @FXML
    private void seleccionarElementosFacturasBuscadas(MouseEvent event) {
    }

    @FXML
    private void buscarCheque(MouseEvent event) {
    }

    @FXML
    private void btnFiltrarCheque(MouseEvent event) {
    }

    @FXML
    private void btnCargarFiltro(MouseEvent event) {
    }

    @FXML
    private void fechaInicio(ActionEvent event) {
    }

    @FXML
    private void cargarCategoria(Event event) {
    }
    
}
