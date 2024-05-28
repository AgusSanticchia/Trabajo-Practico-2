package ar.edu.utn.frbb.tup.presentation.input;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.TipoPersona;
import ar.edu.utn.frbb.tup.model.TipoCuenta;

import java.time.LocalDate;

public class ClienteInputProcessor extends BaseInputProcessor{

    public Cliente ingresarCliente() {

        // Ingreso de datos del Cliente
        Cliente cliente = new Cliente();
        clearScreen();
        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.nextLine();
        cliente.setNombre(nombre);

        System.out.println("Ingrese el apellido del cliente:");
        String apellido = scanner.nextLine();
        cliente.setApellido(apellido);

        System.out.println("Ingrese el tipo de persona Física(F) o Jurídica(J): ");
        String tipoPersonaStr = scanner.nextLine().toUpperCase();
        while (!tipoPersonaStr.equals("F") && !tipoPersonaStr.equals("J")) {
            System.out.println("Tipo de persona inválido. Ingrese NATURAL o JURIDICA:");
            tipoPersonaStr = scanner.nextLine().toUpperCase();
        }
        TipoPersona tipoPersona = TipoPersona.fromString(tipoPersonaStr);
        cliente.setTipoPersona(tipoPersona);

        System.out.println("Ingrese el tipo de cuenta CORRIENTE(C) o AHORRO(A) o CORRIENTE USS(CU) o AHORRO USS(AU): ");
        String tipoCuentaStr = scanner.nextLine().toUpperCase();
        while (!tipoCuentaStr.equals("C") && !tipoCuentaStr.equals("A" )  && !tipoCuentaStr.equals("CU") && !tipoCuentaStr.equals("AU")) {
            System.out.println("Tipo de cuenta inválido. Ingrese CORRIENTE o AHORRO o CORRIENTE USS o AHORRO USS:");
            tipoCuentaStr = scanner.nextLine().toUpperCase();
        }
        if (tipoCuentaStr.equals("C")) {
            cliente.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE);
        } else if (tipoCuentaStr.equals("A")) {
            cliente.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        } else if (tipoCuentaStr.equals("CU")) {
            cliente.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE_USS);
        } else if (tipoCuentaStr.equals("AU")) {
            cliente.setTipoCuenta(TipoCuenta.CAJA_AHORRO_USS);
        }

        System.out.println("Ingrese el banco del cliente:");
        String banco = scanner.nextLine();
        cliente.setBanco(banco);

        System.out.println("Ingrese la fecha de alta del cliente (Formato: YYYY-MM-DD):");
        LocalDate fechaAlta = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                fechaAlta = LocalDate.parse(scanner.nextLine());
                fechaValida = true;
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Ingrese la fecha en formato YYYY-MM-DD:");
            }
        }
        cliente.setFechaAlta(fechaAlta);

        clearScreen();
        return cliente;
    }
}
