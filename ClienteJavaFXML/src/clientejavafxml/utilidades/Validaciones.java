/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientejavafxml.utilidades;

/**
 *
 * @author eduar
 */
public class Validaciones {
     // Verificación de usuario y contraseña
    public static boolean validateCredentials(String user, String password, StringBuilder errorMessage) {
        // Validación del usuario (debe ser un email válido)
        if (user.isEmpty()) {
            errorMessage.append("El campo de usuario no puede estar vacío.\n");
            return false;
        }
        if (!user.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) { // Valida formato de email básico
            errorMessage.append("Formato de correo no válido.\n");
            return false;
        }

        // Validación de la contraseña (no debe tener caracteres especiales)
        if (password.isEmpty()) {
            errorMessage.append("El campo de contraseña no puede estar vacío.\n");
            return false;
        }
        if (!password.matches("^[A-Za-z0-9]*$")) { // Solo permite letras y números
            errorMessage.append("La contraseña no debe contener caracteres especiales.\n");
            return false;
        }

        return true; // Si ambas validaciones pasan
    }
}
