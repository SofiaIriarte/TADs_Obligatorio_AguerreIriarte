import tads.LinkedList.LinkedList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CargaDeDatos {
    private BufferedReader br = null;
    private String booksFile = "C:\\Users\\maxi_\\Desktop\\TADs_Obligatorio_AguerreIriarte-master\\datos_obligatorio\\books.csv";
    private String ratingsFile = "Datos_Obligatorio/ratings.csv";
    private String to_readFile = "Datos_Obligatorio/to_read.csv";
    private String line = "";
    private String cvsSplitBy = ",";

    public LinkedList<String[]> cargaBooks(LinkedList<String[]> books){
        books=null;
        long tiempoInicio=System.currentTimeMillis();
        try {
            br = new BufferedReader(new FileReader(booksFile));
            line=br.readLine();
            while (line != null) {
                String[] datos = line.split(cvsSplitBy);
                books.add(datos);
                line=br.readLine();
            }
            long tiempoFin=System.currentTimeMillis();
            long tiempo= tiempoFin-tiempoInicio;
            System.out.print("Carga de datos exitosa, tiempo de ejecución de la carga:" + tiempo + "ms");
        } catch (FileNotFoundException e){
            System.out.print("Error al cargar los datos, intente de nuevo");
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return books;
    }
    public LinkedList<String[]> cargaRatings(LinkedList<String[]> ratings){
        ratings=null;
        long tiempoInicio=System.currentTimeMillis();
        try {
            br = new BufferedReader(new FileReader(ratingsFile));
            line=br.readLine();
            while (line != null) {
                String[] datos = line.split(cvsSplitBy);
                ratings.add(datos);
                line=br.readLine();
            }
            long tiempoFin=System.currentTimeMillis();
            long tiempo= tiempoFin-tiempoInicio;
            System.out.print("Carga de datos exitosa, tiempo de ejecución de la carga:" + tiempo + "ms");
        } catch (FileNotFoundException e){
            System.out.print("Error al cargar los datos, intente de nuevo");
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ratings;
    }
    public LinkedList<String[]> cargaTo_Read(LinkedList<String[]> to_read){
        to_read=null;
        long tiempoInicio=System.currentTimeMillis();
        try {
            br = new BufferedReader(new FileReader(to_readFile));
            line=br.readLine();
            while (line != null) {
                String[] datos = line.split(cvsSplitBy);
                to_read.add(datos);
                line=br.readLine();
            }
            long tiempoFin=System.currentTimeMillis();
            long tiempo= tiempoFin-tiempoInicio;
            System.out.print("Carga de datos exitosa, tiempo de ejecución de la carga:" + tiempo + "ms");
        } catch (FileNotFoundException e){
            System.out.print("Error al cargar los datos, intente de nuevo");
        } catch (IOException e) {
            System.out.print("Error al cargar los datos, intente de nuevo");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return to_read;
    }

}
