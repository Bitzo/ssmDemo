package cn.bitzo.bms.entity;

import java.util.Date;

public class Borrow {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private User user;
    private Book book;
    private Integer number;
    private Date borrowDate;
    private Date backDate;

    public Borrow(Integer id, Integer userId, Integer bookId, User user, Book book, Integer number, Date borrowDate, Date backDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.user = user;
        this.book = book;
        this.number = number;
        this.borrowDate = borrowDate;
        this.backDate = backDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", user=" + user +
                ", book=" + book +
                ", number=" + number +
                ", borrowDate=" + borrowDate +
                ", backDate=" + backDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public Borrow() {

    }
}
