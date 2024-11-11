package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.pojo.Rutina;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class RutinaDAO {

    public static List<Rutina> obtenerRutinas() {
        List<Rutina> rutinas = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                rutinas = conexionBD.selectList("rutinas.obtenerRutinas");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return rutinas;
    }

    public static List<Rutina> obtenerRutinasPorCliente(Integer idCliente) {
        List<Rutina> rutinas = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                rutinas = conexionBD.selectList("rutinas.obtenerRutinasCliente", idCliente);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return rutinas;
    }

}
