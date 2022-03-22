package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.BookDao;
import dao.UserDao;
import model.Book;

public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService{

	@Autowired
	BookDao bookDao;
	
	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

}
