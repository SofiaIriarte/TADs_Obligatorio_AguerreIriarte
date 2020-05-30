package CircularLinkedList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    @Test
    public void flujoNormal() throws ExceptionListaVacia {
        CircularLinkedList<String> cL = new CircularLinkedList<String>();
        try {
            cL.add("uno");
            String obtener = cL.get(0);
            assertEquals("uno",obtener);
            cL.add("dos");
            String obtener1 = cL.get(1);
            String obtener2 = cL.get(1);
            assertNotSame(obtener,obtener1);
            assertSame(obtener1,obtener2);
            cL.remove(0);
        } catch (ExceptionListaVacia exceptionListaVacia) {
            System.out.println("Esta lista esta vacia");
        }
    }

    @Test
    public void probar() throws ExceptionListaVacia {
        CircularLinkedList<String> cL = new CircularLinkedList<String>();
        Assertions.assertThrows(ExceptionListaVacia.class, () -> {
            cL.remove(0);
        });
    }
}