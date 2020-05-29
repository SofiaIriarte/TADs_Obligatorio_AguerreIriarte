package Hash;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

class HashImplTest {
    @Test
    public void flujoNormal() throws ElementoNoExiste{
        String key = "key";
        String key1 = "keey";
        int size = 5;
        Integer value = 1;
        Integer value1 = 2;
        HashImpl<String,Integer> h = new HashImpl<String, Integer>(size);
        try {
            h.put(key,value);
            Integer where = h.find(key);
            Integer where1 = h.find(key1);
            assertNotNull(where);
            assertEquals(1,where);
            assertNotEquals(4,where);
            assertNull(where1);
            boolean pertenece = h.contains(key);
            assertTrue(pertenece);
            assertFalse(h.contains(key1));
            h.put(key1,value1);
            Integer where2 = h.find(key1);
            Integer where3 = h.find(key1);
            assertNotSame(where,where2);
            assertSame(where2,where3);
            h.remove(key);
        } catch (ElementoNoExiste elementoNoExiste){
            System.out.println("No existe");
        }
    }

    @Test
    public void probar() throws ElementoNoExiste {
        String key = "key";
        int size =5;
        Integer value = 1;
        String key1 = "keey";
        HashImpl<String,Integer> h = new HashImpl<String, Integer>(size);
        Assertions.assertThrows(ElementoNoExiste.class, () -> {
            h.remove(key1);
        });
    }
}