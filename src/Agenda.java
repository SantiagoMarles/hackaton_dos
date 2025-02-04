import javax.swing.*;  // Importa las clases necesarias para la interfaz gráfica.
import java.util.ArrayList;  // Para usar la clase ArrayList.
import java.util.Iterator;  // Para usar iteradores en la lista de contactos.
import java.util.List;  // Para utilizar la interfaz List.
import java.util.Scanner;  // Para leer entradas de texto desde la consola.

public class Agenda {

    private Integer tamano;  // Tamaño máximo de la agenda.
    private final List<Contacto> listaDeContactos;  // Lista que contiene los contactos almacenados.
    private final Scanner scanner;  // Para leer datos desde la consola.

    // Constructor que recibe una lista de contactos, el tamaño de la agenda y un scanner.
    public Agenda(List<Contacto> listaDeContactos, Integer tamano, Scanner scanner) {
        this.listaDeContactos = listaDeContactos;
        this.tamano = tamano;
        this.scanner = scanner;
    }

    // Constructor por defecto que inicializa una lista vacía de contactos y un tamaño predeterminado.
    public Agenda() {
        this.listaDeContactos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.tamano = 10;
    }

    // Métodos getters y setters
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

    // Método principal para iniciar la agenda y mostrar el menú interactivo.
    public void iniciar() {
        int opcion;  // Opción seleccionada por el usuario.
        do {
            // Menú de opciones
            String menu = "\nSomos tu agenda de confianza ¿Qué deseas hacer hoy?\n" +
                    "1. Añadir un contacto.\n" +
                    "2. Verificar si un contacto existe en tu agenda.\n" +
                    "3. Ver la lista de tus contactos.\n" +
                    "4. Buscar un contacto.\n" +
                    "5. Eliminar un contacto.\n" +
                    "6. Modificar un contacto.\n" +
                    "7. Verificar si la agenda está llena.\n" +
                    "8. Verifica cuántos espacios tienes.\n" +
                    "9. Salir.\n" +
                    "Envía el número de la opción:";
            String opcionStr = JOptionPane.showInputDialog(menu);
            if (opcionStr != null) {
                opcion = Integer.parseInt(opcionStr);
            } else {
                opcion = 9; // En caso de que el usuario cierre la ventana
            }

            // Lógica de selección de la opción
            switch (opcion) {
                case 1 -> {  // Añadir un nuevo contacto
                    String nombre = JOptionPane.showInputDialog("Introduce el nombre del contacto: ");
                    String apellido = JOptionPane.showInputDialog("Introduce el apellido del contacto: ");
                    String telefono = JOptionPane.showInputDialog("Introduce el número de teléfono: ");

                    Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
                    anadirContacto(nuevoContacto);
                }
                case 2 -> {  // Verificar si un contacto existe
                    String nombre = JOptionPane.showInputDialog("Introduce el nombre del contacto: ");
                    String apellido = JOptionPane.showInputDialog("Introduce el apellido del contacto: ");

                    Contacto contactoABuscar = new Contacto(nombre, apellido, "");

                    if (existeContacto(contactoABuscar)) {
                        JOptionPane.showMessageDialog(null, "El contacto " + nombre + " " + apellido + " existe en la agenda.");
                    } else {
                        JOptionPane.showMessageDialog(null, "El contacto " + nombre + " " + apellido + " no se encuentra en la agenda.");
                    }
                }
                case 3 -> listarContactos();  // Mostrar lista de contactos
                case 4 -> {  // Buscar un contacto específico
                    String nombre = JOptionPane.showInputDialog("Introduce el nombre del contacto que deseas buscar: ");
                    String apellido = JOptionPane.showInputDialog("Introduce su apellido: ");
                    buscarContacto(nombre, apellido);
                }
                case 5 -> {  // Eliminar un contacto
                    String nombre = JOptionPane.showInputDialog("Introduce el nombre del contacto a eliminar: ");
                    String apellido = JOptionPane.showInputDialog("Introduce el apellido del contacto a eliminar: ");

                    Contacto contactoAEliminar = new Contacto(nombre, apellido, "");

                    if (listaDeContactos.contains(contactoAEliminar)) {
                        eliminarContacto(contactoAEliminar);
                    } else {
                        JOptionPane.showMessageDialog(null, "El contacto " + nombre + " " + apellido + " no se encuentra en la agenda.");
                    }
                }
                case 6 -> {  // Modificar un contacto
                    String nombre = JOptionPane.showInputDialog("Introduce el nombre del contacto a modificar: ");
                    String apellido = JOptionPane.showInputDialog("Introduce el apellido del contacto a modificar: ");
                    String nuevoTelefono = JOptionPane.showInputDialog("Introduce el nuevo número de teléfono: ");
                    modificarTelefono(nombre, apellido, nuevoTelefono);
                }
                case 7 -> agendaLlena();  // Verificar si la agenda está llena
                case 8 -> espaciosLibres();  // Verificar cuántos espacios están disponibles
                case 9 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema. ¡Vuelve pronto!");  // Salir
                default -> JOptionPane.showMessageDialog(null, "Opción inválida, intenta de nuevo.");  // Opción inválida
            }
        } while (opcion != 9);  // Repetir hasta que el usuario decida salir
    }

