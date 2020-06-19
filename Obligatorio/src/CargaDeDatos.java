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

    public LinkedList<String> cargaBooks(LinkedList<String> books) throws IOException{
        try {
            br = new BufferedReader(new FileReader(booksFile));
            line=br.readLine();
            String[] datos = line.split(cvsSplitBy);
            books.addFirst(Arrays.toString(datos));
            while (line != null) {
                datos = line.split(cvsSplitBy);
                books.add(Arrays.toString(datos));
                line=br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        br.close();
        return books;
    }
    public LinkedList<String[]> cargaRatings(LinkedList<String[]> ratings) throws IOException{
        try {
            br = new BufferedReader(new FileReader(ratingsFile));
            line=br.readLine();
            while (line != null) {
                String[] datos = line.split(cvsSplitBy);
                ratings.add(datos);
                line=br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        //br.close();
        return ratings;
    }
    public LinkedList<String[]> cargaTo_Read(LinkedList<String[]> to_read) throws IOException{
        try {
            br = new BufferedReader(new FileReader(to_readFile));
            line=br.readLine();
            while (line != null) {
                String[] datos = line.split(cvsSplitBy);
                to_read.add(datos);
                line=br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        }
        //br.close();
        return to_read;
    }

}
