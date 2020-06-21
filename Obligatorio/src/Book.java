import tads.LinkedList.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Book {

    private long book_id;
    private String isbn;
    private Author author;
    private int original_privation_year;
    private String original_title;
    private String title;
    private String language_code;
    private String image_url;
    private LinkedList<User> reserved_to_read;

    public Book(long book_id, String isbn, Author author, int original_privation_year, String original_title, String title, String language_code, String image_url) {
        this.book_id = book_id;
        this.isbn = isbn;
        this.author = author;
        this.original_privation_year = original_privation_year;
        this.original_title = original_title;
        this.title = title;
        this.language_code = language_code;
        this.image_url = image_url;
    }

    public Book() {
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getOriginal_privation_year() {
        return original_privation_year;
    }

    public void setOriginal_privation_year(int original_privation_year) {
        this.original_privation_year = original_privation_year;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public LinkedList<User> getReserved_to_read() {
        return reserved_to_read;
    }

    public void setReserved_to_read(LinkedList<User> reserved_to_read) {
        this.reserved_to_read = reserved_to_read;
    }
}
