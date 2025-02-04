import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Agenda {

    private Integer tamano;
    private final List<Contacto> listaDeContactos;
    private final Scanner scanner;

    public Agenda(List<Contacto> listaDeContactos, Integer tamano, Scanner scanner) {
        this.listaDeContactos = listaDeContactos;
        this.tamano = tamano;
        this.scanner = scanner;
    }

    public Agenda() {
        this.listaDeContactos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.tamano = 10;
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

    public Scanner getScanner() {
        return scanner;
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\nSomos tu agenda de confianza ¿Qué deseas hacer hoy?");
            System.out.println("1. Añadir un contacto.");
            System.out.println("2. Verificar si un contacto existe en tu agenda.");
            System.out.println("3. Ver la lista de tus contactos.");
            System.out.println("4. 🔍Buscar contacto.");
            System.out.println("5. Eliminar un contacto.");
            System.out.println("6. Modificar un contacto.");
            System.out.println("7. Verificar si la agenda está llena.");
            System.out.println("8. Verifica cuántos espacios tienes.");
            System.out.println("9. Salir 😎.");
            System.out.print("Envía el número de la opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Introduce el nombre del contacto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Introduce el apellido del contacto: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Introduce el número de teléfono: ");
                    String telefono = scanner.nextLine();

                    Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
                    anadirContacto(nuevoContacto);
                }
                case 2 -> {
                    System.out.print("Introduce el nombre del contacto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Introduce el apellido del contacto: ");
                    String apellido = scanner.nextLine();

                    // Creamos un contacto temporal para verificar su existencia
                    Contacto contactoABuscar = new Contacto(nombre, apellido, "");

                    if (existeContacto(contactoABuscar)) {
                        System.out.println("El contacto " + nombre + " " + apellido + " existe en la agenda.");
                    } else {
                        System.out.println("El contacto " + nombre + " " + apellido + " no se encuentra en la agenda.");
                    }
                }
                case 3 -> listarContactos();
                case 4 -> {
                    System.out.print("Introduce el nombre del contacto que deseas buscar: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Introduce su apellido: ");
                    String apellido = scanner.nextLine();

                    buscarContacto(nombre, apellido);
                }
                case 5 -> {
                    System.out.print("Introduce el nombre del contacto a eliminar: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Introduce el apellido del contacto a eliminar: ");
                    String apellido = scanner.nextLine();

                    // Creamos un contacto temporal para buscar y eliminar
                    Contacto contactoAEliminar = new Contacto(nombre, apellido, "");

                    if (listaDeContactos.contains(contactoAEliminar)) {
                        eliminarContacto(contactoAEliminar);
                    } else {
                        System.out.println("El contacto " + nombre + " " + apellido + " no se encuentra en la agenda.");
                    }
                }
                case 6 -> {
                    System.out.print("Introduce el nombre del contacto a modificar: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Introduce el apellido del contacto a modificar: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Introduce el nuevo número de teléfono: ");
                    String nuevoTelefono = scanner.nextLine();
                    modificarTelefono(nombre, apellido, nuevoTelefono);
                }
                case 7 -> agendaLlena();
                case 8 -> espaciosLibres();
                case 9 -> System.out.println("\nSaliendo del sistema. ¡Vuelve pronto!");
                default -> System.out.println("\nOpción inválida, intenta de nuevo.");
            }
        } while (opcion != 9);
    }

    public void anadirContacto(Contacto contacto) {
        if (listaDeContactos.size() >= tamano) {
            System.out.println("La agenda está llena. No se puede añadir más contactos.");
            return;
        }

        if (contacto.getNombre().isEmpty() || contacto.getApellido().isEmpty()) {
            System.out.println("\nEl nombre y el apellido no pueden estar vacíos.");
            return;
        }

        if (existeContacto(contacto)) {
            System.out.println("\nEl contacto ya existe en la agenda.");
            return;
        }

        listaDeContactos.add(contacto);
        System.out.println("\nContacto añadido correctamente.");
    }

    public boolean existeContacto(Contacto contacto) {
        for (Contacto c : listaDeContactos) {
            if (c.getNombre().equalsIgnoreCase(contacto.getNombre()) &&
                    c.getApellido().equalsIgnoreCase(contacto.getApellido())) {
                return true;
            }
        }
        return false;
    }

    public void listarContactos() {
        if (listaDeContactos.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }
        System.out.println("Lista de Contactos:");
        for (Contacto contacto : listaDeContactos) {
            System.out.println(contacto);
        }
    }

    public void buscarContacto(String nombre, String apellido) {
        for (Contacto contacto : listaDeContactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre) && contacto.getApellido().equalsIgnoreCase(apellido)) {
                System.out.println("Contacto encontrado: " + contacto);
                return;
            }
        }
        System.out.println("Contacto no encontrado.");
    }

    public void eliminarContacto(Contacto contacto) {
        boolean encontrado = false;
        Iterator<Contacto> iter = listaDeContactos.iterator();
        while (iter.hasNext()) {
            Contacto c = iter.next();
            if (c.getNombre().equalsIgnoreCase(contacto.getNombre()) &&
                    c.getApellido().equalsIgnoreCase(contacto.getApellido())) {
                iter.remove();
                encontrado = true;
                System.out.println("Contacto eliminado con éxito.");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("El contacto no se encuentra en la agenda.");
        }
    }


    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        for (Contacto contacto : listaDeContactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre) && contacto.getApellido().equalsIgnoreCase(apellido)) {
                contacto.setTelefono(nuevoTelefono);
                System.out.println("Teléfono modificado con éxito.");
                return;
            }
        }
        System.out.println("No se encontró el contacto con ese nombre y apellido.");
    }

    public void agendaLlena() {
        if (listaDeContactos.size() == getTamano()) {
            System.out.println("La agenda está llena.");
        } else {
            System.out.println("La agenda aún tiene " + (getTamano() - listaDeContactos.size()) + " espacios disponibles");
        }
    }

    public void espaciosLibres() {
        if (getTamano() > listaDeContactos.size()) {
            System.out.println("La agenda aún tiene " + (getTamano() - listaDeContactos.size()) + " espacios disponibles");
        } else {
            System.out.println("La agenda está llena.");
        }
    }
}
