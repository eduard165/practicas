package ws;

import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.ClienteDAO;
import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;
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

    @Path("/subir-foto/{idCliente}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirFoto(@PathParam("idCliente") Integer idCliente, byte[] foto) {
        if (idCliente > 0 && idCliente != null && foto != null) {
            return ClienteDAO.subirFoto(idCliente, foto);
        }
        throw new BadRequestException();
    }

    @Path("/obtener-foto/{idCliente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente obtenerFoto(@PathParam("idCliente") Integer idCliente) {
        if (idCliente > 0 && idCliente != null) {
            return ClienteDAO.obtenerFoto(idCliente);
        }
        throw new BadRequestException();
    }

    @Path("/editar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarCliente(Cliente cliente) {
        if (cliente != null && cliente.getIdCliente() != null) {
            return ClienteDAO.editarCliente(cliente);
        }
        throw new BadRequestException("Datos inválidos o faltantes para la edición.");
    }
}
