package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;
import modelo.pojo.RespuestaCliente;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class ClienteDAO {

    public static RespuestaCliente ClientePorCorreo(String correo) {
        RespuestaCliente msj = null;
        SqlSession sqlSession = MyBatisUtil.getSession();

        if (sqlSession != null) {
            try {
                Cliente clienteRes = sqlSession.selectOne("cliente.obtenerClientePorCorreo", correo);

                if (clienteRes != null) {
                    msj = new RespuestaCliente(false, "Hola  " + clienteRes.getNombre(), clienteRes);
                } else {
                    msj = new RespuestaCliente(true, "No se encontro cliente con ese correo, verifica e intenta nuvamente", null);
                }
            } catch (Exception e) {
                msj = new RespuestaCliente(true, "No se encontro cliente con ese correo, verifica e intenta nuvamente", null);
            } finally {
                sqlSession.close();
            }
        }
        return msj;
    }

    public static List<Cliente> listaClientes() {
        List<Cliente> Clientes = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                Clientes = conexionBD.selectList("cliente.obtenerClientes");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return Clientes;
    }

    public static Mensaje subirFoto(Integer idCliente, byte[] foto) {
        Mensaje msj = new Mensaje();
        msj.setError(true);
        LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
        parametros.put("idCliente", idCliente);
        parametros.put("foto", foto);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("cliente.guardarFoto", parametros);
                conexionBD.commit();
                if (filasAfectadas > 0) {
                    msj.setError(false);
                    msj.setContenido("Foto del cliente actualizada con exito");
                } else {
                    msj.setContenido("La foto del cliente no pudo ser guardad cone exito");
                }
            } catch (Exception e) {
                msj.setContenido(e.getMessage());
            }
        } else {
            msj.setContenido("Por el momento no hay conexion estable reinicie la aplicacion nuevamente");
        }
        return msj;
    }
    
    public static Cliente obtenerFoto(Integer idCliente){
        Cliente nCliente =null;
        SqlSession conexionBd = MyBatisUtil.getSession();
        if(conexionBd != null){
            try {
                nCliente = conexionBd.selectOne("cliente.obtenerFoto", idCliente);
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nCliente;
    }
    public static Mensaje editarCliente(Cliente cliente) {
    Mensaje msj = new Mensaje();
    msj.setError(true);
    SqlSession sqlSession = MyBatisUtil.getSession();

    if (sqlSession != null) {
        try {
            
            int filasAfectadas = sqlSession.update("cliente.editarCliente", cliente);
            sqlSession.commit();

            if (filasAfectadas > 0) {
                msj.setError(false);
                msj.setContenido("Datos del cliente actualizados con éxito.");
            } else {
                msj.setContenido("No se pudo actualizar los datos del cliente.");
            }
        } catch (Exception e) {
            msj.setContenido(e.getMessage());
        } finally {
            sqlSession.close();
        }
    } else {
        msj.setContenido("Por el momento no hay conexión estable. Reinicie la aplicación.");
    }

    return msj;
}

}
