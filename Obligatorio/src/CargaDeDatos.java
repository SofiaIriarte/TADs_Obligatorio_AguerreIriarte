import tads.Hash.Hash;
import tads.Hash.HashImpl;
import tads.Heap.HeapMax;
import tads.LinkedList.LinkedList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CargaDeDatos {
    private BufferedReader br = null;
    private String booksFile = "src\\docs\\books.csv";
    private String ratingsFile = "src\\docs\\ratings.csv";
    private String to_readFile = "src\\docs\\to_read.csv";
    private String line = "";
    private String cvsSplitBy = ",";

    public HashImpl<Long, Book> cargaBooks() throws IOException {
        HashImpl<Long, Book> books = new HashImpl<>(9998);
        try {
            br = new BufferedReader(new FileReader(booksFile));
            line = br.readLine();
            line = br.readLine();
            while (line != null) {
                if (!(line.contains(",\"nan\"") || line.contains(",NaN") || line.contains(",nan"))) {
                    String[] datos = identificadorDeComas(line);
                    Author[] authors = new Author[datos.length - 7];//Resto 7 porque es la cantidad de datos que tienen cantidad fija. (No son autores)
                    int position = 0;
                    int i;
                    for (i = 2; i < 2 + authors.length; i++) {
                        authors[position] = new Author(datos[i]);
                        position++;
                    }
                    Book libro = new Book();
                    libro.setBook_id(Long.parseLong(datos[0]));
                    libro.setIsbn(datos[1]);
                    libro.setAuthor(authors);
                    libro.setOriginal_publication_year(Integer.parseInt(datos[i]));
                    libro.setOriginal_title(datos[i + 1]);
                    libro.setTitle(datos[i + 2]);
                    libro.setLanguage_code(datos[i + 3]);
                    libro.setImage_url(datos[i + 4]);
                    books.put(Long.parseLong(datos[0]), libro);
                    line = br.readLine();
                } else{
                    String[] datos = identificadorDeComas(line);
                    Author[] authors = new Author[datos.length - 7];//Resto 7 porque es la cantidad de datos que tienen cantidad fija. (No son autores)
                    int position = 0;
                    int i;
                    for (i = 2; i < 2 + authors.length; i++) {
                        authors[position] = new Author(datos[i]);
                        position++;
                    }
                    Book libro = new Book();
                    libro.setBook_id(Long.parseLong(datos[0]));
                    libro.setAuthor(authors);
                    libro.setOriginal_publication_year(0000);
                    libro.setOriginal_title(datos[i + 1]);
                    libro.setTitle(datos[i + 2]);
                    libro.setLanguage_code(datos[i + 3]);
                    books.put(Long.parseLong(datos[0]), libro);
                    line = br.readLine();
                }
             }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
        return books;
    }

    public HashImpl<Long, Rating> cargaRatings(HashImpl<Long, Book> books) throws IOException{
        int contador=0;
        Book libroAEncontrar;
        HashImpl<Long, Rating> ratings = new HashImpl<>(6000000);
        try {
            br = new BufferedReader(new FileReader(ratingsFile));
            line=br.readLine();
            line=br.readLine();
            while (line != null) {
                if (!(line.contains(",\"nan\"")||  line.contains(",NaN")||  line.contains(",nan"))){
                    String[] datos = identificadorDeComas(line);
                    libroAEncontrar = books.find(Long.parseLong(datos[1]));
                    if (libroAEncontrar != null) {
                        try {
                            User user = new User(Long.parseLong(datos[0]));
                            Rating dato = new Rating(Integer.parseInt(datos[2]),user,libroAEncontrar);
                            ratings.put(user.getUser_id(),dato);
                            contador++;
                        } catch (Exception e ){
                            contador++;
                            System.out.println("Error en linea"+contador);
                        }
                    } else {
                        try {
                            Book book = new Book();
                            book.setBook_id(Long.parseLong(datos[1]));
                            book.setTitle(null);
                            book.setAuthor(null);
                            book.setImage_url(null);
                            book.setIsbn(null);
                            book.setLanguage_code("nan");
                            book.setOriginal_publication_year(0000);
                            book.setOriginal_title(null);
                            User user = new User(Long.parseLong(datos[0]));
                            Rating dato = new Rating(Integer.parseInt(datos[2]),user,book);
                            ratings.put(user.getUser_id(),dato);
                            contador++;
                        } catch (Exception e) {
                            contador++;
                            System.out.println("Error en linea"+contador);
                        }
                    }
                }
                line=br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
        return ratings;
    }
    public HashImpl<Long,Book> cargaTo_Read(HashImpl<Long,Book> book) throws IOException{
        HashImpl<Long,Book> to_read = new HashImpl<>(912705);
        try {
            br = new BufferedReader(new FileReader(to_readFile));
            line=br.readLine();
            line=br.readLine();
            while (line != null) {
                String[] datos = identificadorDeComas(line);
                long id_user= Long.parseLong(datos[0]);
                User user=new User(id_user);
                user.setUser_id(id_user);
                long book_id= Long.parseLong(datos[1]);
                try {
                    Book book1 = book.find(book_id);
                    try {
                        User[] users = book1.getReserved_to_read();
                        User[] userList = new User[users.length+1];
                        for (int l=0;l<users.length;l++){
                            userList[l]=users[l];
                        }
                        userList[users.length-1]=user;
                        book1.setReserved_to_read(userList);
                    } catch (Exception e) {
                        User[] userList = new User[1];
                        userList[0] = user;
                        book1.setReserved_to_read(userList);
                    }
                    to_read.put(book_id,book1);
                    line=br.readLine();
                } catch (Exception e){
                    line=br.readLine();
                }            }
        } catch (Exception e){
                line=null;
        }
        br.close();
        return to_read;
    }

    private String[] identificadorDeComas(String renglon) {//este metodo sirve para que al cargar los datos se sepa cuales comillas/comas forman parte de un nombre y cuales estan para separar
        int cantidad = 0;
        boolean hayComilla = false;
        String[] datosSeparados = new String[8];
        String aux = "";
        for (int i = 0; i < renglon.length(); i++) {
            char caracter = renglon.charAt(i);
            if (caracter == '"' || caracter == ',') {
                if (caracter == '"') {
                    hayComilla = !hayComilla;
                }
                if (!hayComilla && caracter == ',') {
                    if (datosSeparados.length < cantidad + 1) {// +1 pa que no de null pointer en comparacion de hijos
                        String[] array2 = Arrays.copyOf(datosSeparados, datosSeparados.length + 1);
                        datosSeparados = array2;
                    }
                    datosSeparados[cantidad] = aux;
                    aux = "";
                    cantidad++;
                }
            } else {
                aux = aux + caracter;
            }
        }
        datosSeparados[cantidad] = aux;//para que agregue el útlimo sin dar error
        return datosSeparados;
    }

}
