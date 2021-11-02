package request_one;

public class Book {
	// Variables
	private String isbn;
	private String title;
	private String autor;
	private double price;
	
	// Constructor
	public Book (String isbn, String title, String autor,  double price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.autor = autor;
		this.price = price;
	}
	// Getters y setters
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "----- Libro solicitado : |isbn= " + isbn + "|" + " |titulo= " + title + "|" + " |autor= " + autor + "|" + " |precio= " + price + "€| -----";
	}
	
	
}
