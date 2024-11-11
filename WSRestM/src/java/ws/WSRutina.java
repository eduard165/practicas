package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.RutinaDAO;
import modelo.pojo.Rutina;

@Path("rutinas") 
public class WSRutina {

    @Path("/obtener-rutinas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rutina> obtenerRutinas() {
        return RutinaDAO.obtenerRutinas(); 
    }

    @Path("/obtener-rutinas-por-cliente/{idCliente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rutina> obtenerRutinasCliente(@PathParam("idCliente") Integer idCliente) {
        return RutinaDAO.obtenerRutinasPorCliente(idCliente); 
    }
}