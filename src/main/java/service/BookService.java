package service;

import java.util.List;

import model.Book;

public interface BookService extends BaseService<Book>{

	public List<Book> getAllBooks();
}
