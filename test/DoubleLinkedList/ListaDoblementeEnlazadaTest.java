import org.junit.Test;

import static org.junit.Assert.*;

public class ListaDoblementeEnlazadaTest {
    ListaDoblementeEnlazada<Integer> temp = new ListaDoblementeEnlazada<>();

    @Test
    public void flujoNormal() throws EmptyList, InvalidIndex {
        temp.add(1);
        temp.add(2);
        assertTrue(temp.encontrar(1));
        temp.remove(0);
        assertFalse(temp.encontrar(2));
        assertEquals(temp.get(0),new Integer(1));
    }
    @Test
    public void flujoAlternativo() throws EmptyList,InvalidIndex{
        temp.add(1);
        temp.remove(2);//remover una posición que está fuera de los límites
        temp.remove(0);
        temp.remove(0);//eliminar algo de una lista vacía
    }
}