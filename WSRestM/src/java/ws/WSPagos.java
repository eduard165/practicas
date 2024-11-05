
package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.PagosDAO;
import modelo.pojo.Pago;

@Path("pagos") 

public class WSPagos {
     @Path("/obtener-roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pago> loginColaborador() {
        return PagosDAO.obtenerPagos();
    }
    
      @Path("/obtener-roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
      public List<Pago> obtenerPagosCliente(@PathParam("idCliente") Integer idCliente){
          return PagosDAO.obtenerPagos(idCliente);
      }
}
