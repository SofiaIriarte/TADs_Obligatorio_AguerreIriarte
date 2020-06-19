import tads.LinkedList.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Book {

    private long book_id;
    private String isbn;
    private String author;
    private int original_privation_year;
    private String original_title;
    private String title;
    private String language_code;
    private String image_url;
    private int rating;
    private LinkedList reserved_to_read;
    private BufferedReader br = null;
    private String booksFile = "src\\docs\\books.csv";
    private String line = "";
    private String cvsSplitBy = ",";

    public LinkedList cargaBooks() throws IOException {
        LinkedList booksList = new LinkedList();
        try {
            br = new BufferedReader(new FileReader(booksFile));
            line=br.readLine();
            String[] datos = line.split(cvsSplitBy);
            book_id= Long.parseLong(datos[0]);
            isbn=datos[1];
            author=datos[2];
            original_privation_year= Integer.parseInt(datos[3]);
            original_title=datos[4];
            title=datos[5];
            language_code=datos[6];
            image_url=datos[7];
            LinkedList<String> book = new LinkedList();
            book.addFirst(String.valueOf(book_id));
            book.add(isbn);
            book.add(String.valueOf(original_privation_year));
            book.add(original_title);
            book.add(title);
            book.add(language_code);
            book.add(image_url);
            booksList.addFirst(book);
            line=br.readLine();
            while (line != null) {
                datos = line.split(cvsSplitBy);
                book_id= Long.parseLong(datos[0]);
                isbn=datos[1];
                author=datos[2];
                original_privation_year= Integer.parseInt(datos[3]);
                original_title=datos[4];
                title=datos[5];
                language_code=datos[6];
                image_url=datos[7];
                book = new LinkedList();
                book.addFirst(String.valueOf(book_id));
                book.add(isbn);
                book.add(String.valueOf(original_privation_year));
                book.add(original_title);
                book.add(title);
                book.add(language_code);
                book.add(image_url);
                booksList.add(book);
                line=br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        System.out.print(booksList);
        br.close();
        return booksList;
    }

    public void cargaUsers() throws IOException {
        try {
            User user = new User();
            reserved_to_read = user.cargaUsers();
        } catch (IOException i) {
            System.out.print("No se pudieron obtener los usuarios");
        }
    }

    public void cargaRating() throws IOException {
        try {
            Rating rating = new Rating();
            LinkedList ratingList = rating.cargaRating();
        } catch (IOException i) {
            System.out.print("No se pudieron obtener los ratings");
        }
    }
}
