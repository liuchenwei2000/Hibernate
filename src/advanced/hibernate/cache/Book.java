/**
 * 
 */
package hibernate.cache;

/**
 * ʵ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��6��
 */
public class Book {

	private Long id;
	private String name;
	private String author;
	private String isbn;

	public Book() {
		super();
	}

	public Book(String name, String author, String isbn) {
		this.name = name;
		this.author = author;
		this.isbn = isbn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", isbn=" + isbn
				+ "]";
	}
}
