import tads.Heap.HeapMax;
import tads.Heap.KeyYaExiste;
import tads.LinkedList.LinkedList;

import java.util.Date;

public class Consultas {

    public void c1(LinkedList<String[]> to_read, int sizeTo_Read, LinkedList<String[]> books, int sizeBooks) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String titulo=null;
        LinkedList<String[]> to_read1=to_read;
        HeapMax<Integer, LinkedList<Integer>> heap1 = new HeapMax(sizeTo_Read);
        for (int i=0;i<sizeTo_Read;i++){
            String[] hola = to_read1.get(i);
            int id_book= Integer.parseInt(hola[1]);
            int id_user= Integer.parseInt(hola[0]);
            LinkedList users = new LinkedList();
            users.addFirst(id_book);
            users.add(id_user);
            try {
                heap1.agregar(id_book,users);
            } catch (KeyYaExiste k) {
                users = (LinkedList) heap1.obtenerYEliminar(); // como hago para que aca me elimine el que yo quiero y no el primero??
                boolean addId=true;
                for (int j=1;j<users.getSize();j++){
                    if (j==Integer.parseInt(String.valueOf(users.get(j)))){
                        addId=false;
                    }
                }
                if (addId==true){
                    users.add(id_user);
                    heap1.agregar(id_book,users);
                }
            }
        }
        for (int l=0;l<9;l++){
            LinkedList<Integer> datos = heap1.obtenerYEliminar();
            int id_book = datos.get(0);
            int cantidad = datos.getSize();
            LinkedList<String[]> books1=books;
            for (int i=0;i<sizeBooks;i++){
                if (id_book==Integer.parseInt(books1.get(i)[0])){
                    titulo=books1.get(i)[5];
                    System.out.println("Id del libro:" + id_book + "Titulo:" + titulo + "Cantidad:" + cantidad);
                }
            }
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo);

    }
    public void c2(LinkedList<String[]> to_read, int sizeTo_Read, LinkedList<String[]> books, int sizeBooks) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String titulo=null;
        LinkedList books1 = books;
        LinkedList to_read1 = to_read;
        HeapMax<Integer, LinkedList<Integer>> heap2 = new HeapMax(sizeTo_Read);
        for (int i=0;i<sizeTo_Read;i++){
            int counter=1;
            String[] hola = (String[]) to_read1.get(i);
            int id_book= Integer.parseInt(hola[1]);
            LinkedList<Integer> datos = null;
            datos.addFirst(id_book);
            datos.add(counter);
            try {
                heap2.agregar(id_book, datos);
            } catch (KeyYaExiste k){
                LinkedList<Integer> newDatos = heap2.obtenerYEliminar();
                int id_bookNew = newDatos.get(0);
                int counterNew = newDatos.get(1);
                counterNew++;
                LinkedList<Integer> datosNew=null;
                datosNew.addFirst(id_bookNew);
                datos.add(counterNew);
                heap2.agregar(id_bookNew,datosNew);
            }
        }
        for (int l=0;l<19;l++){
            LinkedList<Integer> datos = heap2.obtenerYEliminar();
            int id_book = datos.get(0);
            int cantidad = datos.get(1);
            for (int i=0;i<sizeBooks;i++){
                String[] hola1 = (String[]) books1.get(i);
                if (id_book==Integer.parseInt(hola1[0])){
                    titulo=hola1[5];
                    System.out.println("Id del libro:" + id_book + "Titulo:" + titulo + "Cantidad:" + cantidad);
                }
            }
        }
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c3(LinkedList<String[]> to_read, int sizeTo_Read, LinkedList<String[]> ratings, int sizeRatings) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        LinkedList ratings1 = ratings;
        HeapMax<Integer, LinkedList<Integer>> heap3 = new HeapMax(sizeTo_Read);
        for (int i=0;i<sizeRatings;i++){
            int counter=0;
            String[] hola= (String[]) ratings1.get(i);
            int id_user=Integer.parseInt(hola[0]);
            LinkedList<Integer> datos = new LinkedList<>();
            datos.addFirst(id_user);
            datos.add(counter);
            try {
                heap3.agregar(id_user,datos);
            } catch (KeyYaExiste k){
                LinkedList<Integer> newDatos = heap3.obtenerYEliminar();
                int id_userNew = newDatos.get(0);
                int counterNew = newDatos.get(1);
                counterNew++;
                LinkedList<Integer> datosNew=null;
                datosNew.addFirst(id_userNew);
                datos.add(counterNew);
                heap3.agregar(id_userNew,datosNew);
            }
        }
        int cantidadEva;
        int ratingProm;
        HeapMax<Integer, LinkedList<Integer>> conRating = new HeapMax(9);
        for (int l=0;l<9;l++){
            LinkedList<Integer> extraccion;
            extraccion = heap3.obtenerYEliminar();
            int id_userNew = extraccion.get(0);
            cantidadEva = extraccion.get(1);
            ratingProm=0;
            for (int i=0;i<sizeRatings;i++){
                String[] hola1 = (String[]) ratings1.get(i);
                if (id_userNew==Integer.parseInt(hola1[0])){
                    ratingProm+=Integer.parseInt(hola1[2]);
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
    public void c4() throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        int cod_idioma=0;
        int cantidad=0;
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Codigo del idioma:"+cod_idioma+"Cantidad:"+cantidad+"Tiempo de ejecucion de la consulta:"+tiempo);
    }
    public void c5() throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        String autor=null;
        Date anio_de_publi=null;
        int cantidad=0;
        long tiempoFin=System.currentTimeMillis();
        long tiempo= tiempoFin-tiempoInicio;
        System.out.println("Autor:"+autor+"Anio de publicacion:"+anio_de_publi+"Cantidad:"+cantidad+"Tiempo de ejecucion de la consulta:"+tiempo);
    }
}
