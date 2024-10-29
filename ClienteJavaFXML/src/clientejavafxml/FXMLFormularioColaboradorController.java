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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.ColaboradorDAO;
import modelo.dao.RolDAO;
import modelo.pojo.Colaborador;
import modelo.pojo.Mensaje;
import modelo.pojo.Rol;
import observador.NotificadorOperaciones;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class FXMLFormularioColaboradorController implements Initializable {

    @FXML
    private ComboBox<Rol> cbRol;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfNoPersonal;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfRFC;
    @FXML
    private TextField tfCURP;
    @FXML
    private TextField tfPassword;

    private NotificadorOperaciones observador;
    private Colaborador colaboradorEdicion;
    private boolean isEditable = false;
    List<Rol> roles = RolDAO.obtenerRol();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarTiposUsuario();
    }

    public void inicializarValores(NotificadorOperaciones observador, Colaborador colaboradorEdicion) {
        this.observador = observador;
        this.colaboradorEdicion = colaboradorEdicion;
        if (colaboradorEdicion != null) {
            isEditable = true;
        }
        cargarDatosEdicion();
    }

    private void cargarDatosEdicion() {
        tfNombre.setText(colaboradorEdicion.getNombre());
        tfApellidoMaterno.setText(colaboradorEdicion.getApellidoMaterno());
        tfApellidoPaterno.setText(colaboradorEdicion.getApellidoPaterno());
        tfCURP.setText(colaboradorEdicion.getCurp());
        tfCorreo.setText(colaboradorEdicion.getCorreo());
        tfNoPersonal.setText(colaboradorEdicion.getNumeroPersonal());
        tfNoPersonal.setEditable(false);
        tfPassword.setText(colaboradorEdicion.getPassword());
        tfRFC.setText(colaboradorEdicion.getRfc());
        tfTelefono.setText(colaboradorEdicion.getTelefono());

        int idRol = colaboradorEdicion.getIdRol();
        if (idRol > 0 && idRol <= cbRol.getItems().size()) {
            cbRol.getSelectionModel().select(idRol - 1);
        }

    }

    private void cargarTiposUsuario() {
        //Temporal
        /*ObservableList<Rol> tipoColaboradores = FXCollections.observableArrayList();
        tipoColaboradores.add(new Rol(1, "Colaborador General"));
        tipoColaboradores.add(new Rol(2, "Entrenador"));
        cbRol.setItems(tipoColaboradores);*/

        if (roles != null && !roles.isEmpty()) {
            ObservableList<Rol> listaObservableRoles = FXCollections.observableArrayList(roles);
            cbRol.setItems(listaObservableRoles);
        } else {
            Utilidades.AletaSimple(Alert.AlertType.ERROR, "Hubo un error al momento de cargar los roles, intentelo nuevamente", "Error al cargar");
        }
    }

    private boolean validarCampos(Colaborador colaborador) {
        return true;
    }

    private void guardarDatos(Colaborador colaborador) {
        Mensaje mjs = ColaboradorDAO.agregarColaborador(colaborador);
        if (!mjs.isError()) {
            Utilidades.AletaSimple(Alert.AlertType.INFORMATION, "El colaborador se ha registrado con exito", "Registro exitoso");
            cerrarVentana();
            observador.notificacionOperacion("Nuevo registro", colaborador.getNombre());
        } else {
            Utilidades.AletaSimple(Alert.AlertType.ERROR, mjs.getContenido(), "Error alñ guardar");
        }
    }

    private void cerrarVentana() {
        Stage base = (Stage) tfApellidoMaterno.getScene().getWindow();
        base.close();
    }

    @FXML
    private void clicGuardarColaborador(ActionEvent event) {
        // Obtener los valores de los campos
        String noPersonal = tfNoPersonal.getText();
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String rfc = tfRFC.getText();
        String correo = tfCorreo.getText();
        String telefon = tfTelefono.getText();
        String curp = tfCURP.getText();
        String password = tfPassword.getText(); // Asegúrate de tener un campo tfPassword definido
        String fechaNacimiento = dpFechaNacimiento.getValue().toString();
        Integer idRol = (cbRol.getSelectionModel().getSelectedItem() != null)
                ? cbRol.getSelectionModel().getSelectedItem().getIdRol() : null;

        // Crear el objeto Colaborador y establecer los valores
        Colaborador colaborador = new Colaborador();
        colaborador.setNumeroPersonal(noPersonal);
        colaborador.setNombre(nombre);
        colaborador.setApellidoPaterno(apellidoPaterno);
        colaborador.setApellidoMaterno(apellidoMaterno);
        colaborador.setRfc(rfc);
        colaborador.setCorreo(correo);
        colaborador.setTelefono(telefon);
        colaborador.setCurp(curp);
        colaborador.setPassword(password); // Asegúrate de manejar esto de manera segura
        colaborador.setFechaNacimiento(fechaNacimiento);
        colaborador.setIdRol(idRol);

        if (validarCampos(colaborador)) {
            guardarDatos(colaborador);
        } else {
            Utilidades.AletaSimple(Alert.AlertType.WARNING, "Error en la validacion de los datos, porfavor ingrese nuevamente la informacion", "ERROR");
        }
    }

}
