package cn.bitzo.bms.service.Impl;

import cn.bitzo.bms.dao.BookDao;
import cn.bitzo.bms.entity.Book;
import cn.bitzo.bms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> queryBooks() {
        List<Book> books = bookDao.queryAllBooks();
        return books;
    }

    @Override
    public Book queryBookById(Integer id) {
        Book book = bookDao.queryBookById(id);
        return book;
    }

    @Override
    public Integer countAllBooks() {
        Integer num = bookDao.countAllBook();
        return num;
    }

    @Override
    public Boolean updateBookInfo(Book book) {
        int num = bookDao.updateBook(book);
        if( num > 0 ) return true;
        return false;
    }

    @Override
    public int addBook(Book book) {
        int num = bookDao.addBook(book);
        if (num > 0) {
            return num;
        }
        return 0;
    }
}
