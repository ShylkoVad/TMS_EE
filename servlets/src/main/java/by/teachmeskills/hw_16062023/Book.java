package by.teachmeskills.hw_16062023;

public class Book implements Copyable {
    private String name;
    private String author;
    private int yearPublishing;

    public Book(String name, String author, int yearPublishing) {
        this.name = name;
        this.author = author;
        this.yearPublishing = yearPublishing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(int yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    @Override
    public Object copy() {
        Book copyBook = new Book(name, author, yearPublishing);
        return copyBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearPublishing=" + yearPublishing +
                '}';
    }
}
