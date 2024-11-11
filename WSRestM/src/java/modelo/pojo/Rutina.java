
package modelo.pojo;

public class Rutina {

    private Integer idRuina;
    private String nombre;
    private Integer numeroEjercicios;
    private Integer repeticiones;
    private String nombreAparato;
    private String nombreCliente;
    private String apellidoCliente;
    private String nombreColaborador;
    private String apellidoColaborador;

    public Rutina() {
    }

    public Rutina(Integer idRuina, String nombre, Integer numeroEjercicios, Integer repeticiones, String nombreAparato, String nombreCliente, String apellidoCliente, String nombreColaborador, String apellidoColaborador) {
        this.idRuina = idRuina;
        this.nombre = nombre;
        this.numeroEjercicios = numeroEjercicios;
        this.repeticiones = repeticiones;
        this.nombreAparato = nombreAparato;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.nombreColaborador = nombreColaborador;
        this.apellidoColaborador = apellidoColaborador;
    }

    public Integer getIdRuina() {
        return idRuina;
    }

    public void setIdRuina(Integer idRuina) {
        this.idRuina = idRuina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroEjercicios() {
        return numeroEjercicios;
    }

    public void setNumeroEjercicios(Integer numeroEjercicios) {
        this.numeroEjercicios = numeroEjercicios;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getNombreAparato() {
        return nombreAparato;
    }

    public void setNombreAparato(String nombreAparato) {
        this.nombreAparato = nombreAparato;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getNombreColaborador() {
        return nombreColaborador;
    }

    public void setNombreColaborador(String nombreColaborador) {
        this.nombreColaborador = nombreColaborador;
    }

    public String getApellidoColaborador() {
        return apellidoColaborador;
    }

    public void setApellidoColaborador(String apellidoColaborador) {
        this.apellidoColaborador = apellidoColaborador;
    }

}
