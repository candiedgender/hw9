import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.List;

/**
 * Tester. Lots of black magic, bad design choices, etc. Do not use this as
 * a model for good design.
 * @author jdierberger3
 * @version 1.0
 */
public class Tester {

    /**
     * Main method.
     * @param args Console launch args.
     */
    public static void main(String[] args) {
        System.out.println("**********************************");
        System.out.println("THIS IS NOT A COMPREHENSIVE TESTER");
        System.out.println();
        System.out.println("    PASSING THESE TESTS DOESN'T   ");
        System.out.println("  GUARANTEE 100 ON THE ASSIGNMENT ");
        System.out.println("**********************************");
        Class clazz;
        try {
            clazz = RecursiveLinkedList.class;
        } catch (Error e) {
            System.out.println("FATAL: Missing RecursiveLinkedList class "
                + "(did you compile it?)");
            throw e;
        }
        part1(clazz);
        try {
            final RecursiveLinkedList r = new RecursiveLinkedList();
            try {
                r.set(0, null);
            } catch (UnsupportedOperationException u) {
                Runtime.getRuntime().freeMemory();
            } catch (Throwable t) {
                System.out.println("Incorrect stub on set");
            }
            try {
                r.isEmpty();
            } catch (UnsupportedOperationException u) {
                Runtime.getRuntime().freeMemory();
            } catch (Throwable t) {
                System.out.println("Incorrect stub on isEmpty");
            }
            try {
                r.containsAll(null);
            } catch (UnsupportedOperationException u) {
                Runtime.getRuntime().freeMemory();
            } catch (Throwable t) {
                System.out.println("Incorrect stub on containsAll");
            }
            try {
                r.addAll(null);
            } catch (UnsupportedOperationException u) {
                Runtime.getRuntime().freeMemory();
            } catch (Throwable t) {
                System.out.println("Incorrect stub on addAll(Collection)");
            }
            try {
                r.removeAll(null);
            } catch (UnsupportedOperationException u) {
                Runtime.getRuntime().freeMemory();
            } catch (Throwable t) {
                System.out.println("Incorrect stub on removeAll");
            }
            try {
                r.add(0, null);
            } catch (UnsupportedOperationException u) {
                Runtime.getRuntime().freeMemory();
            } catch (Throwable t) {
                System.out.println("Incorrect stub on add(int, E)");
            }
            try {
                r.listIterator();
            } catch (UnsupportedOperationException u) {
                Runtime.getRuntime().freeMemory();
            } catch (Throwable t) {
                System.out.println("Incorrect stub on listIterator");
            }
            try {
                r.listIterator(0);
            } catch (UnsupportedOperationException u) {
                Runtime.getRuntime().freeMemory();
            } catch (Throwable t) {
                System.out.println("Incorrect stub on listIterator(int)");
            }
            try {
                r.subList(0, 0);
            } catch (UnsupportedOperationException u) {
                Runtime.getRuntime().freeMemory();
            } catch (Throwable t) {
                System.out.println("Incorrect stub on subList");
            }
            try {
                r.remove(0);
            } catch (UnsupportedOperationException u) {
                Runtime.getRuntime().freeMemory();
            } catch (Throwable t) {
                System.out.println("Incorrect stub on remove(int)");
            }
        } catch (Throwable t) {
            System.out.println("Error checking for incorrect stubbing");
        }
        part2();

        System.out.println("Reached end of tests (if no errors printed, "
            + "you passed)");
    }

    /**
     * doif. part1.
     */
    private static void part1(Class clazz) {
        Class[] interfaces = clazz.getInterfaces();
        boolean found = false;
        for (Class c : interfaces) {
            if (c.equals(List.class)) {
                found = true;
            }
        }
        if (!found) {
            System.out.println("FATAL: RecursiveLinkedList does not "
                + "implement List");
            System.exit(1);
        }
        try {
            Constructor c = clazz.getConstructor();
            c.newInstance();
        } catch (NoSuchMethodException | SecurityException e) {
            System.out.println("Missing no args constructor");
        } catch (Throwable t) {
            System.out.println("Exception invoking no args constructor");
        }
        try {
            Field f = clazz.getDeclaredField("head");
        } catch (NoSuchFieldException e) {
            System.out.println("Missing head");
        } catch (Throwable t) {
            System.out.println("Error checking for head");
        }
    }

