package ws;

import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.ColaboradorDAO;
import modelo.pojo.Colaborador;
import modelo.pojo.Mensaje;
import modelo.pojo.RespuestaColaborador;


@Path("colaboradores") 
public class WSColaboradores {

    @Path("/obtener-colaboradores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> loginColaborador() {
        return ColaboradorDAO.listaColaboradores();
    }

    @Path("/{numeroPersonal}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaColaborador colaboradorPorNumeroPersonal(
            @PathParam("numeroPersonal") String numeroPersonal) {
        return ColaboradorDAO.obtenerColaborador(numeroPersonal);
    }

    @Path("/agregar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarColaborador(String json) {
        Gson gson = new Gson();
        Colaborador colaborador = gson.fromJson(json, Colaborador.class);
        if (colaborador == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return ColaboradorDAO.agregarColaborador(colaborador);
    }
    
   @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarColaborador(String json) {
        Gson gson = new Gson();
        Colaborador colaborador = gson.fromJson(json, Colaborador.class);
        if (colaborador == null || colaborador.getNumeroPersonal() == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return ColaboradorDAO.editarColaborador(colaborador);
    }

    @Path("/eliminar/{idColaborador}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(@PathParam("idColaborador") Integer idColaborador) {
        if (idColaborador == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return ColaboradorDAO.eliminarColaborador(idColaborador);
    }

}
