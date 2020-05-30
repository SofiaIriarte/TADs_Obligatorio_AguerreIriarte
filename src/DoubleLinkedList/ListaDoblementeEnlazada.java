public class ListaDoblementeEnlazada<T> implements Lista<T> {
    private NodoDoble<T> primero;
    private int size=0;

    public NodoDoble<T> getPrimero() {
        return primero;
    }

    public void setPrimero(NodoDoble<T> primero) {
        this.primero = primero;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public boolean encontrar(T value){
        NodoDoble<T> nodo= primero;
        for (int i=0;i<size;i++){
            if (nodo.getValue()==value){
                return true;
            }
            nodo=nodo.getSiguiente();
        }
        return false;
    }
    public void addLast(T value) {
        if (primero==null){
            primero=new NodoDoble<>(value,null, null);
        }
        else {
            NodoDoble<T> nodo = primero;
            while (nodo.getSiguiente()!=null){
                nodo=nodo.getSiguiente();
            }

             NodoDoble<T> nodotemp =new NodoDoble<>(value,null, nodo);
            nodo.setSiguiente(nodotemp);
        }
        size++;
    }

    public void addFirst(T value){
        if (primero== null){
            primero= new NodoDoble<T>(value,null,null);
        }
        else {
            NodoDoble<T> nodo= new NodoDoble<T>(value, primero, null);
            primero.setAnterior(nodo);
           nodo = primero;
        }
        size++;
    }

    @Override
    public void add(T value) {
        addLast(value);
    }

    @Override
    public void remove(int position) throws EmptyList,InvalidIndex {
        if (this.primero==null){
            throw new EmptyList();
        }
        if (position>=size+1){
            throw new InvalidIndex();
        }
        else {
            NodoDoble<T> nodo = primero;
            for (int i = 0; i < position; i++) {
                nodo = nodo.getSiguiente();
            }
            if (nodo.getSiguiente() == null && nodo.getAnterior() == null) {

                primero = null;
            } else if (nodo.getSiguiente() == null) {
                nodo.getAnterior().setSiguiente(null);
            } else if (nodo.getAnterior() == null) {
                nodo.getSiguiente().setAnterior(null);
            }
            size--;
        }
    }

    @Override
    public T get(int position) {

        NodoDoble<T> buscado = this.primero;
        T value = null;

        try {

            if(position<0){
                throw new  NullPointerException();
            }

            for (int i=0; i < position; i++) {
                buscado = buscado.getSiguiente();
            }
            value = buscado.getValue();
        }
        catch (NullPointerException e){
            System.out.println("Indice invalido (indx max=" +(size-1) +")");
        }
        return value;
    }
}
