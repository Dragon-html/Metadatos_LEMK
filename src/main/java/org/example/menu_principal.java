package org.example;

import org.example.gestores.GestorBBDD;
import org.example.gestores.GestorMigracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class menu_principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Connection conOriginal = null;
        Connection conDestino = null;

        try {
            Class.forName("org.sqlite.JDBC"); //Para que se use si o si el driver que daba problemas
            conOriginal = Conexion.getConnection();

            String rutaDestino = "playstation2.sqlite";
            conDestino = DriverManager.getConnection("jdbc:sqlite:" + rutaDestino);
            conDestino.setAutoCommit(false);

            GestorMigracion gm = new GestorMigracion();
            GestorBBDD gestor = new GestorBBDD(conDestino);

            int opcion;
            do {
                System.out.println("\n Menú");
                System.out.println("1. Insertar nuevo juego en PlayStation 2");
                System.out.println("2. Crear tabla de destino (si no existe)");
                System.out.println("3. Migrar datos de GameCube a PlayStation 2");
                System.out.println("4. Salir");
                System.out.println("5. Prueba, listar juegos");

                System.out.print("Selecciona una opción: ");

                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {

                    case 1:
                        try {
                            System.out.print("Introduce el ID del juego: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Introduce el título del juego: ");
                            String title = scanner.nextLine();

                            System.out.print("Introduce el desarrollador: ");
                            String developer = scanner.nextLine();

                            System.out.print("Introduce el publisher: ");
                            String publisher = scanner.nextLine();

                            System.out.print("Europa (Sí/No): ");
                            String europe = scanner.nextLine();

                            System.out.print("Japón (Sí/No): ");
                            String japan = scanner.nextLine();

                            System.out.print("Norteamérica (Sí/No): ");
                            String northAmerica = scanner.nextLine();

                            Juego juego = new Juego(id, title, developer, publisher, europe, japan, northAmerica);

                            gestor.insertarJuego(juego);

                        } catch (Exception e) {
                            System.out.println("Error al insertar el juego: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            gm.crearTablas(conOriginal, conDestino);
                        } catch (SQLException e) {
                            System.out.println("Error al crear la tabla: " + e.getMessage());
                        }
                        break;

                    case 3:
                        try {
                            gm.enviarDatos(conOriginal, conDestino);
                        } catch (SQLException e) {
                            System.out.println("Error al migrar datos: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("Saliendo...");
                        break;

                    case 5:
                        gestor.listarJuegos();
                        break;
                    default:
                        System.out.println("La opción no es válida. Intenta de nuevo.");
                        break;


                }

            } while (opcion != 4);

        } catch (Exception e) {
            System.out.println("Error en la conexión o inicialización: " + e.getMessage());
            e.printStackTrace();
        } finally {
            //Copiada de manual pero ni me arrancaba por algo de los drivers :(
            try {
                if (conOriginal != null && !conOriginal.isClosed()) conOriginal.close();
                if (conDestino != null && !conDestino.isClosed()) conDestino.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }
    }
}
