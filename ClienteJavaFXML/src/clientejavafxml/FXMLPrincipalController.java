/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientejavafxml;

import clientejavafxml.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    private Button BTcerrarSesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        Utilidades.AletaSimple(Alert.AlertType.CONFIRMATION, "Esperamos regreses pronto", "Adios u.u0236598/");
        try {
            Stage escenarioBase = (Stage) BTcerrarSesion.getScene().getWindow();

            Parent principal = FXMLLoader.load(getClass().getResource("FXMLInisioSesion.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Inisio de sesion");
        } catch (IOException ex) {
            Utilidades.AletaSimple(Alert.AlertType.ERROR, ex + "/n error bro al cambiar", "Error al cambiar de pantalla");
        }
    }

    @FXML
    private void irAdminColaraborador(ActionEvent event) {
        try {
            Stage escenarioAdministrador = new Stage();
            Parent administrador = FXMLLoader.load(getClass().getResource("FXMLAdminColaborador.fxml"));
            Scene scene = new Scene(administrador);
            escenarioAdministrador.setScene(scene);
            escenarioAdministrador.setTitle("Administrador de colaboradores");
            escenarioAdministrador.initModality(Modality.APPLICATION_MODAL);
            escenarioAdministrador.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
            Utilidades.AletaSimple(Alert.AlertType.ERROR, "Lo sentimos ocurrio un error inesperado, intentalo nuevamente", "Error");
        }
    }
}
