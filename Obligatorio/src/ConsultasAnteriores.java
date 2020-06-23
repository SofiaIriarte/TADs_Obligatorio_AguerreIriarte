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
