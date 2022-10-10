import java.lang.reflect.Field;

/**
 * Template for a tester for CS1331 2018 Summer HW9.
 * @author (your name here)
 * @version 13313
 */
public class TesterTemplate {

    /*
     * Table of helpful methods:
     * getHead(LinkedListNode<E> list) - Get the head node of the list.
     */

    /**
     * Main method for the tester.
     * @param args Console launch arguments.
     */
    public static void main(String[] args) {
    }
















    /* ********************************************************************* */
    private static Field f;
    static {
        init();
    }
    /**
     * init routine
     */
    private static void init() {
        try {
            f = RecursiveLinkedList.class.getDeclaredField("head");
            f.setAccessible(true);
        } finally {
            return;
        }
    }
    /**
     * Get the head of a list.
     * @param list the list to get the head of
     * @param <T> generic type of list
     * @return the head of the input list
     */
    private static <T> LinkedListNode<T> getHead(RecursiveLinkedList<T> list) {
        try {
            return (LinkedListNode<T>) f.get(list);
        } catch (IllegalAccessException t) {
            throw new SecurityException(t);
        }
    }

}