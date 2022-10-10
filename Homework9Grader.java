import java.lang.reflect.*;
import java.util.*;

public class Homework9Grader {

    // currently just verifies that this works the same as a normal LinkedList
    public static void main(String[] args) {
        bigboye();

        LinkedList<String> l = new LinkedList<>();
        RecursiveLinkedList<String> rll = new RecursiveLinkedList<>();

        boolean gotCCE = false;
        boolean eq = true;
        try {
            eq = rll.equals("Yeet");
        } catch (ClassCastException cce) {
            gotCCE = true;
        }
        System.out.println("No ClassCastException with bad type in equals: " + !gotCCE);
        System.out.println("\tExpected: true");
        System.out.println("Not equals to bad type: " + !eq);
        System.out.println("\tExpected: true");

        l.add("Hello!");
        rll.add("Hello!");
        System.out.println("Add 1 equals: " + (rll.equals(l)));
        System.out.println("\tExpected: true");
        System.out.println("Add 1 size: " + (rll.size() == l.size()));
        System.out.println("\tExpected: true");
        l.add("General Kenobi!");
        rll.add("General Kenobi!");
        System.out.println("Add 2 equals: " + (rll.equals(l)));
        System.out.println("\tExpected: true");
        System.out.println("Add 2 size: " + (rll.size() == l.size()));
        System.out.println("\tExpected: true");
        l.add("A");
        rll.add("B");
        System.out.println("Add 3 nequals: " + (!rll.equals(l)));
        System.out.println("\tExpected: true");
        System.out.println("Add 3 size: " + (rll.size() == l.size()));
        System.out.println("\tExpected: true");
        rll.clear();
        l.clear();

        System.out.println();

        System.out.println("Clear equals: " + (rll.equals(l)));
        System.out.println("\tExpected: true");
        System.out.println("Clear size: " + (rll.size() == l.size()));
        System.out.println("\tExpected: true");
        l.add("A");
        l.add("B");
        rll.add("B");
        rll.add("A");
        System.out.println("Content mis-order nequals: " + (!rll.equals(l)));
        System.out.println("\tExpected: true");
        System.out.println("Content mis-order size: " + (rll.size() == l.size()));
        System.out.println("\tExpected: true");
        l.remove("B");
        rll.remove("B");
        System.out.println("Remove head equals: " + (rll.equals(l)));
        System.out.println("\tExpected: true");
        System.out.println("Remove head size: " + (rll.size() == l.size()));
        System.out.println("\tExpected: true");
        l.clear();
        rll.clear();
        l.add("A");
        l.add("B");
        rll.add("B");
        rll.add("A");
        l.remove("A");
        rll.remove("A");
        System.out.println("Remove tail equals: " + (rll.equals(l)));
        System.out.println("\tExpected: true");
        System.out.println("Remove tail size: " + (rll.size() == l.size()));
        System.out.println("\tExpected: true");

        System.out.println();

        rll.clear();
        l.clear();
        rll.add("A");
        rll.add("B");
        rll.add("C");
        rll.add("D");
        rll.add("E");
        System.out.println("Start:\t\t" + rll);
        boolean remExistant = rll.remove("C");
        System.out.println("Remove C:\t" + rll);
        System.out.println("\tExpected: [A, B, D, E]");
        System.out.println("Ret true: " + remExistant);
        System.out.println("\tExpected: true");
        rll.remove("A");
        System.out.println("Remove A:\t" + rll);
        System.out.println("\tExpected: [B, D, E]");
        rll.remove("B");
        System.out.println("Remove B:\t" + rll);
        System.out.println("\tExpected: [D, E]");
        boolean remNonexistant = rll.remove("B");
        System.out.println("Remove B:\t" + rll);
        System.out.println("\tExpected: [D, E]");
        System.out.println("Ret false: " + !remNonexistant);
        System.out.println("\tExpected: true");
        rll.remove("D");
        System.out.println("Remove D:\t" + rll);
        System.out.println("\tExpected: [E]");
        rll.remove("E");
        System.out.println("Remove E:\t" + rll);
        System.out.println("\tExpected: []");
        boolean remEmpty = rll.remove("E");
        System.out.println("Remove E:\t" + rll);
        System.out.println("\tExpected: []");
        System.out.println("Ret false: " + !remEmpty);
        System.out.println("\tExpected: true");

        System.out.println();

        rll.add("A");
        rll.add("C");
        l.add("A");
        l.add("C");
        System.out.println("Hash [A, C]: " + (l.hashCode() == rll.hashCode()));
        System.out.println("\tExpected: true");
        rll.clear();
        l.clear();
        System.out.println("Hash []: " + (l.hashCode() == rll.hashCode()));
        System.out.println("\tExpected: true");

        System.out.println();

        rll.add("A");
        rll.add("B");
        rll.add("C");
        rll.add("E");
        System.out.println(rll);
        System.out.println("Index of A: " + (rll.indexOf("A") == 0));
        System.out.println("\tExpected: true");
        System.out.println("Index of B: " + (rll.indexOf("B") == 1));
        System.out.println("\tExpected: true");
        System.out.println("Index of C: " + (rll.indexOf("C") == 2));
        System.out.println("\tExpected: true");
        System.out.println("Index of E: " + (rll.indexOf("E") == 3));
        System.out.println("\tExpected: true");
        System.out.println("Index of D: " + (rll.indexOf("D") == -1));
        System.out.println("\tExpected: true");

        System.out.println();

        String s = "";
        Iterator<String> i = rll.iterator();
        while (i.hasNext()) {
            s += i.next() + " ";
        }
        boolean nsee = false;
        try {
            i.next();
        } catch (NoSuchElementException e) {
            nsee = true;
        }
        System.out.println("Exception iterating past list: " + nsee);
        System.out.println("\tExpected: true");
        System.out.println("Iteration result: " + (s.equals("A B C E ")));
        System.out.println("\tExpected: true");

        System.out.println();

        System.out.println("Get(0): " + rll.get(0).equals("A"));
        System.out.println("\tExpected: true");
        System.out.println("Get(1): " + rll.get(1).equals("B"));
        System.out.println("\tExpected: true");
        System.out.println("Get(2): " + rll.get(2).equals("C"));
        System.out.println("\tExpected: true");
        System.out.println("Get(3): " + rll.get(3).equals("E"));
        System.out.println("\tExpected: true");

        boolean ioobeGet = false;
        try {
            rll.get(4);
        } catch (IndexOutOfBoundsException ioobe) {
            ioobeGet = true;
        }
        System.out.println("Get(4): " + ioobeGet);
        System.out.println("\tExpected: true");

        System.out.println();

        System.out.println("ToArray(): " + Arrays.equals(new String[]{"A", "B", "C", "E"}, rll.toArray()));
        System.out.println("\tExpected: true");

        System.out.println();

        System.out.println("Contains(A): " + rll.contains("A"));
        System.out.println("\tExpected: true");
        System.out.println("Contains(B): " + rll.contains("B"));
        System.out.println("\tExpected: true");
        System.out.println("Contains(C): " + rll.contains("C"));
        System.out.println("\tExpected: true");
        System.out.println("!Contains(D): " + !rll.contains("D"));
        System.out.println("\tExpected: true");
        System.out.println("Contains(E): " + rll.contains("E"));
        System.out.println("\tExpected: true");

        System.out.println();

        l.add("A");
        l.add("B");
        l.add("C");
        l.add("E");
        LinkedList<String> l2 = new LinkedList<>();
        l2.add("D1");
        l2.add("D2");
        l.addAll(3, l2);
        rll.addAll(3, l2);
        System.out.println("AddAll(3, [D1, D2]): " + rll.equals(l));
        System.out.println("\tExpected: true");
        System.out.println("AddAll(3, [D1, D2]) size: " + (rll.size() == l.size()));
        System.out.println("\tExpected: true");
        System.out.println("\tCorrect:" + l);
        System.out.println("\tGot:\t" + rll);

        System.out.println();

        l.addAll(0, l2);
        rll.addAll(0, l2);
        System.out.println("AddAll(0, [D1, D2]): " + rll.equals(l));
        System.out.println("\tExpected: true");
        System.out.println("AddAll(0, [D1, D2]) size: " + (rll.size() == l.size()));
        System.out.println("\tExpected: true");
        System.out.println("\tCorrect:" + l);
        System.out.println("\tGot:\t" + rll);

        System.out.println();

        l.addAll(l.size(), l2);
        rll.addAll(rll.size(), l2);
        System.out.println("AddAll(size(), [D1, D2]): " + rll.equals(l));
        System.out.println("\tExpected: true");
        System.out.println("AddAll(size(), [D1, D2]) size: " + (rll.size() == l.size()));
        System.out.println("\tExpected: true");
        System.out.println("\tCorrect:" + l);
        System.out.println("\tGot:\t" + rll);
        rll.clear();
        l.clear();

        System.out.println();

        rll.add("A");
        rll.add("B");
        rll.add("C");
        Integer[] ia = new Integer[3];
        boolean gotAse = false;
        try {
            rll.toArray(ia);
        } catch (ArrayStoreException ase) {
            gotAse = true;
        }
        System.out.println("ToArray(Integer[]) ArrayStoreException: " + gotAse);
        System.out.println("\tExpected: true");

        Object[] o = new Object[0];
        o = rll.toArray(o);
        System.out.println("ToArray(Object[0]): " + Arrays.equals(o, new String[]{"A", "B", "C"}));
        System.out.println("\tExpected: true");

        o = new Object[3];
        Object[] o2 = rll.toArray(o);
        System.out.println("ToArray(Object[3]): " + Arrays.equals(o, new String[]{"A", "B", "C"}));
        System.out.println("\tExpected: true");
        System.out.println("ToArray(Object[3]) !new: " + (o == o2));
        System.out.println("\tExpected: true");

        o = new Object[4];
        o[3] = "Hallo";
        o2 = rll.toArray(o);
        System.out.println("ToArray(Object[4]): " + Arrays.equals(o, new String[]{"A", "B", "C", null}));
        System.out.println("\tExpected: true");
        System.out.println("ToArray(Object[4])[3] == null: " + (o[3] == null));
        System.out.println("\tExpected: true");
        System.out.println("ToArray(Object[4]) !new: " + (o == o2));
        System.out.println("\tExpected: true");

        o = new Object[5];
        o[3] = "D";
        o[4] = "E";
        o2 = rll.toArray(o);
        System.out.println("ToArray(Object[5]): " + Arrays.equals(o, new String[]{"A", "B", "C", null, "E"}));
        System.out.println("\tExpected: true");
        System.out.println("ToArray(Object[5])[3] == null: " + (o[3] == null));
        System.out.println("\tExpected: true");
        System.out.println("ToArray(Object[5])[4] unchanged: " + (o[4].equals("E")));
        System.out.println("\tExpected: true");
        System.out.println("ToArray(Object[5]) !new: " + (o == o2));
        System.out.println("\tExpected: true");

        System.out.println();

        l.clear();
        rll.clear();
        rll.add("A");
        rll.add("B");
        rll.add("A");
        rll.add("C");

        System.out.println("List:" + rll);
        System.out.println("LastIndexOf(A)==2: " + (rll.lastIndexOf("A") == 2));
        System.out.println("\tExpected: true");
        System.out.println("LastIndexOf(B)==1: " + (rll.lastIndexOf("B") == 1));
        System.out.println("\tExpected: true");
        System.out.println("LastIndexOf(C)==3: " + (rll.lastIndexOf("C") == 3));
        System.out.println("\tExpected: true");
        System.out.println("LastIndexOf(D)==-1: " + (rll.lastIndexOf("D") == -1));
        System.out.println("\tExpected: true");

        System.out.println();

        l.add("A");
        l.add("B");
        l.add("A");
        l.add("C");
        l2 = new LinkedList<>();
        l2.add("B");
        l2.add("C");
        boolean retAll = rll.retainAll(l2);
        l.retainAll(l2);
        System.out.println("retainAll([B,C]): " + rll.equals(l));
        System.out.println("\tExpected: true");
        System.out.println("ret: " + retAll);
        System.out.println("\tExpected: true");

        boolean retAll2 = rll.retainAll(l2);
        retAll2 = l.retainAll(l2);
        System.out.println("retainAll([B,C]): " + rll.equals(l));
        System.out.println("\tExpected: true");
        System.out.println("!ret: " + !retAll2);
        System.out.println("\tExpected: true");

        l2.clear();
        l2.add("D");
        boolean retAll3 = rll.retainAll(l2);
        l.retainAll(l2);
        System.out.println("retainAll([D]): " + rll.equals(l));
        System.out.println("\tExpected: true");
        System.out.println("ret: " + retAll3);
        System.out.println("\tExpected: true");
        System.out.println("size == 0: " + (rll.size() == 0));
        System.out.println("\tExpected: true");
        System.out.println();
        System.out.println("**You'll probably need to scroll up to see the start of your test cases.**");
        System.out.println();
        bigboye();
    }

    public static void bigboye() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("********************************************************************************");
        System.out.println("*******************************HW9 MODIFIED AUTOGRADER**************************");
        System.out.println("********************************************************************************");
        System.out.println("** TO RUN THIS TESTER YOU WILL NEED EQUALS & TOSTRING");
        System.out.println("** THIS TESTER DOES NOT TEST THE FOLLOWING RUBRIC ITEMS:");
        System.out.println("\t** DOING THINGS WITHOUT LOOPS");
        System.out.println("\t** WHETHER OR NOT LOOPS WERE USED INSTEAD OF RECURSION");
        System.out.println("********************************************************************************");
        System.out.println("WE RESERVE THE RIGHT TO ADD, REMOVE, OR OTHERWISE CHANGE OUR TEST CASES UESD");
        System.out.println("WHEN DETERMINING YOUR GRADE WITHOUT NOTIFYING YOU. CHANGES TO THIS TESETER");
        System.out.println("WILL BE REPORTED IN AN ANNOUNCEMENT. IF YOU NOTICE ANY BUGS, POST ON PIAZZA");
        System.out.println("********************************************************************************");
        System.out.println("********************************************************************************");
        System.out.println("********************************************************************************");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}