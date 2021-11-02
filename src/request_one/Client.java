package request_one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	// Puerto de conexion con el servidor.
	public static final int PORT = 2020;
	// Nombre del servidor.
	public static final String IP_SERVER = "localhost";
	
	public static void main(String[] args) {
		System.out.println("        APLICACIÓN CLIENTE");
		System.out.println("-----------------------------------");

		// Objeto encapsulacion informacion de la conexion.
		InetSocketAddress serverAdress = new InetSocketAddress(IP_SERVER, PORT);
		
		// Bloque try, abre el escaner y lo cierra al terminar el bloque.
		try (Scanner sc = new Scanner(System.in)){
			
			System.out.println("CLIENTE: Esperando a que el servidor accepte la conexion");
			//------ SE ESTABLECE LA CONEXION CON EL SERVIDOR -----
			// Creacion nuevo objeto socket que conectara con el servidor.
			Socket socketToServer = new Socket();
			// El socket se conecta al servidor pasandole como argumento el objeto que contiene la informacion del servidor.
			socketToServer.connect(serverAdress);
			// Objeto que realiza la recepcion de informacion enviada por parte del servidor.
			InputStreamReader inputFromServer = new InputStreamReader(socketToServer.getInputStream());
			// Objeto que nos permite leer linea a linea la respuesta del servidor en vez de  caracter a caracter.
			BufferedReader inputBuffer = new BufferedReader(inputFromServer);
			// Objeto que realizara ael envio de informacion al servido.
			PrintStream outputToServer = new PrintStream(socketToServer.getOutputStream());
			//----------------------------------------------------
			System.out.println("CLIENTE: conexion establecida a " + IP_SERVER + " por el puerto: " + PORT);
			
			//--------------------- MENU --------------------------
			// boolean para controlar la salida del do while.
			boolean flag = true;
			do {
				// Solicita al usuario que elija una opcion por consola.
				System.out.println(
						  "-----------------------------------\n"
						+ "Tiene que elegir una de estas 3 opciones:"
						+ "\n1-.Consultar libro por ISBN"
						+ "\n2-.Consultar libro por titulo"
						+ "\n3-.Salir de la aplicacion");
				
				// Variable donde almacenaremos la informacion que enviaremos al servidor.
				// Lee la respuesta del usuario por consola.
				String answer = sc.nextLine();
				// Variable donde almacenaremos la respuesta del servidor.
				String respuesta = null;
				// Bucle que solicita un numero hasta que el valor introducido sea 1, 2 o 3.
				while (!answer.equals("3") && !answer.equals("1") && !answer.equals("2")) {
					System.out.println("Tiene que escoger una de las 3 opciones");
					// Lee la respuesta del usuario por consola.
					answer = read.nextLine();
				}
				
				//Respuesta del programa segun la eleccion del usuario.
				switch (answer) {
					case "1":
						// Solicita por consola el ISBN del libro
						System.out.println("Escribe el ISBN del libro que deseas encontrar: ");
						// Lee el isbn introducido por consola y lo almacena en la variable.
						String bookIsbn = read.nextLine();
						// Envia al servidor la seleccion del menu seguido de un - y el isbn.
						outputToServer.println(answer + "-" + bookIsbn);
						// Queda a la espera de una respuesta por parte del servidor.
						serverAnswer = inputBuffer.readLine();
						// Salida del Switch.
						break;
					case "2":
						// Solicita por consola el titulo del libro.
						System.out.println("Escribe el titulo del libro que deseas encontrar: ");
						// Lee el titulo introducido por consola y lo almacena en la variable.
						String bookTitle = read.nextLine();
						// Envia al servidor la seleccion del menu seguido de un - y el titulo.
						outputToServer.println(answer + "-" + bookTitle);
						// Queda a la espera de una respuesta por parte del servidor.
						serverAnswer = inputBuffer.readLine();
						// Salida del Switch.
						break;
					case "3":
						// Envia la respuesta del usuario al servidor.
						outputToServer.println("FIN");
						// Cambia el boolean para salir del do while.
						flag = false;
						// Queda a la espera de una respuesta por parte del servidor.
						serverAnswer = inputBuffer.readLine();
						// Salida del Switch.
						break;
				}
				 // Imprime la respuesta por consola.
				 System.out.println(respuesta);
			// Fin del do while, comprueba si el boolean es true.
			}while(flag);
			//Se cierra la conexion con el servidor.
			socketAlServidor.close();  
		// Captura las posibles excepciones.	
		} catch (UnknownHostException e) {	
			System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}
		
		
	}

}
