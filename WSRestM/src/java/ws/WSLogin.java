package ws;

import com.google.gson.Gson;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.AutenticacionDAO;
import modelo.pojo.Colaborador;
import modelo.pojo.RespuestaColaborador;


@Path("login") 
public class WSLogin {

    @Context 
    private UriInfo context;

    public WSLogin() {

    }

    @Path("colaborador")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaColaborador loginColaborador(Colaborador colaborador) {
        return AutenticacionDAO.verificarSesionColaborador(colaborador);
    }


    @Path("colaboradorHashMap")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response loginColaboradorConHashMap(@FormParam("numeroPersonal") String numeroPersonal, @FormParam("password") String password) {
        if ((numeroPersonal == null &&  password == null) && numeroPersonal.isEmpty() && password.isEmpty()) {
            throw new BadRequestException("Credenciales no válidas"); 
        }
        
        Gson gson = new Gson();
        String jsonResultado = gson.toJson(AutenticacionDAO.verificarSesionColaboradorConHasMap(numeroPersonal, password));

        return Response.ok(jsonResultado, MediaType.APPLICATION_JSON).build();
    }
}
