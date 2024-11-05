/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientejavafxml;

import clientejavafxml.utilidades.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.dao.ColaboradorDAO;
import modelo.pojo.Colaborador;
import modelo.pojo.Mensaje;
import observador.NotificadorOperaciones;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class FXMLAdminColaboradorController implements Initializable, NotificadorOperaciones {

    private ObservableList<Colaborador> OLcolaboradores;

    @FXML
    private TableColumn colNumeroPersonal;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn colFechaNacimiento;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colTipo;
    @FXML
    private TableView<Colaborador> tblColaboradores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarInformacionTabla();
    }

    @FXML
    private void agregarColaborador(ActionEvent event) {
        irFormulario(this, null);
    }

    @FXML
    private void editarColaborador(ActionEvent event) {
        Colaborador colaborador = tblColaboradores.getSelectionModel().getSelectedItem();
        if (colaborador != null) {
            irFormulario(this, colaborador);
        } else {
            Utilidades.AletaSimple(Alert.AlertType.WARNING, "SELECCIONE UN ELEMENTO EN LA TABLA PARA CONTINUAR", "Error");
        }
    }

    @FXML
    private void eliminarColaborador(ActionEvent event) {
        Colaborador colaborador = tblColaboradores.getSelectionModel().getSelectedItem();

        if (colaborador != null) {
            Mensaje mjs = ColaboradorDAO.eliminarColaborador(colaborador.getIdColaborador());

            if (!mjs.isError()) {
                Utilidades.AletaSimple(Alert.AlertType.INFORMATION, "El colaborador se ha eliminado con exito", "Eliminacion exitosa");
                cargarInformacionTabla();
            } else {
                Utilidades.AletaSimple(Alert.AlertType.ERROR, mjs.getContenido(), "Error al eliminar");
            }
        } else {
            Utilidades.AletaSimple(Alert.AlertType.WARNING, "SELECCIONE UN ELEMENTO EN LA TABLA PARA CONTINUAR", "Error");
        }
    }

    private void configurarTabla() {
        colNumeroPersonal.setCellValueFactory(new PropertyValueFactory("numeroPersonal"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        colTipo.setCellValueFactory(new PropertyValueFactory("rol"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));
    }

    private void cargarInformacionTabla() {
        OLcolaboradores = FXCollections.observableArrayList();
        List<Colaborador> listaWS = ColaboradorDAO.obtenerColaboradores();
        OLcolaboradores.addAll(listaWS);
        tblColaboradores.setItems(OLcolaboradores);

    }

    private void irFormulario(NotificadorOperaciones observador, Colaborador colaborador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormularioColaborador.fxml"));
            Parent root = loader.load();
            FXMLFormularioColaboradorController controlador = loader.getController();
            controlador.inicializarValores(observador, colaborador);

            Stage escenarioAdministrador = new Stage();
            Scene scene = new Scene(root);
            escenarioAdministrador.setScene(scene);
            escenarioAdministrador.setTitle("Administrador de colaboradores");
            escenarioAdministrador.initModality(Modality.APPLICATION_MODAL);
            escenarioAdministrador.showAndWait();
        } catch (Exception e) {
            Utilidades.AletaSimple(Alert.AlertType.ERROR, e.getMessage(), "Error");
        }
    }

    @Override
    public void notificacionOperacion(String tipo, String nombre) {
        System.out.println(nombre);
        System.out.println(tipo);
        cargarInformacionTabla();

    }
}
