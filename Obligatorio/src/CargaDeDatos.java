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

    public LinkedList<Book> cargaBooks() throws IOException{
        int contador=0;
        LinkedList<Book> books = new LinkedList<>();
        try {
            br = new BufferedReader(new FileReader(booksFile));
            line=br.readLine();
            line=br.readLine();
            while (line != null) {
                if (!(line.contains(",\"nan\"")||  line.contains(",NaN")||  line.contains(",nan"))){
                    String[] datos = identificadorDeComas(line);
                    Author[] authors = new Author[datos.length - 7];//Resto 7 porque es la cantidad de datos que tienen cantidad fija. (No son autores)
                    int position = 0;
                    int i;
                    for (i = 2; i < 2 + authors.length; i++) {
                        authors[position] = new Author(datos[i]);
                        position++;
                    }
                    Book libro = new Book(Long.parseLong(datos[0]), datos[1], authors, Integer.parseInt(datos[i]), datos[i + 1], datos[i + 2], datos[i + 3], datos[i] + 4);
                    books.add(libro);
                    contador++;
                    //System.out.println(contador);
                }
                line=br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
        return books;
    }
    public LinkedList<Rating> cargaRatings() throws IOException{
        int contador=0;
        LinkedList<Rating> ratings = new LinkedList<>();
        try {
            br = new BufferedReader(new FileReader(ratingsFile));
            line=br.readLine();
            line=br.readLine();
            while (line != null) {
                if (!(line.contains(",\"nan\"")||  line.contains(",NaN")||  line.contains(",nan"))){
                    String[] datos = identificadorDeComas(line);
                    int rating= Integer.parseInt(datos[0]);
                    long id_user= Long.parseLong(datos[1]);
                    int book_id= Integer.parseInt(datos[2]);
                    Rating ratingAdd = new Rating();
                    ratingAdd.setBook_id(book_id);
                    ratingAdd.setUser_id(id_user);
                    ratingAdd.setRating(rating);
                    ratings.add(ratingAdd);
                    contador++;
                }
                line=br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
        return ratings;
    }
    public LinkedList<Book> cargaTo_Read(LinkedList<Book> book) throws IOException{
        int contador=0;
        LinkedList<Book> to_read = new LinkedList<>();
        LinkedList<User> userList=new LinkedList<>();
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
                    userList.add(user);
                    long book_id= Long.parseLong(datos[1]);
                    /*for (int i=0;i<book.getSize();i++) {
                        if (book_id==book.get(i).getBook_id()){
                            book.get(i).setReserved_to_read(userList);
                        }
                    }*/
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

    private String[] identificadorDeComas(String renglon){
        int cantidad=0;
        boolean hayComilla= false;
        String[] datosSeparados= new String[8];
        String aux="";
        for (int i=0;i<renglon.length();i++){
            char caracter=renglon.charAt(i);
            if (caracter=='"'|| caracter==','){
                if (caracter=='"'){
                    hayComilla=!hayComilla;
                }
                if (!hayComilla && caracter==','){
                    if (datosSeparados.length < cantidad + 1) {// +1 pa que no de null pointer en comparacion de hijos
                        String[] array2 = Arrays.copyOf(datosSeparados, datosSeparados.length + 1);
                        datosSeparados = array2;
                    }
                    datosSeparados[cantidad]=aux;
                    aux="";
                    cantidad++;
                }
            }
            else{
                aux=aux+caracter;
            }
        }
        datosSeparados[cantidad]=aux;//para que agregue el útlimo sin dar error
        return datosSeparados;
    }

}
