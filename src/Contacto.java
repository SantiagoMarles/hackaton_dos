public class Contacto {

private String nombre;
private String apellido;
private String telefono;
//private Integer id;

    public Contacto() {
    }

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
