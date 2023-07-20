package by.teachmeskills.hw_16062023;

public class BookFactory {
    Book book;

    public BookFactory(Book book) {
        this.book = book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    Book cloneBook() {
        return (Book) book.copy();
    }
}
