/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import static java.util.Collections.list;
import modelo.pojo.Pago;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;


/**
 *
 * @author eduar
 */
public class PagosDAO {
    
    public static List<Pago> obtenerPagos(){
        List<Pago> pagos = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                pagos = conexionBD.selectList("pagos.obtenerTodos");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return pagos;
    }
       
    public static List<Pago> obtenerPagos(Integer idCliente){
       List<Pago> pagos = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                pagos = conexionBD.selectList("pagos.obenerPagoPorCliente", idCliente);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return pagos;
    }
}
