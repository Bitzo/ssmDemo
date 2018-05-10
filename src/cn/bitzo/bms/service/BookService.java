package cn.bitzo.bms.service;

import cn.bitzo.bms.entity.Book;

import java.util.List;

public interface BookService {

    public List<Book> queryBooks();

    public Book queryBookById(Integer id);

    public Integer countAllBooks();

    public Boolean updateBookInfo(Book book);

    public int addBook(Book book);
}
