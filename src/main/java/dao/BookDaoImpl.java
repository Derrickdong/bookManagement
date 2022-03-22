package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.Book;

@Repository("bookDao")
public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao{

	@Override
	public List<Book> getAllBooks() {
		String hql = "from Book";
		return super.list(hql, null, null);
	}

}
