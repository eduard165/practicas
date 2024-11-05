/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import clientejavafxml.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionHTTP;
import modelo.pojo.Colaborador;
import modelo.pojo.Mensaje;
import modelo.pojo.RespuestaHTTP;

/**
 *
 * @author eduar
 */
public class ColaboradorDAO {

    public static List<Colaborador> obtenerColaboradores() {
        List<Colaborador> colaboradores = null;
        String url = Constantes.URL_WS + "colaboradores/obtener-colaboradores";
        RespuestaHTTP respuesta = ConexionHTTP.peticionGET(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>() {
                }.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return colaboradores;
    }

    public static Mensaje agregarColaborador(Colaborador colaborador) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "colaboradores/agregar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(colaborador);
            RespuestaHTTP respuesta = ConexionHTTP.peticionPOSTJson(url, parametros);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                mensaje.setError(true);
                mensaje.setContenido(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje.setError(true);
            mensaje.setContenido(e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje editarColaborador(Colaborador colaborador) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "colaboradores/editar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(colaborador);
            RespuestaHTTP respuesta = ConexionHTTP.peticionPUTJSON(url, parametros);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                mensaje.setError(true);
                mensaje.setContenido(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje.setError(true);
            mensaje.setContenido(e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje eliminarColaborador(int idColaborador) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "colaboradores/eliminar/" + idColaborador;
        Gson gson = new Gson();
        try {
            RespuestaHTTP respuesta = ConexionHTTP.peticionDELETE(url);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                mensaje.setError(true);
                mensaje.setContenido(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje.setError(true);
            mensaje.setContenido(e.getMessage());
        }

        return mensaje;
    }
}
