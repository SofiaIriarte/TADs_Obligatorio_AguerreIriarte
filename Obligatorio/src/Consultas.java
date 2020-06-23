import tads.Hash.Hash;
import tads.Hash.HashImpl;
import tads.Heap.HeapMax;
import tads.Heap.HeapMin;
import tads.Heap.KeyYaExiste;
import tads.LinkedList.LinkedList;

import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Consultas {

    public void c1(HashImpl<Long,Book> to_read,HashImpl<Long,Book> books) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String titulo="";
        HeapMax<Integer,Book> cons = new HeapMax<>(912705);
        HashImpl<Long,Integer> consInt = new HashImpl<>(912705);
        long id_book=0;
        for (long i=0;i<10000;i++){
            int counter=0;
            try{
                while (to_read.find(i).getBook_id()==i){
                    counter++;
                }
                cons.agregar(counter,to_read.find(i));
                consInt.put(to_read.find(i).getBook_id(),counter);
            } catch (NullPointerException n){
                break;
            }
        }
        /*long sizeTo_Read = 912705;
        System.out.print(sizeTo_Read);
        String titulo=null;
        HeapMax<Long,Book> to_read1=to_read;
        HashImpl<Long, User[]> hash1 = new HashImpl((int) sizeTo_Read);
        HashImpl<Long, LinkedList> heap1 = new HashImpl<>((int) sizeTo_Read);
        HeapMax<Integer, Integer> heap2 = new HeapMax((int) sizeTo_Read);
        HashImpl<Long, Integer> heap3 = new HashImpl((int) sizeTo_Read);
        long id_book=0;
        while (sizeTo_Read>1) {
            Book hola=to_read1.obtenerYEliminar();
            id_book =hola.getBook_id();
            User[] user = new User[2000];
            try{
                user = hola.getReserved_to_read();
            } catch (Exception e){
                hola.setReserved_to_read(user);
            }
            //HashImpl<Long,User[]> users = new HashImpl(10000);
            //users.put((long)i,user);
            //hash1.put((long)i,user);
            int valueSize=0;
            LinkedList<Integer> values = new LinkedList();
            values.addFirst((int) id_book);
            try {
                try {
                    values.add(user.length);
                } catch (Exception e){
                    values.add(0);
                }
                heap1.put(id_book,values);
                valueSize =values.getSize()-1;
            } catch (Exception k){
                //id_book=heap2.obtenerYEliminar();
                //valueSize=heap1.find(id_book).getSize()-1;
                valueSize++;
                //heap2.agregar(valueSize, (int) id_book);
            }
            heap2.agregar(valueSize,(int)id_book);
            heap3.put(id_book,valueSize);
            sizeTo_Read--;
        }*/
        try{
            for (int l=0;l<9;l++){
                id_book=cons.obtenerYEliminar().getBook_id();
                int cantidad = (int) consInt.find(id_book);
                try {
                    titulo=books.find(id_book).getTitle();
                } catch (Exception e){
                    titulo="";
                }
                System.out.println("Id del libro: " + id_book + " Titulo: " + titulo + " Cantidad: " + cantidad);
            }
        } catch (Exception e){
            System.out.print("La cantidad de reservas es menor a la solicitada");
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta: "+tiempo+"\n");
    }

    public void c2(HashImpl<Long,Book> to_read,HashImpl<Long,Book> books) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String titulo="";
        HeapMax<Integer,Book> cons = new HeapMax<>(912705);
        HashImpl<Long,Integer> consInt = new HashImpl<>(912705);
        long id_book=0;
        for (long i=0;i<10000;i++){
            int counter=0;
            try{
                while (to_read.find(i).getBook_id()==i){
                    counter++;
                }
                cons.agregar(counter,to_read.find(i));
                consInt.put(to_read.find(i).getBook_id(),counter);
            } catch (NullPointerException n){
                break;
            }
        }
        try{
            for (int l=0;l<19;l++){
                id_book=cons.obtenerYEliminar().getBook_id();
                int cantidad = (int) consInt.find(id_book);
                try {
                    titulo=books.find(id_book).getTitle();
                } catch (Exception e){
                    titulo="";
                }
                System.out.println("Id del libro: " + id_book + " Titulo: " + titulo + " Cantidad: " + cantidad);
            }
        } catch (Exception e){
            System.out.print("La cantidad de reservas es menor a la solicitada");
        }
        /*long tiempoInicio=System.currentTimeMillis();
        String titulo=null;
        long sizeTo_Read= 912705;
        HashImpl<Long, HashImpl<Long,User[]>> hash1 = new HashImpl((int) sizeTo_Read);
        HashImpl<Long, LinkedList> heap2 = new HashImpl((int) sizeTo_Read);
        long id_book=0;
        User[] user = new User[2000];
        Book hola = new Book();
        HeapMax<Integer, Integer> heap1 = new HeapMax((int) sizeTo_Read);

        for (int i=0;i<sizeTo_Read;i++) {
            hola=to_read.find((long)i);
            id_book =hola.getBook_id();
            try{
                user = hola.getReserved_to_read();
            } catch (Exception e){
                user=new User[0];
            }
            HashImpl<Long,User[]> users = new HashImpl<>(10000);
            users.put((long)i,user);
            hash1.put((long)i,users);
            try {
                LinkedList<Integer> values = new LinkedList();
                values.addFirst(i);
                try {
                    values.add(users.find((long) i).length);
                } catch (Exception e){
                    values.add(0);
                }
                heap2.put((long)i,values);
                int valueSize =values.getSize()-1;
                heap1.agregar(valueSize, i);
            } catch (KeyYaExiste k){
                System.out.print("55555555555");
                break;
            }
        }

        for (int l=0;l<19;l++){
            id_book=heap1.obtenerYEliminar();
            int cantidad = (int) heap2.find(id_book).get(0);
            try {
                titulo=books.find(id_book).getTitle();
            } catch (Exception e){
                break;
            }
            System.out.println("Id del libro:" + id_book + "Titulo:" + titulo + "Cantidad:" + cantidad);
        }*/
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c3(HashImpl<Long,Book> to_read,HashImpl<Long,Rating> ratings) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        HashImpl<Long,Rating> ratings1 = ratings;
        int sizeTo_Read= 100000000;
        HeapMax<Long, LinkedList> heap3 = new HeapMax(sizeTo_Read);
        for (int i=0;i<1000;i++){
            int counter=0;
            Rating hola= (Rating) ratings1.find((long) i);
            User id_user=hola.getUser_id();
            LinkedList datos = new LinkedList<>();
            datos.addFirst(id_user);
            datos.add(counter);
            try {
                heap3.agregar(id_user.getUser_id(),datos);
            } catch (KeyYaExiste k){
                LinkedList newDatos = heap3.obtenerYEliminar();
                long id_userNew = (long) newDatos.get(0);
                int counterNew = (int) newDatos.get(1);
                counterNew++;
                LinkedList datosNew=null;
                datosNew.addFirst(id_userNew);
                datos.add(counterNew);
                heap3.agregar(id_userNew,datosNew);
            }
        }
        int cantidadEva;
        int ratingProm;
        HeapMax<Long, LinkedList<Integer>> conRating = new HeapMax(9);
        for (int l=0;l<9;l++){
            LinkedList extraccion;
            extraccion = heap3.obtenerYEliminar();
            long id_userNew = (Long) extraccion.get(0);
            cantidadEva = (int) extraccion.get(1);
            ratingProm=0;
            for (int i=0;i<1000;i++){
                Rating hola1 = (Rating) ratings1.find((long) i);
                if (id_userNew==hola1.getUser_id().getUser_id()){ //revisar
                    ratingProm+=hola1.getRating();
                }
            }
            LinkedList nuevo = new LinkedList();
            nuevo.addFirst(id_userNew);
            nuevo.addFirst(cantidadEva);
            nuevo.add(ratingProm);
            conRating.agregar(id_userNew,nuevo);
        }
        //ordenar segun 3er atributo la lista conRating
        for (int m=0;m<conRating.getSize();m++){
            LinkedList<Integer> ultima;
            ultima = conRating.obtenerYEliminar();
            int idUser = ultima.get(0);
            cantidadEva = ultima.get(1);
            ratingProm=ultima.get(2);
            System.out.println("Id del usuario:" + idUser + "Cantidad:" + cantidadEva + "Rating promedio:" + ratingProm);
        }

        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c4(HashImpl<Long,Book> to_read) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String cod_idioma=null;
        int sizeTo_Read = 100000000;
        int cantidad=0;
        HeapMax<String,Integer> idiomas = new HeapMax<>(sizeTo_Read);
        for (int i=0;i<sizeTo_Read;i++){
            Book algo = to_read.find((long)i);
            cod_idioma = algo.getLanguage_code();
            cantidad = algo.getReserved_to_read().length;
            try {
                idiomas.agregar(cod_idioma,cantidad);
            } catch (KeyYaExiste k) {
                int cantidadNew = idiomas.obtenerYEliminar(); //como se que esta es la misma key??
                cantidadNew += cantidad;
                idiomas.agregar(cod_idioma,cantidadNew);
            }
        }
        for (int j=0;j<5;j++){
            cantidad = idiomas.obtenerYEliminar(); //como obtengo mi key?
            System.out.println("Codigo del idioma:"+cod_idioma+"Cantidad:"+cantidad);
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c5(HashImpl<Long,Book> books) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String autor=null;
        int anio_de_publi=0;
        HeapMin<Long, Integer> anio = new HeapMin<>(10000);
        for (int i=0;i<10000;i++){
            long id = books.find((long) i).getBook_id();
            anio_de_publi = books.find((long) i).getOriginal_publication_year();
            anio.agregar(id,anio_de_publi);
        }
        anio_de_publi = anio.obtenerYEliminar();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        int actual = Integer.parseInt(dtf.format(now));
        while (anio_de_publi<actual){
            HeapMax<Integer,Author[]> autores = new HeapMax<>(10000);
            HeapMax<Integer,Integer> autores1 = new HeapMax<>(10000);
            for (int i=0;i<10000;i++){
                int counter=1;
                if (books.find((long) i).getOriginal_publication_year()==anio_de_publi){
                    try {
                        autores.agregar(counter,books.find((long)i).getAuthor());
                        autores1.agregar(counter,counter);
                    } catch (KeyYaExiste k){
                        Author[] autor1 = autores.obtenerYEliminar();
                        counter = autores1.obtenerYEliminar();
                        counter++;
                        autores.agregar(counter,autor1);
                        autores1.agregar(counter,counter);
                    }
                }
            }
            for (int i=0;i<20;i++){
                Author[] autor2 = autores.obtenerYEliminar();
                int counter = autores1.obtenerYEliminar();
                System.out.println("Autor:"+autor2+"Anio de publicacion:"+anio_de_publi+"Cantidad:"+counter);
            }
            anio_de_publi++;
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Tiempo de ejecucion de la consulta:"+tiempo);
    }
}
