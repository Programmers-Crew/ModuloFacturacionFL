/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.moduloFacturacion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author davis
 */
public class creditosController implements Initializable {

    @FXML
    private Pane buttonInicio;
    @FXML
    private Pane buttonProveedor;
    @FXML
    private AnchorPane anchor1;
    @FXML
    private JFXTextField txtCodigoCredito;
    @FXML
    private JFXTextField txtfechaInicioCredito;
    @FXML
    private ComboBox<?> cmbProveedorCreditos;
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
    private JFXTextField txtFechaFinalCredito;
    @FXML
    private TextArea txtDescripcionCredito;
    @FXML
    private ComboBox<?> cmbProveedorProducto1;
    @FXML
    private AnchorPane anchor2;
    @FXML
    private TableView<?> tableProductos;
    @FXML
    private TableColumn<?, ?> colFechaInicio;
    @FXML
    private TableColumn<?, ?> colFechaFinal;
    @FXML
    private TableColumn<?, ?> colDescripcion;
    @FXML
    private TableColumn<?, ?> colProveedor;
    @FXML
    private TableColumn<?, ?> colMonto;
    @FXML
    private TableColumn<?, ?> colDiasRestantes;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private ComboBox<?> cmbFiltroCredito;
    @FXML
    private ComboBox<?> cmbBuscar;

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
    private void btnEliminar(MouseEvent event) {
    }

    @FXML
    private void btnEditar(MouseEvent event) {
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
    private void cmbBuscar(ActionEvent event) {
    }

    @FXML
    private void atajosProductos(KeyEvent event) {
    }

    @FXML
    private void cargarProductos(Event event) {
    }
    
}
