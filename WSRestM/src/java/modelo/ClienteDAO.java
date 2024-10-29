package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.pojo.Cliente;
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
}