    /**
     * checkstyle can stop
     */
    private static void part2() {
        RecursiveLinkedList<String> r = new RecursiveLinkedList<>();
        try {
            r.iterator().next();
            System.out.println("Expected NoSuchElementException in the "
                + "iterator when calling next() when list is empty");
        } catch (NoSuchElementException cce) {
            // 'must have at least one statement' can heck itself
            Runtime.getRuntime().freeMemory();
        } catch (Throwable t) {
            System.out.println("Expected NoSuchElementException in the "
                + "iterator when calling next() when list is empty, got "
                + t.getClass().getSimpleName());
            t.printStackTrace();
            System.out.println();
        }
        try {
            r.get(1);
            System.out.println("Expected IndexOutOfBoundsException in get"
                + " getting index 1 for size 0");
        } catch (IndexOutOfBoundsException cce) {
            // 'must have at least one statement' can heck itself
            Runtime.getRuntime().freeMemory();
        } catch (UnsupportedOperationException u) {
            // 'must have at least one statement' can heck itself
            Runtime.getRuntime().freeMemory();
        } catch (Throwable t) {
            System.out.println("Expected IndexOutOfBoundsException in get"
                + " getting index 1 for size 0, got "
                + t.getClass().getSimpleName());
            t.printStackTrace();
            System.out.println();
        }
        try {
            r.get(0);
            System.out.println("Expected IndexOutOfBoundsException in get "
                + "getting index 0 for size 0");
        } catch (IndexOutOfBoundsException cce) {
            // 'must have at least one statement' can heck itself
            Runtime.getRuntime().freeMemory();
        } catch (UnsupportedOperationException u) {
            // 'must have at least one statement' can heck itself
            Runtime.getRuntime().freeMemory();
        } catch (Throwable t) {
            System.out.println("Expected IndexOutOfBoundsException in get"
                + " getting index 0 for size 0, got "
                + t.getClass().getSimpleName());
            t.printStackTrace();
            System.out.println();
        }
        try {
            r.toArray(null);
            System.out.println("Expected NullPointerException in toArray(T[])"
                + " for null argument");
        } catch (NullPointerException cce) {
            if (cce.getMessage() != null && cce.getMessage().equals("")) {
                System.out.println("Missing exception message in "
                    + "NullPointerException in toArray(T[])");
            }
        } catch (UnsupportedOperationException u) {
            // 'must have at least one statement' can heck itself
            Runtime.getRuntime().freeMemory();
        } catch (Throwable t) {
            System.out.println("Expected NullPointerException in toArray(T[])"
                + " for null argument, got " + t.getClass().getSimpleName());
            t.printStackTrace();
            System.out.println();
        }
        try {
            System.out.println("[NOTE] Adding element to test toArray;add"
                + " must work for this test to work");
            r.add("Hallo");
            Object[] arr = new Integer[12];
            r.toArray(arr);
            System.out.println("Expected ArrayStoreException in toArray(T[])"
                + " for incompatible array type");
            System.out.println("List is of type string, array given has static"
                + " type Object[] and dynamic type Integer[]");
        } catch (ArrayStoreException | UnsupportedOperationException cce) {
            Runtime.getRuntime().freeMemory();
        } catch (Throwable t) {
            System.out.println("Expected ArrayStoreException in toArray(T[]) "
                + "for incompatible array type, got "
                + t.getClass().getSimpleName());
            System.out.println("List is of type string, array given has static"
                + " type Object[] and dynamic type Integer[]");
            t.printStackTrace();
            System.out.println();
        }
        r = new RecursiveLinkedList<>();
        try {
            r.retainAll(null);
            System.out.println("Expected NullPointerException in retainAll "
                + "for null argument");
        } catch (NullPointerException cce) {
            if (cce.getMessage() != null && cce.getMessage().equals("")) {
                System.out.println("Missing exception message in "
                    + "NullPointerException in retainAll");
            }
        } catch (UnsupportedOperationException u) {
            Runtime.getRuntime().freeMemory();
        } catch (Throwable t) {
            System.out.println("Expected NullPointerException in retainAll "
                + "for null argument, got " + t.getClass().getSimpleName());
            t.printStackTrace();
            System.out.println();
        }
        try {
            r.addAll(0, null);
            System.out.println("Expected NullPointerException in addAll(int,"
                + " Collection) for null argument");
        } catch (NullPointerException cce) {
            if (cce.getMessage() != null && cce.getMessage().equals("")) {
                System.out.println("Missing exception message in NullPointer"
                    + "Exception in addAll(int, Collection)");
            }
        } catch (UnsupportedOperationException u) {
            Runtime.getRuntime().freeMemory();
        } catch (Throwable t) {
            System.out.println("Expected NullPointerException in addAll(int,"
                + " Collection) for null argument, got "
                + t.getClass().getSimpleName());
            t.printStackTrace();
            System.out.println();
        }
        try {
            r.addAll(1, new LinkedList<String>());
            System.out.println("Expected IndexOutOfBoundsException in addAll"
                + "(int, Collection) for index 1 in size 0");
        } catch (IndexOutOfBoundsException cce) {
            Runtime.getRuntime().freeMemory();
        } catch (UnsupportedOperationException u) {
            Runtime.getRuntime().freeMemory();
        } catch (Throwable t) {
            System.out.println("Expected IndexOutOfBoundsException in addAll"
                + "(int, Collection) for index 1 in size 0, got "
                + t.getClass().getSimpleName());
            t.printStackTrace();
            System.out.println();
        }
        try {
            r.addAll(0, new LinkedList<String>());
        } catch (Throwable t) {
            System.out.println("Should be able to add at index 0 in size 0 "
                + "list (this is just adding to the end), got "
                + t.getClass().getSimpleName());
            t.printStackTrace();
            System.out.println();
        }
    }

}