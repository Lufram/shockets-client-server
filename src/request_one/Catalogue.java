package request_one;

import java.util.ArrayList;
import java.util.List;

public class Catalogue {
	// Almacena los libros en una lista.
	private ArrayList<Book> books;
	// Constructor
	public Catalogue() {
		super();
		// Crea una lista al inicializarse
		this.books = new ArrayList();
	}
	
	// Getters y setters
	public ArrayList<Libro> getLibros() {
		return books;
	}
	public void setLibros(ArrayList<Libro> libros) {
		this.books = books;
	}
	// Metodos sobreescritos
	@Override
	public String toString() {
		return "Catalogo [libros=" + books + "]";
	}
	// Metodos 
	public void addBook(String isbn, String title, String autor, double price) {
		this.books.add(new Book(isbn, title, autor, price));
	}
	 
}


