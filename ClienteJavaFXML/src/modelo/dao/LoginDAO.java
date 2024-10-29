package modelo.dao;

import clientejavafxml.utilidades.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;
import modelo.ConexionHTTP;
import modelo.pojo.Login;
import modelo.pojo.RespuestaHTTP;

public class LoginDAO {
   
    public static Login iniciarSesion(String numeroPersonal, String password){
        Login respuestaLogin = new Login();
        String urlServicio = Constantes.URL_WS+"login/colaboradorHashMap";
        String parametros = String.format("numeroPersonal=%s&password=%s", numeroPersonal, password);
        RespuestaHTTP respuestaWS = ConexionHTTP.peticionPOST(urlServicio, parametros);
        if(respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            System.out.println("JSON:" + respuestaWS.getContenido());
            Gson gson = new Gson();
            respuestaLogin = gson.fromJson(respuestaWS.getContenido(), Login.class);
        }else{
            respuestaLogin.setError(true);
            respuestaLogin.setContenido("Lo sentimos, hubo un error al procesar la autenticación,"+" por favor intentelo más tarde");
        }
        return respuestaLogin;
    
    }
}
