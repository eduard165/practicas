package ws;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.WSCliente.class);
        resources.add(ws.WSColaboradores.class);
        resources.add(ws.WSLogin.class);
        resources.add(ws.WSPagos.class);
        resources.add(ws.WSRol.class);
        resources.add(ws.WSRutina.class);
    }
    
}
