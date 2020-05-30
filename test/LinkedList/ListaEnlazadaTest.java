package LinkedList;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListaEnlazadaTest {
    ListaEnlazada<Integer> temp= new ListaEnlazada<>();
    @Test
    public void flujoNormal() throws EmptyList,InvalidIndex{
        temp.add(2);
        temp.addFirst(1);
        assertTrue(temp.encontrar(2));
        temp.remove(1);
        assertFalse(temp.encontrar(2));
        assertEquals(temp.get(0),new Integer(1));
    }


    @Test
    public void flujoAlternativo() throws EmptyList,InvalidIndex {
        temp.remove(0);//Borrar algo de una lista vacía
        temp.add(1);
        temp.remove(1);//Borrar un índice fuera del rango
    }

    @Test
    public void get() {
    }
}