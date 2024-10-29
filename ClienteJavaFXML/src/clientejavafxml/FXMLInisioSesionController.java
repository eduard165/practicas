/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientejavafxml;

import clientejavafxml.utilidades.Utilidades;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.LoginDAO;
import modelo.pojo.Login;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class FXMLInisioSesionController implements Initializable {

    @FXML
    private TextField tfUser;
    @FXML
    private Label lbErrorPassword;
    @FXML
    private Label lbErrorEmail;
    @FXML
    private PasswordField pfPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void InisiarSesion(ActionEvent event) {
        String correo = tfUser.getText();
        String password = pfPassword.getText();
        if(validarCampos(correo, password)){
            verificarCredencialesSistema(correo, password);
            
        }
    }


    private boolean validarCampos(String correo, String password) {
        boolean camposValidos = true;
        lbErrorPassword.setText("");
        lbErrorEmail.setText("");
        if (correo.isEmpty()) {
            lbErrorEmail.setText("No es un correo valido");
            camposValidos = false;
        }
        if (password.isEmpty()) {
            lbErrorPassword.setText("Contraseña no valida");
            camposValidos = false;

        }
        return camposValidos;
    }

    private void irPantallaPrincipal() {

        try {
            Stage escenarioBase = (Stage) lbErrorPassword.getScene().getWindow();
            
            Parent principal = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("BIENVENIDOS A SHIBU");
        } catch (IOException ex) {
            Utilidades.AletaSimple(Alert.AlertType.ERROR, ex + "/n error bro al cambiar", "Error al cambiar de pantalla");
        }

    }
   
    
    private void verificarCredencialesSistema(String numeroPersonal, String password){
        Login respuestaLogin = LoginDAO.iniciarSesion(numeroPersonal, password);
        if(respuestaLogin.isError() == false){
            Utilidades.AletaSimple(Alert.AlertType.INFORMATION,"Bienvenido(a)", "Bienvenido(a) al sistema de GymForte");
                   irPantallaPrincipal();         
        }else{
            Utilidades.AletaSimple(Alert.AlertType.ERROR, respuestaLogin.getContenido(), "Error");
        }
        //Temporal
    }
    
   
    
}

