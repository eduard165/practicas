package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.pojo.Colaborador;
import modelo.pojo.Mensaje;
import modelo.pojo.RespuestaColaborador;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class ColaboradorDAO {

    public static List<Colaborador> listaColaboradores() {
        List<Colaborador> colaboradores = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                colaboradores = conexionBD.selectList("colaborador.obenerColaboradores");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return colaboradores;
    }

   

    public static RespuestaColaborador obtenerColaborador(String numeroPersonal) {
        SqlSession sqlSession = MyBatisUtil.getSession();
        Colaborador colaborador = null;
        RespuestaColaborador respuesta;

        if (sqlSession != null) {
            try {
                colaborador = sqlSession.selectOne("colaborador.obtenerColaboradorPorNumeroPersonal", numeroPersonal);

                if (colaborador == null) {
                    respuesta = new RespuestaColaborador(true, "Colaborador no encontrado", null);
                } else {
                    respuesta = new RespuestaColaborador(false, "Hola " + colaborador.getNombre(), colaborador);
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta = new RespuestaColaborador(true, "Error al obtener el colaborador", null);
            } finally {
                sqlSession.close();
            }
        } else {
            respuesta = new RespuestaColaborador(true, "Error al conectar con la base de datos", null);
        }

        return respuesta;
    }
 public static Mensaje agregarColaborador(Colaborador colaborador) {
        SqlSession sqlSession = MyBatisUtil.getSession();
        Mensaje respuesta;

        if (sqlSession != null) {
            try {
                int filasModificadas = sqlSession.insert("colaborador.agregarColaborador", colaborador);

                if (filasModificadas > 0) {
                    sqlSession.commit();
                    respuesta = new Mensaje(false, "Registrado con éxito");
                } else {
                    respuesta = new Mensaje(true, "Error al registrar el colaborador");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta = new Mensaje(true, "Error al registrar el colaborador: " + e.getMessage());
            } finally {
                sqlSession.close();
            }
        } else {
            respuesta = new Mensaje(true, "Error al conectar con la base de datos");
        }

        return respuesta;
    }
  public static Mensaje editarColaborador(Colaborador colaborador) {
    SqlSession sqlSession = MyBatisUtil.getSession();
    Mensaje respuesta;

    if (sqlSession != null) {
        try {
            int filasModificadas = sqlSession.update("colaborador.editarColaborador", colaborador);

            if (filasModificadas > 0) {
                sqlSession.commit();
                respuesta = new Mensaje(false, "Editado con éxito");
            } else {
                respuesta = new Mensaje(true, "Error al editar el colaborador");
            }
        } catch (Exception e) {
            e.printStackTrace();
            respuesta = new Mensaje(true, "Error al editar el colaborador: " + e.getMessage());
        } finally {
            sqlSession.close();
        }
    } else {
        respuesta = new Mensaje(true, "Error al conectar con la base de datos");
    }

    return respuesta;
}

public static Mensaje eliminarColaborador(Integer idColaborador) {
    SqlSession sqlSession = MyBatisUtil.getSession();
    Mensaje respuesta;

    if (sqlSession != null) {
        try {
            int filasModificadas = sqlSession.delete("colaborador.eliminarColaborador", idColaborador);

            if (filasModificadas > 0) {
                sqlSession.commit();
                respuesta = new Mensaje(false, "Eliminado con éxito");
            } else {
                respuesta = new Mensaje(true, "Error al eliminar el colaborador");
            }
        } catch (Exception e) {
            e.printStackTrace();
            respuesta = new Mensaje(true, "Error al eliminar el colaborador: " + e.getMessage());
        } finally {
            sqlSession.close();
        }
    } else {
        respuesta = new Mensaje(true, "Error al conectar con la base de datos");
    }

    return respuesta;
}


}
