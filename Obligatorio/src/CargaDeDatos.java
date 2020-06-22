import tads.Hash.Hash;
import tads.Hash.HashImpl;
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

    public LinkedList<Long> id_books() throws IOException{
        LinkedList<Long> id_books = new LinkedList();
        try {
            br = new BufferedReader(new FileReader(booksFile));
            line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] datos = identificadorDeComas(line);
                id_books.add(Long.parseLong(datos[0]));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
        return id_books;
    }

    public HashImpl<Long, Book> cargaBooks() throws IOException {
        int contador = 0;
        HashImpl<Long, Book> books = new HashImpl<>(10000);
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
                    Book libro = new Book(Long.parseLong(datos[0]), datos[1], authors, Integer.parseInt(datos[i]), datos[i + 1], datos[i + 2], datos[i + 3], datos[i+4]);
                    books.put(libro.getBook_id(), libro);
                    contador++;
                }
                else {
                    String[] datos = identificadorDeComas(line);
                    Author[] authors = new Author[datos.length - 7];//Resto 7 porque es la cantidad de datos que tienen cantidad fija. (No son autores)
                    int position = 0;
                    int i;
                    for (i = 2; i < 2 + authors.length; i++) {
                        authors[position] = new Author(datos[i]);
                        position++;
                    }
                    Book libro = new Book(Long.parseLong(datos[0]), datos[1], authors, 0000, datos[i + 1], datos[i + 2], datos[i + 3], datos[i + 4]);
                    books.put(libro.getBook_id(), libro);
                    contador++;
                }
                line = br.readLine();
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
        int contador=0;
        HashImpl<Long,Book> to_read = new HashImpl<>(912705);
        try {
            br = new BufferedReader(new FileReader(to_readFile));
            line=br.readLine();
            line=br.readLine();
            while (line != null) {
                if (!(line.contains(",\"nan\"")||  line.contains(",NaN")||  line.contains(",nan"))){
                    String[] datos = identificadorDeComas(line);
                    long id_user= Long.parseLong(datos[0]);
                    User user=new User(id_user);
                    user.setUser_id(id_user);
                    long book_id= Long.parseLong(datos[1]);
                    for (int i=0;i<10000;i++) {
                        try{
                            if (book_id==book.find((long)i).getBook_id()){
                                User[] users = book.find((long)i).getReserved_to_read();
                                User[] userList = new User[users.length];
                                for (int l=0;l<users.length;l++){
                                    userList[i]=users[i];
                                }
                                userList[-1]=user;
                                book.find(book_id).setReserved_to_read(userList);
                                Book bookAdd = book.find((long)i);
                                to_read.put(book_id,bookAdd);
                            }
                        } catch (NullPointerException n){
                            break;
                        }
                    }
                    contador++;
                }
                line=br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
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
