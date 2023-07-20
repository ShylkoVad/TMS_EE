package by.teachmeskills.hw_16062023;

public class VersionControlRunner {
    public static void main(String[] args) {
        Book book = new Book("Мастер и Маргарита", "Булгаков", 2023);
        System.out.println(book);

        BookFactory factory = new BookFactory(book);
        Book cloneBook = factory.cloneBook();

        System.out.println("\n===================================\n");
        System.out.println(cloneBook);
    }
}
