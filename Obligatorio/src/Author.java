import tads.LinkedList.LinkedList;

import java.io.IOException;
import java.lang.reflect.Array;

public class Author {

    private String name;
    private LinkedList<String[]> books;

    public void cargaBooks() throws IOException {
        Book book = new Book();
        books = book.cargaBooks();
        for (int i=0;i<books.getSize();i++){
            String bookName = books.get(i)[2];
            if (name!=bookName) {
                books.remove(i);
            }
        }
    }



}
