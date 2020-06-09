import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class ObligatorioImp implements Obligatorio{

    Scanner books;
    Scanner ratings;
    Scanner to_read;

    public void Principal() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Menu:\nSeleccione la opcion que desee:\n\t" +
                "1. Carga de datos\n\t" +
                "2. Ejecutar consultas\n" +
                "3. Salir");
        String opcion = br.readLine();
        int numero = Integer.parseInt(opcion);
        switch (numero){
            case 1:
                carga();
            case 2:
                consultas();
            case 3:
                break;
            default:
                throw new IOException();
        }

    }

    public void carga(){
        long tiempoInicio=System.currentTimeMillis();
        String booksFile = "books.csv";
        String ratingsFile = "ratings.csv";
        String to_readFile = "to_read.csv";
        File bFile = new File(booksFile);
        File rFile = new File(ratingsFile);
        File tFile = new File(to_readFile);
        try {
            Scanner books = new Scanner(bFile);
            Scanner ratings = new Scanner(rFile);
            Scanner to_read = new Scanner(tFile);
        } catch (FileNotFoundException e){
            System.out.print("No se han podido cargar los datos, intente de nuevo");
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Carga de datos exitosa, tiempo de ejecución de la carga:" + tiempo + "ms");
    }

    public void consultas() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("1. Indicar el Top 10 de libros que más lecturas tienen por parte de usuarios.\n" +
                "2. Indicar el Top 20 de libros que más cantidad de lecturas tienen.\n" +
                "3. Indicar el Top 10 de usuarios que realizaron mayor cantidad de evaluaciones a libros " +
                "y ordenarlo por rating promedio descendente.\n" +
                "4. Indicar el Top 5 de los idiomas asociados a libros que han tenido más reservas.\n" +
                "5. Indicar el Top 20 de autores que más publicaciones han hecho por año.\n" +
                "6. Salir");
        String opcion = br.readLine();
        int numero = Integer.parseInt(opcion);
        switch (numero){
            case 1:
                c1();
            case 2:
                c2();
            case 3:
                c3();
            case 4:
                c4();
            case 5:
                c5();
            case 6:
                break;
            default:
                throw new IOException();
        }
    }

    public void c1(){
        long tiempoInicio=System.currentTimeMillis();
        int id_libro=0;
        String titulo=null;
        int cantidad=0;
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Id del libro:"+id_libro+"Titulo:"+titulo+"Cantidad:"+cantidad+"Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c2(){
        long tiempoInicio=System.currentTimeMillis();
        int id_libro=0;
        String titulo=null;
        int cantidad=0;
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Id del libro:"+id_libro+"Titulo:"+titulo+"Cantidad:"+cantidad+"Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c3(){
        long tiempoInicio=System.currentTimeMillis();
        int id_usuario=0;
        int cantidad=0;
        int rating_prom=0;
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Id del usuario:"+id_usuario+"Cantidad:"+cantidad+"Rating promedio:"+rating_prom+"Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c4(){
        long tiempoInicio=System.currentTimeMillis();
        int cod_idioma=0;
        int cantidad=0;
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Codigo del idioma:"+cod_idioma+"Cantidad:"+cantidad+"Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c5(){
        long tiempoInicio=System.currentTimeMillis();
        String autor=null;
        Date anio_de_publi=null;
        int cantidad=0;
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Autor:"+autor+"Anio de publicacion:"+anio_de_publi+"Cantidad:"+cantidad+"Tiempo de ejecucion de la consulta:"+tiempo);
    }

}
