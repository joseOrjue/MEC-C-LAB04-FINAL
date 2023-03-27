import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AsignacionTurnos {
    public static void main(String[] args) {
    Queue<Paciente> pacientes = new LinkedList<>();
    Scanner sc = new Scanner(System.in);
    int turno = 1;
    int tiempoRestante = 0;

    while (true) {
        System.out.println("Ingrese los datos del paciente:");
        System.out.print("Nombre y apellidos: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());
        System.out.print("Afiliación (POS o PC): ");
        String afiliacion = sc.nextLine();
        System.out.print("Condición especial (embarazo o limitación motriz): ");
        String condicionEspecial = sc.nextLine();

        boolean esPrioritario = esPrioritario(edad, afiliacion, condicionEspecial);
        Paciente paciente = new Paciente(nombre, edad, afiliacion, condicionEspecial, esPrioritario);
        pacientes.add(paciente);
        System.out.println("Turno asignado: " + turno);
        System.out.println("Turnos pendientes: " + (pacientes.size() - 1));
        turno++;

        if (pacientes.size() == 1) {
            tiempoRestante = 5;
        }

        while (tiempoRestante > 0) {
            System.out.println("Atendiendo paciente actual...");
            System.out.println("Tiempo restante: " + tiempoRestante + " segundos");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tiempoRestante--;
        }

        if (!pacientes.isEmpty()) {
            Paciente siguientePaciente = pacientes.peek();
            System.out.println("Llamando al paciente " + siguientePaciente.getNombre() + " (turno " + (turno - pacientes.size()) + ")");
            pacientes.remove();
            tiempoRestante = 5;
        }
    }
}

public static boolean esPrioritario(int edad, String afiliacion, String condicionEspecial) {
    boolean esPrioritario = false;
    if (edad < 12 || edad >= 60 || condicionEspecial.equalsIgnoreCase("embarazo")) {
        esPrioritario = true;
    }
    if (afiliacion.equalsIgnoreCase("PC")) {
        esPrioritario = true;
    }
    return esPrioritario;
}
}

class Paciente {
private String nombre;
private int edad;
private String afiliacion;
private String condicionEspecial;
private boolean esPrioritario;
public Paciente(String nombre, int edad, String afiliacion, String condicionEspecial, boolean esPrioritario) {
    this.nombre = nombre;
    this.edad = edad;
    this.afiliacion = afiliacion;
    this.condicionEspecial = condicionEspecial;
    this.esPrioritario = esPrioritario;
}

public String getNombre() {
    return nombre;
}

public int getEdad() {
    return edad;
}

public String getAfiliacion() {
    return afiliacion;
}

public String getCondicionEspecial() {
    return condicionEspecial;
}

public boolean esPrioritario() {
    return esPrioritario;
}
}