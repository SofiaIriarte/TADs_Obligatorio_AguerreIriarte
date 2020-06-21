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
                    Book libro = new Book(Long.parseLong(datos[0]), datos[1], authors, Integer.parseInt(datos[i]), datos[i + 1], datos[i + 2], datos[i + 3], datos[i] + 4);
                    books.put(libro.getBook_id(), libro);
                    contador++;
                    System.out.println(contador);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
        return books;
    }

    public HashImpl<Long, Rating> cargaRatings(HashImpl<Long, Book> books) throws IOException {
        HashImpl<Long, Rating> ratings = new HashImpl<>(1000);
        Book libroAEncontrar;
        int contador = 0;
        try {
            br = new BufferedReader(new FileReader(ratingsFile));
            line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] datos = line.split(cvsSplitBy);
                libroAEncontrar = books.find(Long.parseLong(datos[0]));
                if (libroAEncontrar != null) {
                    try {
                        ratings.put(Long.parseLong(datos[0] + datos[1]), new Rating(Integer.parseInt(datos[2]), new User(Long.parseLong(datos[1])), libroAEncontrar));
                        contador++;
                        System.out.println(contador+" "+line);
                    } catch (Exception e ){
                        contador++;
                        System.out.println("Error en linea"+contador);
                    }
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
        return ratings;
    }

    public void cargaTo_Read() throws IOException {
        try {
            br = new BufferedReader(new FileReader(to_readFile));
            line = br.readLine();
            while (line != null) {
                String[] datos = line.split(cvsSplitBy);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
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
