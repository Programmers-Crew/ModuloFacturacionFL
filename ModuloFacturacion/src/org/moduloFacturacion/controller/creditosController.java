package org.moduloFacturacion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
import org.moduloFacturacion.bean.AutoCompleteComboBoxListener;
import org.moduloFacturacion.bean.CambioScene;
import org.moduloFacturacion.bean.Creditos;
import org.moduloFacturacion.db.Conexion;

public class creditosController implements Initializable {

    @FXML
    private Pane buttonInicio;
    @FXML
    private AnchorPane anchor1;
    @FXML
    private JFXTextField txtCodigoCredito;
    @FXML
    private JFXDatePicker txtfechaInicioCredito;
    @FXML
    private JFXTextField txtMontoCredito;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXTextField txtDiasrestantesCredito;
    @FXML
    private JFXDatePicker txtFechaFinalCredito;
    @FXML
    private TextArea txtDescripcionCredito; 
    @FXML
    private AnchorPane anchor2;
    @FXML
    private TableView<Creditos> tableProductos;
    @FXML
    private TableColumn<Creditos, Date > colFechaInicio;
    @FXML
    private TableColumn<Creditos, Date> colFechaFinal;
    @FXML
    private TableColumn<Creditos, Integer> colDiasRestantes;
    @FXML
    private TableColumn<Creditos, String> colDescripcion;
    @FXML
    private TableColumn<Creditos, String> colProveedor;
    @FXML
    private TableColumn<Creditos, Double> colMonto;
    @FXML
    private TableColumn<Creditos, String> colEstadoCredito;
    @FXML
    private ComboBox<String> cmbProveedorProducto1;
    @FXML
    private ComboBox<String> cmbProveedorCreditos;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private ComboBox<String> cmbFiltroCredito;
    @FXML
    private ComboBox<String> cmbBuscar;
    @FXML
    private JFXButton btnPagado;
    private AnchorPane anchor;

    

    Image imgError = new Image("org/moduloFacturacion/img/error.png");
    Image imgCorrecto= new Image("org/moduloFacturacion/img/correcto.png");
    
    CambioScene cambioScene = new CambioScene();
    
    String codigo = "";
    @FXML
    private JFXButton btnReporte;
    @FXML
    private AnchorPane anchorCreditos;

    @FXML
    private void validarPrecioProducto(KeyEvent event) {
    }
    
    public enum Operacion{AGREGAR,GUARDAR,ELIMINAR,BUSCAR,ACTUALIZAR,CANCELAR,FILTRAR,NINGUNO};
    public Operacion tipoOperacion= Operacion.NINGUNO;
    public Operacion cancelar = Operacion.NINGUNO;

    ObservableList<String> listaProveedores;
    ObservableList<String> listaEstadoCredito;
    ObservableList<String> listaFiltroCredito;
    ObservableList<Creditos> listaCreditos;
    ObservableList<String> listaCmbCodigoCreditos;
    ObservableList<String> listaCmbBuscar;
    ObservableList<String> listaCmbFiltro;


    @FXML
    private JFXDatePicker txtFechaInicio;
    @FXML
    private JFXDatePicker txtFechaFinal;
    @FXML
    private JFXButton btnFiltrar;
    
     //EVENTOS DE LA VISTA DE PROVEEDORES
    public void limpiarTextProveedores(){
          txtCodigoCredito.setText("");          
          txtDescripcionCredito.setText("");
          txtMontoCredito.setText("");
          txtDiasrestantesCredito.setText("");
          cmbProveedorCreditos.setValue("");
          cmbProveedorProducto1.setValue("");

                    
    }
    
    public void desactivarControlesCreditos(){    
        btnEditar.setDisable(true);
        btnEliminar.setDisable(true);
    }
    
    public void activarControlesCreditos(){        
        btnEditar.setDisable(true);
        btnEliminar.setDisable(true);
    }
    
    public void desactivarTextCreditos(){
          txtCodigoCredito.setEditable(false);
          txtfechaInicioCredito.setEditable(false);
          txtFechaFinalCredito.setEditable(false);
          txtDescripcionCredito.setEditable(false);
          txtMontoCredito.setEditable(false);
          txtDiasrestantesCredito.setEditable(false);
          cmbProveedorCreditos.setDisable(true);
          cmbProveedorProducto1.setDisable(true);
        
    }
    
