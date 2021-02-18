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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author davis
 */
public class chequesController implements Initializable {

    @FXML
    private Pane buttonInicio;
    @FXML
    private Pane buttonProveedor;
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
    private TableView<?> tableProductos;
    @FXML
    private TableColumn<?, ?> colNumeroCuenta;
    @FXML
    private TableColumn<?, ?> colDescripcion;
    @FXML
    private TableColumn<?, ?> colValor;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresar(MouseEvent event) {
    }

    @FXML
    private void buttonProveedor(MouseEvent event) {
    }

    @FXML
    private void validarPrecioProducto(KeyEvent event) {
    }

    @FXML
    private void btnAgregar(MouseEvent event) {
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
