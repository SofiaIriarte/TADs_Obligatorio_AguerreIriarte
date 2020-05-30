//package Stack;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyStackIMPTest {
    MyStackIMP<Integer> temp =new MyStackIMP<>();
    @Test
    public void testPop() {
        temp.pop(); //probando un tipo de flujo incorrecto: eliminar de una lista vacía
    }

    @Test
    public void testTop() {
        temp.top(); // tomar el primer elemento de una lista vacía
    }

    @Test
    public void flujoNormal() {
        temp.push(1);
        temp.push(2);
        assertFalse(temp.isEmpty());
        assertEquals(temp.top(), new Integer(2));
        temp.pop();
        assertEquals(temp.top(), new Integer(1));
        temp.makeEmpty();
        assertTrue(temp.isEmpty());


    }
}