    public void activarTextCreditos(){
          txtCodigoCredito.setEditable(true);
          txtfechaInicioCredito.setEditable(true);
          txtFechaFinalCredito.setEditable(true);
          txtDescripcionCredito.setEditable(true);
          txtMontoCredito.setEditable(true);
          txtDiasrestantesCredito.setEditable(true);
          cmbProveedorCreditos.setDisable(false);
          cmbProveedorProducto1.setDisable(false);
    }
    
    
    public ObservableList<Creditos> getCreditos(){
        ArrayList<Creditos> lista = new ArrayList();
        ArrayList<String> comboCodigo = new ArrayList();
        String sql = "{call SpListarCreditos()}";
        int x=0;
        
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(new Creditos(
                            rs.getInt("idCredito"),
                            rs.getDate("creaditoFechaInicio"),
                            rs.getDate("creditoFechaFinal"),
                            rs.getInt("creditoDiasRestantes"),
                            rs.getString("creditoDesc"),
                            rs.getString("proveedorNombre"),
                            rs.getDouble("creditoMonto"),
                            rs.getString("estadoCreditoDesc")
                ));
                comboCodigo.add(x, rs.getString("idCredito"));
                x++;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        listaCmbCodigoCreditos = FXCollections.observableList(comboCodigo);
        cmbBuscar.setItems(listaCmbCodigoCreditos);

        return listaCreditos = FXCollections.observableList(lista);
    }

