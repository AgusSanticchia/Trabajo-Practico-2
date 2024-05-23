package ar.edu.utn.frbb.tup;

import ar.edu.utn.frbb.tup.utils.Cliente;
import java.util.Scanner;
import java.util.Set;
import java.time.LocalDateTime;

public class MenuInputProcessor extends BaseInputProcessor {
    ClienteInputProcessor clienteInputProcessor = new ClienteInputProcessor();
    boolean exit = false;

    public void renderMenu(Banco banco) {

        while (!exit) {
            System.out.println("Bienvenido a la aplicación del Banco!");
            System.out.println("1. Crear un nuevo Cliente");
            System.out.println("2. Crear una nueva Cuenta");
            System.out.println("3. Generar un Movimiento");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción (1-4): ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
                continue; // Volver al inicio del bucle
            }

            switch (choice) {
                case 1:
                    Cliente nuevoCliente = clienteInputProcessor.ingresarCliente();
                    banco.agregarCliente(nuevoCliente);
                    break;

                case 2:
                    System.out.println("Creación de una nueva cuenta:");
                    System.out.print("Ingrese el nombre del cliente asociado a la cuenta: ");
                    String nombreCliente = scanner.nextLine();
                    Cliente clienteCuenta = banco.buscarClientePorNombre(nombreCliente);
                    if (clienteCuenta != null) {
                        Cuenta nuevaCuenta = new Cuenta().setNombre(nombreCliente).setFechaCreacion(LocalDateTime.now());
                        clienteCuenta.addCuenta(nuevaCuenta);
                        System.out.println("Nueva cuenta creada con éxito para el cliente: " + nombreCliente);
                    } else {
                        System.out.println("Cliente no encontrado. No se pudo crear la cuenta.");
                    }
                    break;

                case 3:

                    System.out.println("Generación de un movimiento:");
                    System.out.print("Ingrese el nombre del cliente asociado al movimiento: ");
                    String nombreClienteMovimiento = scanner.nextLine();
                    
                    // Buscar el cliente en el banco
                    Cliente clienteMovimiento = banco.buscarClientePorNombre(nombreClienteMovimiento);
                    if (clienteMovimiento != null) {
                        // Mostrar las cuentas del cliente para que el usuario elija 
                        System.out.println("Cuentas asociadas al cliente " + nombreClienteMovimiento + ":");
                        Set<Cuenta> cuentasCliente = clienteMovimiento.getCuentas();
                        int index = 1;
                        for (Cuenta cuenta : cuentasCliente) {
                            System.out.println(index + ". " + cuenta.getNombre());
                            index++;
                        }
                        System.out.print("Seleccione el número de la cuenta para el movimiento: ");
                        int cuentaIndex = scanner.nextInt();
                        scanner.nextLine(); 
                        
                        // Verificar si el número de cuenta seleccionado es válido
                        if (cuentaIndex >= 1 && cuentaIndex <= cuentasCliente.size()) {
                            // Obtener la cuenta seleccionada
                            Cuenta cuentaSeleccionada = (Cuenta) cuentasCliente.toArray()[cuentaIndex - 1];
                            
                            System.out.print("Ingrese el monto del movimiento: ");
                            int montoMovimiento = scanner.nextInt();
                            scanner.nextLine();


                        // Supongamos que el monto positivo representa un depósito y el monto negativo representa un retiro
                            if (montoMovimiento > 0) { // Mayor a 0 ya que es positivo
                                cuentaSeleccionada.depositar(montoMovimiento);
                                System.out.println("Depósito de " + montoMovimiento + " realizado en la cuenta " + cuentaSeleccionada.getNombre());
                            } else if (montoMovimiento < 0) { // Menor a 0 ya que es negativo
                                if (cuentaSeleccionada.getBalance() >= Math.abs(montoMovimiento)) {
                                    cuentaSeleccionada.retirar(Math.abs(montoMovimiento));
                                    System.out.println("Retiro de " + Math.abs(montoMovimiento) + " realizado en la cuenta " + cuentaSeleccionada.getNombre());
                                } else {
                                    System.out.println("Saldo insuficiente en la cuenta " + cuentaSeleccionada.getNombre() + " para realizar el retiro.");
                                }
                            } else {
                                System.out.println("El monto del movimiento debe ser distinto de cero.");
                            }
                        } else {
                            System.out.println("Número de cuenta inválido. No se pudo realizar el movimiento.");
                        }
                    } else {
                        System.out.println("Cliente no encontrado. No se pudo realizar el movimiento.");
                    }
                    break;   

                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor seleccione 1-4.");

            }
            clearScreen();
        }
    }
}

