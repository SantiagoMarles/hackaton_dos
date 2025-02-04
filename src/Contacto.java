import java.util.Objects;

public class Contacto {

    private String nombre;       // Nombre del contacto
    private String apellido;     // Apellido del contacto
    private String telefono;     // Teléfono del contacto

    // Constructor que inicializa los campos de la clase Contacto
    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    // Getters y setters para acceder y modificar los atributos

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

    // Sobreescritura del metodo equals para comparar dos objetos Contacto correctamente
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Si el objeto es el mismo, retornamos true
        if (o == null || getClass() != o.getClass()) return false; // Si el objeto es null o no es del tipo correcto, retornamos false
        Contacto contacto = (Contacto) o; // Hacemos un casting del objeto a Contacto
        // Comparamos los nombres y apellidos, ignorando mayúsculas/minúsculas
        return nombre.equalsIgnoreCase(contacto.nombre) && apellido.equalsIgnoreCase(contacto.apellido);
    }

    // Sobreescritura del metodo hashCode para generar un código hash consistente con equals
    @Override
    public int hashCode() {
        // Usamos los atributos 'nombre' y 'apellido' en minúsculas para generar el hash
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }

    // Sobreescritura del metodo toString para obtener una representación en formato String del objeto Contacto
    @Override
    public String toString() {
        // Retorna una cadena con los valores de los atributos de Contacto
        return
                "nombre: " + nombre + '\n' +
                ", apellido: " + apellido + '\n' +
                ", telefono: " + telefono + '\n' ;
    }
}
