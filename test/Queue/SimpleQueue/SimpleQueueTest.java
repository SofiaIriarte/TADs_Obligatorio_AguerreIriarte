package Queue.SimpleQueue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

class SimpleQueueTest {
    @Test
    public void flujoNormal() throws QueueFullException, EmptyQueueException {
        int cosa = 1;
        int cosa1 = 2;
        SimpleQueue<Integer> cQ = new SimpleQueue<Integer>();

        try {
            cQ.enqueue(cosa);
            Integer primero = cQ.getFirst();
            Integer primero1 = cQ.getFirst();
            assertEquals(1,primero);
            assertNotEquals(4,primero);
            assertSame(primero,primero1);
            assertNotNull(primero);
            boolean algo = cQ.isEmpty();
            assertFalse(algo);
            Integer eliminar = cQ.dequeue();
            boolean algo1 = cQ.isEmpty();
            assertTrue(algo1);
            Integer eliminar1 = cQ.dequeue();
            cQ.enqueue(cosa1);
            Integer primero2 = cQ.getFirst();
            assertNotSame(primero,primero2);
        } catch (EmptyQueueException emptyQueueException) {
            System.out.println("Esta vacia");
        }
    }
    @Test
    public void probar(){
        int cosa = 1;
        SimpleQueue<Integer> cQ = new SimpleQueue<Integer>();
        Assertions.assertThrows(EmptyQueueException.class, () -> {
            cQ.dequeue();
        });
    }
}