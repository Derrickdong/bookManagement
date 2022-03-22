package model;

public class Book {

	private int id;
	private String bookName;
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
}