    // Método para añadir un contacto a la agenda
    public void anadirContacto(Contacto contacto) {
        if (listaDeContactos.size() >= tamano) {
            System.out.println("La agenda está llena. No se puede añadir más contactos.");
            return;
        }

        // Verifica que el nombre y apellido no estén vacíos
        if (contacto.getNombre().isEmpty() || contacto.getApellido().isEmpty()) {
            System.out.println("\nEl nombre y el apellido no pueden estar vacíos.");
            return;
        }

        // Verifica que el contacto no exista ya en la agenda
        if (existeContacto(contacto)) {
            System.out.println("\nEl contacto ya existe en la agenda.");
            return;
        }

        // Añadir el contacto a la lista
        listaDeContactos.add(contacto);
        System.out.println("\nContacto añadido correctamente.");
    }

    // Verifica si un contacto existe en la lista
    public boolean existeContacto(Contacto contacto) {
        for (Contacto c : listaDeContactos) {
            if (c.getNombre().equalsIgnoreCase(contacto.getNombre()) &&
                    c.getApellido().equalsIgnoreCase(contacto.getApellido())) {
                return true;
            }
        }
        return false;
    }

    // Muestra la lista de contactos
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

    // Busca un contacto por nombre y apellido
    public void buscarContacto(String nombre, String apellido) {
        for (Contacto contacto : listaDeContactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre) && contacto.getApellido().equalsIgnoreCase(apellido)) {
                System.out.println("Contacto encontrado: " + contacto);
                return;
            }
        }
        System.out.println("Contacto no encontrado.");
    }

    // Elimina un contacto de la lista
    public void eliminarContacto(Contacto contacto) {
        boolean encontrado = false;
        Iterator<Contacto> iter = listaDeContactos.iterator();
        while (iter.hasNext()) {
            Contacto c = iter.next();
            if (c.getNombre().equalsIgnoreCase(contacto.getNombre()) &&
                    c.getApellido().equalsIgnoreCase(contacto.getApellido())) {
                iter.remove();  // Eliminar el contacto de la lista
                encontrado = true;
                System.out.println("Contacto eliminado con éxito.");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("El contacto no se encuentra en la agenda.");
        }
    }

    // Modifica el número de teléfono de un contacto
    private void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        Contacto contactoABuscar = new Contacto(nombre, apellido, "");

        // Buscar el contacto en la lista
        for (int i = 0; i < listaDeContactos.size(); i++) {
            Contacto contacto = listaDeContactos.get(i);
            if (contacto.equals(contactoABuscar)) {
                // Modificar el teléfono del contacto
                contacto.setTelefono(nuevoTelefono);
                JOptionPane.showMessageDialog(null, "Número de teléfono actualizado para: " + nombre + " " + apellido);
                return;
            }
        }

        // Si no se encuentra el contacto
        JOptionPane.showMessageDialog(null, "El contacto no fue encontrado.");
    }

    // Verifica si la agenda está llena
    public void agendaLlena() {
        if (listaDeContactos.size() == getTamano()) {
            System.out.println("La agenda está llena.");
        } else {
            System.out.println("La agenda aún tiene " + (getTamano() - listaDeContactos.size()) + " espacios disponibles");
        }
    }

    // Muestra cuántos espacios libres quedan en la agenda
    public void espaciosLibres() {
        if (getTamano() > listaDeContactos.size()) {
            System.out.println("La agenda aún tiene " + (getTamano() - listaDeContactos.size()) + " espacios disponibles");
        } else {
            System.out.println("La agenda está llena.");
        }
    }
}
