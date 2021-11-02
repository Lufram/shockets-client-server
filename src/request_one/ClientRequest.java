package request_one;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientRequest implements Runnable{
	
	//----VARIABLES---
	// Objeto hilo.
	private Thread serverThread;
	// Contador de clientes.
	private static int clientNum = 0;
	// Conexion con el cliente.
	private Socket socketToClient;
	// Catalogo de libros.
	private Catalogo bookCatalogue;
	
	// Constructor.
	public HiloConsultaLibro (Socket socketToClient, Catalogue bookCatalogue) {
		// Aumenta el contador de clientes.
		clientNum++;
		// Crea un nuevo hilo.
		serverThread = new Thread(this, "Cliente_" + clientNum);
		this.socketToClient = socketToClient;
		this.bookCatalogue = bookCatalogue;
		// Inicia el hilo.
		serverThread.start();
	}

	// Metodo run sobreescrito.
	@Override
	public void run() {
		// Informa por consola la conexion con el nº de cliente.
		System.out.println("Estableciendo conexion con " + serverThread.getName());
		// Inicializacion de variables internas a null.
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader entradaBuffer = null;
		// Blo
		try {
			salida = new PrintStream(socketAlCliente.getOutputStream());  //servidor --> cliente
			entrada = new InputStreamReader(socketAlCliente.getInputStream());  //cliente --> servidor
			entradaBuffer = new BufferedReader(entrada);  //lectura de datos
			
			String consulta = "";
			boolean continuar = true;
			
			//Respuesta al usuario segun su elecion
			while (continuar) {
				consulta = entradaBuffer.readLine();
				String resultado = "Libros solicitados:";
				
				if (consulta.equalsIgnoreCase("FIN")) {        //usuario elige salir de la aplicacion
					salida.println("Saliendo del programa");
					System.out.println(hilo.getName() + " se ha desconectado");
					continuar = false;
				} else if (consulta.equalsIgnoreCase("uno")){   //usuario elige consulta por isbn
					consulta = entradaBuffer.readLine();
					for (int i = 0; i < catalogoLibros.libros.size(); i++) {
						if (consulta.trim().equalsIgnoreCase(catalogoLibros.libros.get(i).getIsbn())) {
							resultado = catalogoLibros.libros.get(i).toString();
							break;
						}else {
							resultado = "No hemos podido encontrar ningun libro con el isbn indicado en nuestra base de datos";
						}
					}
				} else if (consulta.equalsIgnoreCase("dos")){     //usuario elige consulta por titulo
					consulta = entradaBuffer.readLine();
					for (int i = 0; i < catalogoLibros.libros.size(); i++) {
						if (consulta.trim().equalsIgnoreCase(catalogoLibros.libros.get(i).getTitulo())) {
							resultado = catalogoLibros.libros.get(i).toString();
							break;
						}else {
							resultado = "No hemos podido encontrar ningun libro con el titulo indicado en nuestra base de datos";
						}
					}
				}
				salida.println(resultado);
			}

			//se cierra el socket
			socketAlCliente.close();  
			
			
		} catch (IOException e) {
			System.err.println("HiloConsultaLibro: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("HiloConsultaLibro: Error");
			e.printStackTrace();
		}
		
	}

}
