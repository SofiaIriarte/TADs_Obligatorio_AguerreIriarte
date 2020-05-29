package BinaryTree;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeImpTest {
    @Test
    public void flujoNormal() throws InvalidValue {
        String cosa = "hola";
        String cosa1 = "holaa";
        String cosa2 = "hoola";
        BinaryTreeImp<String> bT = new BinaryTreeImp<String>(cosa);
        try {
            ArrayList<String> lista = bT.inOrder();
            bT.add(cosa);
            BinaryTree<String> tree = bT.delete(cosa);
            boolean exist = bT.exists(cosa);
            assertFalse(bT.exists(cosa));
            bT.add(cosa);
            boolean exist1 = bT.exists(cosa);
            assertTrue(bT.exists(cosa));
            String value = bT.getMinValue();
            String value1 = bT.getMaxValue();
            int h = bT.getHeight();
            String root = bT.getRoot();
            String root1 = bT.getRoot();
            assertSame(root,root1);
            assertEquals("cosa",root);
            assertNotEquals("cosa1",root);
            assertNotNull(root);
            bT.add(cosa1);
            bT.add(cosa2);
            BinaryTree<String> leftChild = bT.getLeftChild();
            BinaryTree<String> rightChild = bT.getRightChild();
            assertNotSame(rightChild,leftChild);
            BinaryTree<String> where = bT.find(cosa);
        } catch (InvalidValue invalidValue) {
            System.out.println("Valor invalido, pruebe con otro");
        }
    }
    @Test
    public void probar() throws InvalidValue {
        String cosa = "hola";
        BinaryTreeImp<String> bT = new BinaryTreeImp<String>(cosa);
        Assertions.assertThrows(InvalidValue.class, () -> {
            bT.add(null);
        });
    }
}