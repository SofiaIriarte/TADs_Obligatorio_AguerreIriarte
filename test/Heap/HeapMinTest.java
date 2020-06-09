package Heap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

class HeapMinTest {
    @Test
    public void flujoNormal() throws KeyYaExiste {
        String key = "key";
        String key1 = "keey";
        int size = 5;
        Integer value = 1;
        Integer value1 = 2;
        HeapMin<String,Integer> hM = new HeapMin<String, Integer>(size);
        try {
            hM.agregar(key,value);
            int size1 = hM.getSize();
            assertEquals(1,size1);
            assertNotNull(size1);
            hM.obtenerYEliminar();
            int size2 = hM.getSize();
            assertNotEquals(2,size2);
            assertNotSame(size1,size2);
        } catch (KeyYaExiste keyYaExiste){
            System.out.println("Ya existe");
        }
    }

    @Test
    public void probar() throws KeyYaExiste {
        String key = "key";
        int size = 5;
        Integer value = 1;
        HeapMin<String,Integer> hM = new HeapMin<String, Integer>(size);
        Assertions.assertThrows(KeyYaExiste.class, () -> {
            hM.agregar(key,value);
            hM.agregar(key,value);
        });
    }
}