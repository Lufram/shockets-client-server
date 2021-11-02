package request_one;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	// Puerto por el que se conectara el Cliente.
	public static final int PORT = 2020;
	
	public static void main(String[] args) {
		System.out.println("      APLICACIÓN SERVIDOR     ");
		System.out.println("-------------------------------------");
		
		// Creamos una biblioteca y añadimos algunos libros.
		Catalogue bookCatalogue = new Catalogue();
		bookCatalogue.addBook("A1234","Odisea","Homero",19.99);
		bookCatalogue.addBook("B1234","Divina Comedia", "Dante", 20);
		bookCatalogue.addBook("C1234", "La Biblia", "Pepe", 15.55);
		bookCatalogue.addBook("D1234", "Harry Potter", "J.K Rowling", 9.99);
		bookCatalogue.addBook("E1234", "Crepusculo", "Stephenie", 23);
		
		//TRY - WHITH - RESOURCES
		try{
			// Crea un socket servidor 
			ServerSocket server = new ServerSocket();
			// Objeto que encapsula la informacion de conexion con el servidor.
			InetSocketAddress adress = new InetSocketAddress(PORT);
			// Asigna la direccion del puerto al socket.
			server.bind(adress);
			
			System.out.println("SERVIDOR: Esperando peticion por el puerto " + PORT);
			// Escucha peticiones de clientes permanentemente.
			while(true) {
				// Queda a la espera de una peticion entrante.
				Socket socketToClient = server.accept();
				// Informa por consola que ha entrado una peticion.
				System.out.println("SERVIDOR: peticion de consulta recibida");
				// Crea un nuevo hilo para peticion pasandole la peticion aceptada y la biblioteca donde almacenamos los libros.
				new ClientRequest(socketToClient, bookCatalogue);
			}
			// Cierra el servidor.
			server.close;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block.
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error");
			e.printStackTrace();
		}	
			
	}

}
