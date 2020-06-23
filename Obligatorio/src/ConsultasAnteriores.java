public class ConsultasAnteriores {

        /*1
        long sizeTo_Read = 912705;
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


        /*public void c3(HashImpl<Long,Book> to_read,HashImpl<Long,Rating> ratings) throws KeyYaExiste {
        long tiempoInicio=System.currentTimeMillis();
        int sizeTo_Read= 6000000;
        HeapMax<Integer, LinkedList> heap3 = new HeapMax(sizeTo_Read);
        for (int i=0;i<1000;i++){
            int counter=1;
            Rating hola= (Rating) ratings.find((long) i);
            User id_user=hola.getUser_id();
            int j=0;
            while(j<1000&&ratings.find((long)j).getUser_id()==id_user){
                counter++;
                j++;
            }
            LinkedList datos = new LinkedList<>();
            datos.addFirst(id_user);
            datos.add(counter);
            try {
                heap3.agregar(counter,datos);
            } catch (KeyYaExiste k){
                /*LinkedList newDatos = heap3.obtenerYEliminar();
                long id_userNew = (long) newDatos.get(0);
                int counterNew = (int) newDatos.get(1);
                counterNew++;
                LinkedList datosNew=null;
                datosNew.addFirst(id_userNew);
                datos.add(counterNew);
                heap3.agregar(id_userNew,datosNew);
    i++;
}
        }
                int cantidadEva;
                int ratingProm;
                HeapMax<Long, LinkedList<Integer>> conRating = new HeapMax(10);
        for (int l=0;l<10;l++){
        LinkedList extraccion;
        extraccion = heap3.obtenerYEliminar();
        long id_userNew = (Long) extraccion.get(0);
        cantidadEva = (int) extraccion.get(1);
        ratingProm=0;
        for (int i=0;i<1000;i++){
        Rating hola1 = (Rating) ratings.find((long) i);
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
        System.out.print("Tiempo de ejecucion de la consulta:"+tiempo+" ms\n");
        }*/



        /*2
        long tiempoInicio=System.currentTimeMillis();
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


}
