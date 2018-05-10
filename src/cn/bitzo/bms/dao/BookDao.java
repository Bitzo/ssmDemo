package cn.bitzo.bms.dao;

import cn.bitzo.bms.entity.Book;

import java.util.List;

public interface BookDao {
    public List<Book> queryAllBooks();
    public Book queryBookById(int id);
    public int updateBook(Book book);
    public int countAllBook();
    public int addBook(Book book);
}
