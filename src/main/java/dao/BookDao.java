package dao;

import java.util.List;

import model.Book;
import model.User;

public interface BookDao extends BaseDao<Book> {
	
	public List<Book> getAllBooks();
}
