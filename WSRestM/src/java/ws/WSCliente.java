package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.ClienteDAO;
import modelo.pojo.Cliente;
import modelo.pojo.RespuestaCliente;

@Path("cliente")
public class WSCliente {

    @Path("/{correo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaCliente loginColaborador(@PathParam("correo") String correo) {
        RespuestaCliente clienteRest = ClienteDAO.ClientePorCorreo(correo);
        if (clienteRest.getCliente() == null) {
            return new RespuestaCliente(true, "Cliente no encontrado", null);
        }
        return clienteRest;
    }

    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> loginColaborador() {
        return ClienteDAO.listaClientes();
    }

}
