import java.util.List;

public class Agenda{

    private Integer tamano;
    private final List<Contacto> listaDeContactos;


    public Agenda(List<Contacto> listaDeContactos) {
        this.listaDeContactos = listaDeContactos;
        this.tamano= 10;
    }

    public Agenda(List<Contacto> listaDeContactos, Integer tamano) {
        this.listaDeContactos = listaDeContactos;
        this.tamano= this.tamano;
    }

    public Integer getTamano() {
        return tamano;
    }

    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    public List<Contacto> getListaDeContactos() {
        return listaDeContactos;
    }

    //Angie
    public void añadirContacto(Contacto contacto){};

    public void existeContacto(Contacto conctacto){};


    //Javi
   public void listarContacto(){};

    public void buscarContacto(String nombre){};


//Santi
   public void eliminarContacto(Contacto contacto){};

    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono){};

    //Mariana

    public void agendaLlena(){};

   public void espaciosLibres(){};

}
