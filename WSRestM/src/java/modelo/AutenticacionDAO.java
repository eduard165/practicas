package modelo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import modelo.pojo.Cliente;
import modelo.pojo.Colaborador;
import modelo.pojo.RespuestaCliente;
import modelo.pojo.RespuestaColaborador;

public class AutenticacionDAO {

    public static RespuestaCliente verificarSesionCliente(String correo, String password) {
        RespuestaCliente respuesta = new RespuestaCliente();
        respuesta.setError(true);

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("correo", correo);
                parametros.put("password", password);

                System.out.println(parametros.get(correo));
                System.out.println(parametros.get(password));

                Cliente clienteRest = conexionBD.selectOne("autenticacion.verificarCredencialesClientes", parametros);

                if (clienteRest != null) {
                    respuesta.setError(false);
                    respuesta.setContenido("Bienvenid(@) " + clienteRest.getNombre() + " al sistema.");
                    respuesta.setCliente(clienteRest);
                } else {
                    respuesta.setContenido("Correo y/o contraseña incorrectos, favor de verificar.");
                }
            } catch (Exception e) {
                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }

        } else {
            respuesta.setContenido("Error: Por el momento no se puede acceder a la informacion.");
        }

        return respuesta;
    }

    public static RespuestaColaborador verificarSesionColaborador(Colaborador colaborador) {
        RespuestaColaborador respuesta = new RespuestaColaborador();
        respuesta.setError(true);

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {

                // Verifica que estés usando "verificarCredencialesColaborador"
                Colaborador colaboradorRest = conexionBD.selectOne("autenticacion.verificarCredencialesColaborador", colaborador);

                if (colaboradorRest != null) {

                    respuesta.setError(false);
                    respuesta.setContenido("Bienvenid(@) " + colaboradorRest.getNombre() + " al sistema.");
                    respuesta.setColaborador(colaboradorRest);
                } else {
                    respuesta.setContenido("Correo y/o contraseña incorrectos, favor de verificar.");
                }
            } catch (Exception e) {

                respuesta.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Error: Por el momento no se puede acceder a la informacion.");
        }

        return respuesta;
    }

    public static HashMap<String, Object> verificarSesionColaboradorConHasMap(String numeroPersonal, String password) {
        HashMap<String, Object> respuesta = new HashMap<>();

        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("numeroPersonal", numeroPersonal);
                parametros.put("password", password);

                Colaborador colaborador = conexionDB.selectOne("autenticacion.verificarCredencialesColaboradorConHashMap", parametros);

                if (colaborador != null) {
                    respuesta.put("error", false);
                    respuesta.put("mensaje", "Bienvenid(@) " + colaborador.getNombre() + " al sistema.");
                    respuesta.put("Colaborador", colaborador);
                } else {
                    respuesta.put("error", true);
                    respuesta.put("mensaje", "Correo y/o contraseña incorrectos, favor de verificar.");
                }
            } catch (Exception e) {
                respuesta.put("error", true);
                respuesta.put("mensaje", e.getMessage());
            } finally {
                conexionDB.close();
            }
        } else {
            respuesta.put("error", true);
            respuesta.put("mensaje", "Error: Por el momento no se puede acceder a la informacion.");
        }
        return respuesta;
    }

}