    public void cargarCreditos(){
        tableProductos.setItems(getCreditos());
        
        colFechaInicio.setCellValueFactory(new PropertyValueFactory("creaditoFechaInicio"));
        colFechaFinal.setCellValueFactory(new PropertyValueFactory("creditoFechaFinal"));  
        colDiasRestantes.setCellValueFactory(new PropertyValueFactory("creditoDiasRestantes"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("creditoDesc"));
        colProveedor.setCellValueFactory(new PropertyValueFactory("proveedorNombre"));
        colMonto.setCellValueFactory(new PropertyValueFactory("creditoMonto"));
        colEstadoCredito.setCellValueFactory(new PropertyValueFactory("estadoCreditoDesc"));

        new AutoCompleteComboBoxListener(cmbBuscar);
        new AutoCompleteComboBoxListener(cmbProveedorCreditos);
        new AutoCompleteComboBoxListener(cmbProveedorProducto1);
    }  

    /* FILTRAR POR FECHAS*/
        public ObservableList<Creditos> getFiltroCreditos(){
        ArrayList<Creditos> lista = new ArrayList();
        ArrayList<String> comboCodigo = new ArrayList();
        
        String  sql = "{call SpFiltrarCredito('"+txtFechaInicio.getValue()+"','"+txtFechaFinal.getValue()+"')}";
                    int x=0;
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(new Creditos(
                            rs.getInt("idCredito"),
                            rs.getDate("creaditoFechaInicio"),
                            rs.getDate("creditoFechaFinal"),
                            rs.getInt("creditoDiasRestantes"),
                            rs.getString("creditoDesc"),
                            rs.getString("proveedorNombre"),
                            rs.getDouble("creditoMonto"),
                            rs.getString("estadoCreditoDesc")
                ));
                comboCodigo.add(x, rs.getString("idCredito"));
                x++;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        listaCmbCodigoCreditos = FXCollections.observableList(comboCodigo);
        cmbBuscar.setItems(listaCmbCodigoCreditos);
            System.out.println("FILTRO");
            System.out.println(sql);

        return listaCreditos = FXCollections.observableList(lista);
    }
      
    public void cargarCreditosFiltrados(){
        tableProductos.setItems(getFiltroCreditos());
        
        colFechaInicio.setCellValueFactory(new PropertyValueFactory("creaditoFechaInicio"));
        colFechaFinal.setCellValueFactory(new PropertyValueFactory("creditoFechaFinal"));  
        colDiasRestantes.setCellValueFactory(new PropertyValueFactory("creditoDiasRestantes"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("creditoDesc"));
        colProveedor.setCellValueFactory(new PropertyValueFactory("proveedorNombre"));
        colMonto.setCellValueFactory(new PropertyValueFactory("creditoMonto"));
        colEstadoCredito.setCellValueFactory(new PropertyValueFactory("estadoCreditoDesc"));

        new AutoCompleteComboBoxListener(cmbBuscar);
        new AutoCompleteComboBoxListener(cmbProveedorCreditos);
        new AutoCompleteComboBoxListener(cmbProveedorProducto1);
    }  
    /* FILTRO DE  PROVEEDORES*/
    public ObservableList<Creditos> getFiltroCreditospProveedor(){
        ArrayList<Creditos> lista = new ArrayList();
        ArrayList<String> comboCodigo = new ArrayList();
        
         String sql = "{call SpFiltrarCreditoEmpresa('"+txtFechaInicio.getValue()+"','"+txtFechaFinal.getValue()+"','"+cmbBuscar.getValue()+"')}";
                    int x=0;  
            try{
                PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    lista.add(new Creditos(
                                rs.getInt("idCredito"),
                                rs.getDate("creaditoFechaInicio"),
                                rs.getDate("creditoFechaFinal"),
                                rs.getInt("creditoDiasRestantes"),
                                rs.getString("creditoDesc"),
                                rs.getString("proveedorNombre"),
                                rs.getDouble("creditoMonto"),
                                rs.getString("estadoCreditoDesc")
                    ));
                    comboCodigo.add(x, rs.getString("idCredito"));
                    x++;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            listaCmbCodigoCreditos = FXCollections.observableList(comboCodigo);
            cmbBuscar.setItems(listaCmbCodigoCreditos);
            System.out.println("FILTRO EMPRESA");
            System.out.println(sql);
        

        return listaCreditos = FXCollections.observableList(lista);
    }
    
        public void cargarCreditosFiltradosProveedores(){
        tableProductos.setItems(getFiltroCreditospProveedor());
        
        colFechaInicio.setCellValueFactory(new PropertyValueFactory("creaditoFechaInicio"));
        colFechaFinal.setCellValueFactory(new PropertyValueFactory("creditoFechaFinal"));  
        colDiasRestantes.setCellValueFactory(new PropertyValueFactory("creditoDiasRestantes"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("creditoDesc"));
        colProveedor.setCellValueFactory(new PropertyValueFactory("proveedorNombre"));
        colMonto.setCellValueFactory(new PropertyValueFactory("creditoMonto"));
        colEstadoCredito.setCellValueFactory(new PropertyValueFactory("estadoCreditoDesc"));

        new AutoCompleteComboBoxListener(cmbBuscar);
        new AutoCompleteComboBoxListener(cmbProveedorCreditos);
        new AutoCompleteComboBoxListener(cmbProveedorProducto1);
    }  
    
    public void cargarCombo(){
        ArrayList<String>lista = new ArrayList();
        
        lista.add(0,"CÓDIGO");
        lista.add(1,"PROVEEDOR");
        
        listaCmbFiltro = FXCollections.observableList(lista);
        
        cmbFiltroCredito.setItems(listaCmbFiltro);
    }
    
    public void llenarComboProveedores(){
        ArrayList<String> lista = new ArrayList();
        String sql= "{call SpListarProveedores()}";
            int x =0;
        
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(x, rs.getString("proveedorNombre"));
                x++;
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
            
            Notifications noti = Notifications.create();
            noti.graphic(new ImageView(imgError));
            noti.title("ERROR AL CARGAR DATOS CMB");
            noti.text("Error al cargar la base de datos");
            noti.position(Pos.BOTTOM_RIGHT);
            noti.hideAfter(Duration.seconds(4));
            noti.darkStyle();
            noti.show();
        }
        listaProveedores = FXCollections.observableList(lista);
        cmbProveedorCreditos.setItems(listaProveedores);
    }
    
    public void llenarComboEstado(){
        ArrayList<String> lista = new ArrayList();
        String sql= "{call SpListarEstado()}";
            int x =0;
        
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(x, rs.getString("estadoCreditoDesc"));
                x++;
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
            
            Notifications noti = Notifications.create();
            noti.graphic(new ImageView(imgError));
            noti.title("ERROR AL CARGAR DATOS CMB");
            noti.text("Error al cargar la base de datos");
            noti.position(Pos.BOTTOM_RIGHT);
            noti.hideAfter(Duration.seconds(4));
            noti.darkStyle();
            noti.show();
        }
        listaEstadoCredito = FXCollections.observableList(lista);
        cmbProveedorProducto1.setItems(listaEstadoCredito);
    }
    
    @FXML
    public void buscarCreditosFiltrados(){
        try{
            if(cmbFiltroCredito.getValue().isEmpty()){
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
                    tipoOperacion = Operacion.FILTRAR;
                    cargarCreditosFiltrados();
                    System.out.println("BUSCAR VACIO");
                }  
            }else{
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
                      tipoOperacion = Operacion.FILTRAR;
                      cargarCreditosFiltradosProveedores();
                      System.out.println("BUSCAR LLENO");
                }  
            }          
        try{
        }catch(Exception e){
            e.printStackTrace();
        }
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void limpiarBuscar(MouseEvent event) {
        limpiarTextProveedores();
        desactivarControlesCreditos();
    }
    
    @FXML
    private void comboFiltro(ActionEvent event) {
        btnBuscar.setDisable(false);
        ArrayList<String> lista = new ArrayList();
        
        String sql = "";
        if(cmbFiltroCredito.getValue().equals("CÓDIGO")){
              sql ="{call SpListarComboFiltro()}";
            }else if(cmbFiltroCredito.getValue().equals("PROVEEDOR")){
              sql ="{call SpListarComboFiltroProveedor()}";      
        }
        System.out.println(sql);
        int x=0;
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                 if(cmbFiltroCredito.getValue().equals("CÓDIGO")){
                     lista.add(x, rs.getString("idCredito"));
                     
                }else if(cmbFiltroCredito.getValue().equals("PROVEEDOR")){
                    lista.add(x, rs.getString("proveedorNombre"));
                }
                 x++;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
       listaCmbBuscar = FXCollections.observableList(lista);
       cmbBuscar.setItems(listaCmbBuscar);
       new AutoCompleteComboBoxListener(cmbBuscar);
    }
    
    @FXML
    private void seleccionarElementos(MouseEvent event) {
        
        int index = tableProductos.getSelectionModel().getSelectedIndex();
        try{
            txtCodigoCredito.setText(listaCreditos.get(index).getIdCredito().toString());
            txtfechaInicioCredito.setValue(LocalDate.parse(colFechaInicio.getCellData(index).toString()));
            txtFechaFinalCredito.setValue(LocalDate.parse(colFechaFinal.getCellData(index).toString()));
            txtDiasrestantesCredito.setText(colDiasRestantes.getCellData(index).toString());
            txtDescripcionCredito.setText(colDescripcion.getCellData(index));
            txtMontoCredito.setText(colMonto.getCellData(index).toString());
            cmbProveedorCreditos.setValue(colProveedor.getCellData(index));
            cmbProveedorProducto1.setValue(colEstadoCredito.getCellData(index));

            btnEliminar.setDisable(false);
            btnEditar.setDisable(false);
            
            activarTextCreditos();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
      @FXML
    private void regresar(MouseEvent event) throws IOException {
        String menu1 = "org/moduloFacturacion/view/menuPrincipal.fxml";
        cambioScene.Cambio(menu1, (Stage) anchorCreditos.getScene().getWindow());
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cargarCreditos();
       cargarCombo();
       llenarComboProveedores();
       llenarComboEstado();
       txtDiasrestantesCredito.setDisable(true);
       cmbFiltroCredito.setValue("");
    }    
    
        public void accionCreditos(){
        switch(tipoOperacion){
            case AGREGAR:
                tipoOperacion = Operacion.GUARDAR;
                cancelar = Operacion.CANCELAR;
                desactivarControlesCreditos();
                btnAgregar.setText("GUARDAR");
                btnEliminar.setText("CANCELAR");
                btnEliminar.setDisable(false);
                activarTextCreditos();
                limpiarTextProveedores();
                btnBuscar.setDisable(true);
                break;
            case CANCELAR:
                tipoOperacion = Operacion.NINGUNO;
                desactivarControlesCreditos();
                desactivarTextCreditos();
                btnAgregar.setText("AGREGAR");
                btnEliminar.setText("ELIMINAR");
                limpiarTextProveedores();
                btnBuscar.setDisable(false);
                cancelar = Operacion.NINGUNO;
                break;
        }
    }

        public void accionCreditos(String sql){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        PreparedStatement ps;
        ResultSet rs;
        Notifications noti = Notifications.create();
        ButtonType buttonTypeSi = new ButtonType("Si");
        ButtonType buttonTypeNo = new ButtonType("No");
        switch(tipoOperacion){
            
            case GUARDAR:
                alert.setTitle("AGREGAR REGISTRO");
                alert.setHeaderText("AGREGAR REGISTRO");
                alert.setContentText("¿Está seguro que desea guardar este registro?");
                
                alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);
                
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == buttonTypeSi ){
                    try {
                        ps = Conexion.getIntance().getConexion().prepareCall(sql);
                        ps.execute();
                        
                        noti.graphic(new ImageView(imgCorrecto));
                        noti.title("OPERACIÓN EXITOSA");
                        noti.text("SE HA AGREGADO EXITOSAMENTE EL REGISTRO");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                        tipoOperacion = Operacion.CANCELAR;
                        accionCreditos();
                        cargarCreditos();
                        limpiarTextProveedores();
                        
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                        noti.graphic(new ImageView(imgError));
                        noti.title("ERROR AL AGREGAR");
                        noti.text("HA OCURRIDO UN ERROR AL GUARDAR EL REGISTRO");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                        tipoOperacion = Operacion.CANCELAR;
                        accionCreditos();
                    }
                }else{
                    
                    noti.graphic(new ImageView(imgError));
                    noti.title("OPERACIÓN CANCELADA");
                    noti.text("NO SE HA AGREGADO EL REGISTRO");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacion = Operacion.CANCELAR;
                    accionCreditos();
                }
                break;
            case ELIMINAR:
                 alert.setTitle("ELIMINAR REGISTRO");
                alert.setHeaderText("ELIMINAR REGISTRO");
                alert.setContentText("¿Está seguro que desea Eliminar este registro?");
               
                alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);
                
                Optional<ButtonType> resultEliminar = alert.showAndWait();
                
                if(resultEliminar.get() == buttonTypeSi){
                    try {
                        ps = Conexion.getIntance().getConexion().prepareCall(sql);
                        ps.execute();
                        
                        noti.graphic(new ImageView(imgCorrecto));
                        noti.title("OPERACIÓN EXITOSA");
                        noti.text("SE HA ELIMINADO EXITOSAMENTE EL REGISTRO");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                        cargarCreditos();
                        tipoOperacion = Operacion.CANCELAR;
                        accionCreditos();
                        
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                        
                        
                        noti.graphic(new ImageView(imgError));
                        noti.title("ERROR AL ELIMINAR");
                        noti.text("HA OCURRIDO UN ERROR AL ELIMINAR EL REGISTRO");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                        tipoOperacion = Operacion.CANCELAR;
                        accionCreditos();
                    }
                }else{
                     noti.graphic(new ImageView(imgError));
                    noti.title("OPERACIÓN CANCELADA");
                    noti.text("NO SE HA ELIMINADO EL REGISTRO");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacion = Operacion.CANCELAR;
                    accionCreditos();
                }
                break;
            case ACTUALIZAR:
                alert.setTitle("ACTUALIZAR REGISTRO");
                alert.setHeaderText("ACTUALIZAR REGISTRO");
                alert.setContentText("¿Está seguro que desea Actualizar este registro?");
               
                alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);
                
                Optional<ButtonType> resultactualizar = alert.showAndWait();
                if(resultactualizar.get() == buttonTypeSi ){
                    try {
                        ps = Conexion.getIntance().getConexion().prepareCall(sql);
                        ps.execute();
                        
                        noti.graphic(new ImageView(imgCorrecto));
                        noti.title("OPERACIÓN EXITOSA");
                        noti.text("SE HA ACTUALIZADO EXITOSAMENTE EL REGISTRO");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                        tipoOperacion = Operacion.CANCELAR;
                        accionCreditos();
                        cargarCreditos();
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                        noti.graphic(new ImageView(imgError));
                        noti.title("ERROR AL ACTUALIZAR");
                        noti.text("HA OCURRIDO UN ERROR AL ACTUALIZAR EL REGISTRO");
                        noti.position(Pos.BOTTOM_RIGHT);
                        noti.hideAfter(Duration.seconds(4));
                        noti.darkStyle();
                        noti.show();
                        tipoOperacion = Operacion.CANCELAR;
                        accionCreditos();
                    }
                }else{
                    noti.graphic(new ImageView(imgError));
                    noti.title("OPERACIÓN CANCELADA");
                    noti.text("NO SE HA ACTUALIZAR EL REGISTRO");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacion = Operacion.CANCELAR;
                    accionCreditos();
                }
                break;
            case BUSCAR:
                 try{
                    ps = Conexion.getIntance().getConexion().prepareCall(sql);
                    rs = ps.executeQuery();
                    while(rs.next()){
                        txtCodigoCredito.setText(rs.getString("idCredito"));
                        txtfechaInicioCredito.setValue(LocalDate.parse(rs.getString("creaditoFechaInicio")));
                        txtFechaFinalCredito.setValue(LocalDate.parse(rs.getString("creditoFechaFinal")));
                        txtDescripcionCredito.setText(rs.getString("creditoDesc"));
                        txtMontoCredito.setText(rs.getString("creditoMonto"));
                        txtDiasrestantesCredito.setText(rs.getString("creditoDiasRestantes"));
                        cmbProveedorCreditos.setValue(rs.getString("proveedorNombre"));
                        cmbProveedorProducto1.setValue(rs.getString("estadoCreditoDesc"));

                        codigo = rs.getString("idCredito");
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
                    tipoOperacion = Operacion.CANCELAR;
                    accionCreditos();
                }
                break;
            case FILTRAR:
                 try{
                    ps = Conexion.getIntance().getConexion().prepareCall(sql);
                    rs = ps.executeQuery();
                    int numero=0;
                }catch(SQLException ex){
                    ex.printStackTrace();
                    noti.graphic(new ImageView(imgError));
                    noti.title("ERROR AL BUSCAR");
                    noti.text("HA OCURRIDO UN ERROR EN LA BASE DE DATOS");
                    noti.position(Pos.BOTTOM_RIGHT);
                    noti.hideAfter(Duration.seconds(4));
                    noti.darkStyle();
                    noti.show();
                    tipoOperacion = Operacion.CANCELAR;
                }
                break;
        }
    }
    
    
    public void buscar(){
        if(cmbBuscar.getValue().equals("")){
            Notifications noti = Notifications.create();
            noti.graphic(new ImageView(imgError));
            noti.title("ERROR");
            noti.text("El CAMPO DE CÓDIGO ESTA VACÍO");
            noti.position(Pos.BOTTOM_RIGHT);
            noti.hideAfter(Duration.seconds(4));
            noti.darkStyle();   
            noti.show();
        }else{
            if(cmbFiltroCredito.getValue().equals("CÓDIGO")){
                tipoOperacion = Operacion.BUSCAR;
                    String sql = "{ call SpBuscarCredito('"+cmbBuscar.getValue()+"')}";
                    accionCreditos(sql);
            }else if(cmbFiltroCredito.getValue().equals("PROVEEDOR")){
                    tipoOperacion = Operacion.BUSCAR;
                    String sql = "{ call SpBuscarCreditoProveedor('"+cmbBuscar.getValue()+"')}";
                    accionCreditos(sql);
            }
        }
    }
    
    public void marcarPagado(){
        String sql = "";
        sql = "{call SpMarcarPagado('"+txtCodigoCredito.getText()+"')}";
        Notifications noti = Notifications.create();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType buttonTypeSi = new ButtonType("Si");
        ButtonType buttonTypeNo = new ButtonType("No");
        
        alert.setTitle("MARCAR COMO PAGADO");
        alert.setHeaderText("MARCAR COMO PAGADO");
        alert.setContentText("¿Está seguro que desea marcar como pagado este credito?");
                
        alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == buttonTypeSi ){
            try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
              noti.graphic(new ImageView(imgCorrecto));
              noti.title("OPERACIÓN EXITOSA");
              noti.text("SE HA MARCADO COMO PAGADO");
              noti.position(Pos.BOTTOM_RIGHT);
              noti.hideAfter(Duration.seconds(4));
              noti.darkStyle();
              noti.show();
            }catch(SQLException ex){
                ex.printStackTrace();
               noti.graphic(new ImageView(imgCorrecto));
              noti.title("OPERACIÓN EXITOSA");
              noti.text("ERROR AL MARCAR, INTENTA DE NUEVO");
              noti.position(Pos.BOTTOM_RIGHT);
              noti.hideAfter(Duration.seconds(4));
              noti.darkStyle();
              noti.show();
            }
        }else{
            noti.graphic(new ImageView(imgError));
            noti.title("OPERACIÓN CANCELADA");
            noti.text("NO SE HA MARCARCADO COMO PAGADO");
            noti.position(Pos.BOTTOM_RIGHT);
            noti.hideAfter(Duration.seconds(4));
            noti.darkStyle();
            noti.show();
        }
    }
    
    @FXML
    private void btnMarcarPagado(MouseEvent event) {
        marcarPagado();
    }
    
    public String verficarProveedor(String proveedor){
        String sql = "{call SpBuscarProveedoresNombre('"+proveedor+"')}";
        String codigoProveedor="";
        try{
            PreparedStatement ps = Conexion.getIntance().getConexion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                codigoProveedor = rs.getString("proveedorId");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return codigoProveedor;
    }
    
    @FXML
    private void btnAgregar(MouseEvent event) {
        if(tipoOperacion == Operacion.GUARDAR){
            if(txtfechaInicioCredito.getValue().equals("") || txtFechaFinalCredito.getValue().equals("") || txtDescripcionCredito.getText().isEmpty() || cmbProveedorCreditos.getValue().isEmpty() ||txtMontoCredito.getText().isEmpty() ||cmbProveedorProducto1.getValue().isEmpty()){
                Notifications noti = Notifications.create();
                noti.graphic(new ImageView(imgError));
                noti.title("ERROR");
                noti.text("HAY CAMPOS VACÍOS");
                noti.position(Pos.BOTTOM_RIGHT);
                noti.hideAfter(Duration.seconds(4));
                noti.darkStyle();   
                noti.show();
            }else{
                Creditos nuevoCredito = new Creditos();
                    nuevoCredito.setCreaditoFechaInicio(java.sql.Date.valueOf( txtfechaInicioCredito.getValue()));
                    nuevoCredito.setCreditoFechaFinal(java.sql.Date.valueOf( txtFechaFinalCredito.getValue()));
                    nuevoCredito.setCreditoDesc(txtDescripcionCredito.getText());
                    nuevoCredito.setProveedorNombre(cmbProveedorCreditos.getValue());
                    nuevoCredito.setCreditoMonto(Double.parseDouble(txtMontoCredito.getText()));
                    
                    Integer codigoEstado = 0;
                    if(cmbProveedorProducto1.getValue().equals("PENDIENTE")){
                        codigoEstado = 1;
                    }else if(cmbProveedorProducto1.getValue().equals("PAGADO")){
                        codigoEstado = 2;
                    }else if(cmbProveedorProducto1.getValue().equals("VENCIDO")){
                        codigoEstado = 3;
                    }
                    System.out.println(nuevoCredito.getCreaditoFechaInicio());
                    System.out.println(verficarProveedor(nuevoCredito.getProveedorNombre()));
                    System.out.println(codigoEstado);

                String sql = "{call SpAgregarCredito('"+nuevoCredito.getCreaditoFechaInicio()+"','"+nuevoCredito.getCreditoFechaFinal()+"','"+nuevoCredito.getCreditoDesc()+"','"+verficarProveedor(nuevoCredito.getProveedorNombre())+"','"+nuevoCredito.getCreditoMonto()+"','"+codigoEstado+"')}";
                    accionCreditos(sql);
                }
        }else{
            tipoOperacion = Operacion.AGREGAR;
            accionCreditos();
        }
    }

    @FXML
    private void btnEliminar(MouseEvent event) {
    }

    @FXML
    private void btnEditar(MouseEvent event) {
    }


    @FXML
    private void btnBuscar(MouseEvent event) {
        buscar();
    }

    @FXML
    private void cmbBuscar(ActionEvent event) {
    }

    @FXML
    private void atajosProductos(KeyEvent event) {
    }


}
