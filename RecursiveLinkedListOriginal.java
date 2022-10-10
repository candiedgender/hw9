import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Recursive doubly-linked list for CS1331 2018 Summer HW9.
 * @author cproenza3
 * @version 1
 * @param <E> Generic type parameter.
 */
public class RecursiveLinkedList<E> implements List<E> {

    /**
     * Do not delete this field! You must use this as your LinkedList head.
     */
    private LinkedListNode<E> head;
    private int size;

    /**
     * No-args constructor. You may add code to this, add other constructors,
     * or just leave this empty, but you may not delete this constructor
     * entirely.
     */
    public RecursiveLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public boolean add(E e) {
        return addHelper(e, head);
    }

    /**
     * Recursive helper method for add
     * @param o is the object being added
     * @param curr is the current element in the linked list
     */
    private boolean addHelper(E e, LinkedListNode<E> curr) {
        if (head == null) {
            head = new LinkedListNode<>(e);
            size++;
            return true;
        } else if (curr.getNext() == null) {
            curr.setNext(new LinkedListNode<>(e, curr, null));
            size++;
            return true;
        }
        return addHelper(e, curr.getNext());
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        return containsHelper(o, head);
    }

    /**
     * Recursive helper method for contains
     * @param o is the object being checked
     * @param curr is the current element in the linked list
     */
    private boolean containsHelper(Object o, LinkedListNode<E> curr) {
        if (curr == null) {
            return false;
        } else if (curr.getData().equals(o)) {
            return true;
        }
        return containsHelper(o, curr.getNext());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof List)) {
            return false;
        } else if (size != ((List) o).size()) {
            return false;
        }

        List oList = (List) o;
        if (oList.size() == 0 & this.size() == 0) {
            return true;
        } else if (this == o) {
            return true;
        }
        Iterator i = oList.iterator();
        return equalsHelper(head, i);
    }

    private boolean equalsHelper(LinkedListNode<E> curr, Iterator i) {
        if (i.hasNext() && curr.getData().equals(i.next())) {
            if (curr.getNext() == null) {
                return true;
            }
            return equalsHelper(curr.getNext(), i);
        } else if (i.hasNext() && !curr.getData().equals(i.next())) {
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(head, "[");
    }

    private String toStringHelper(LinkedListNode<E> curr, String s) {
        if (head == null) {
            return "[]";
        } else if (curr.getNext() != null) {
            return toStringHelper(curr.getNext(), s + curr.toString() + ", ");
        }
        return s + curr.toString() + "]";
    }

    @Override
    public int hashCode() {
        return hashHelper(head, 1);
    }

    /**
     * Recursive helper for the hashCode method
     * @param curr is the current node
     * @param hash is the integer hashcode
    */
    private int hashHelper(LinkedListNode<E> curr, int hash) {
        if (head == null) {
            return hash;
        }
        if (curr.getNext() == null) {
            hash = 31 * hash + (int) curr.getData().hashCode();
            return hash;
        }
        hash = 31 * hash + (int) curr.getData().hashCode();
        return hashHelper(curr.getNext(), hash);
    }

    @Override
    public int indexOf(Object o) {
        return indexOfHelper(o, head, 0);
    }

    /**
     * Recursive helper for the indexOf method
     * @param o is the object being searched for
     * @param curr is the current element in the list
     * @param count is the index being returned
    */
    private int indexOfHelper(Object o, LinkedListNode<E> curr, int count) {
        if (!contains(o)) {
            return -1;
        } else if (curr.getData() == o || curr.getData().equals(o)) {
            return count;
        }
        return indexOfHelper(o, curr.getNext(), count + 1);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    /**
     * The inner class for the iterator
    */
    private class MyIterator implements Iterator<E> {

        private LinkedListNode<E> genericNode;
        private int index = 0;

        /**
         * Constructor for MyIterator
        */
        public MyIterator() {
            this.genericNode = head;
        }

        @Override
        public boolean hasNext() {
            if (genericNode.getNext() == null) {
                return false;
            }
            return index < size || genericNode.getNext() != null;
        }

        @Override
        public E next() {
            if (index == 0) {
                index++;
                return head.getData();
            }
            if (!(hasNext())) {
                throw new NoSuchElementException("End of linked list.");
            }
            E nodeData = genericNode.getNext().getData();
            genericNode = genericNode.getNext();
            index++;
            return nodeData;
        }
    }

    @Override
    public E get(int index) {
        if (index >= size || size == 0) {
            throw new IndexOutOfBoundsException("Index " + index
                + " is out of bounds for this list.");
        }
        return getHelper(index, 0, head);
    }

    private E getHelper(int index, int count, LinkedListNode<E> curr) {
        // if (head == null) {
        //     return ;
        // }
        if (count == index) {
            return curr.getData();
        }
        return getHelper(index, count + 1, curr.getNext());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(Object o) {
        return removeHelper(o, head);
    }

    /**
     * Recursive helper method for remove
     * @param o is the object being removed
     * @param curr is the current element in the linked list
     */
    private boolean removeHelper(Object o, LinkedListNode<E> curr) {
        if (curr == null) {
            return false;
        } else if (curr.getData().equals(o)) {
            if (curr == head) {
                if (curr.getNext() == null) {
                    head = null;
                } else {
                    head = curr.getNext();
                    head.setPrev(null);
                }
            } else {
                if (curr.getPrev() != null) {
                    curr.getPrev().setNext(curr.getNext());
                }
                if (curr.getNext() != null) {
                    curr.getNext().setPrev(curr.getPrev());
                }
            }
            size--;
            return true;
        }
        return removeHelper(o, curr.getNext());
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        return toArrayHelper(head, 0, arr);
    }

    /**
     * Recursive helper method for toArray method
     * @param curr is the current element
     * @param index is the index of the array
     * @param arr is the array
    */
    private Object[] toArrayHelper(LinkedListNode<E> curr, int index,
        Object[] arr) {
        if (head == null) {
            return arr;
        } else if (curr.getNext() != null) {
            arr[index] = curr.getData();
            return toArrayHelper(curr.getNext(), index + 1, arr);
        }
        arr[index] = curr.getData();
        return arr;
    }

    //LOOPLINE: EVERYTHING BELOW THIS LINE USES LOOPS

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Collection " + c + " is null.");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Index " + index
                + " is out of bounds for this list.");
        }

        LinkedListNode<E> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
            if (size == index && i == index - 2) {
                i = index;
            }
        }

        for (E e : c) {
            LinkedListNode<E> nodey = new LinkedListNode<>(e);
            size++;
            index++;

            if (curr != null && curr.getNext() == null && size == index) {
                curr.setNext(nodey);
                nodey.setPrev(curr);
                curr = curr.getNext();
            } else if (curr != null && curr.getPrev() != null) {
                nodey.setNext(curr);
                nodey.setPrev(curr.getPrev());
                curr.setPrev(nodey);
                nodey.getPrev().setNext(nodey);
            } else if (curr != null && curr.getPrev() == null) {
                curr.setPrev(nodey);
                nodey.setNext(curr);
                head = nodey;
            }
        }
        return true;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Null Pointer Exception. The "
                +  "array " + a + " is null.");
        }
        return a;
    }

    /**
     * Recursive helper method for toArray method that takes in an array
     * @param curr is the current node
     * @param index is the index of the array
     * @param a is the array
    */
    private <T> T[] toArrayElp(LinkedListNode<E> curr, int index, T[] a) {
        if (head == null) {
            return a;
        } else if (curr.getNext() != null) {
            a[index] = (T) curr.getData();
            return toArrayElp(curr.getNext(), index + 1, a);
        }
        a[index] = (T) curr.getData();
        return a;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (!(contains(o))) {
            return -1;
        }
        return lastIndexOfHelper(o, head, 0, 0);
    }

    /**
     * Recursive helper method for lastIndexOf method
     * @param o is the element being searched for
     * @param curr is the current node
     * @param track is an index where the element is found
     * @param count is the index of the linked list
    */
    private int lastIndexOfHelper(Object o, LinkedListNode<E> curr, int track,
        int count) {
        if (curr == null) {
            return track;
        }
        if (curr.getData().equals(o)) {
            return lastIndexOfHelper(o, curr.getNext(), count, count + 1);
        }
        return lastIndexOfHelper(o, curr.getNext(), track, count + 1);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("The collection " + c
                + " you have passed in is null.");
        }
        return retainAllHelper(c, head, false);
    }

    private boolean retainAllHelper(Collection c, LinkedListNode<E> curr,
        boolean removed) {
        if (curr.getNext() == null) {
            if (!c.contains(curr.getData())) {
                remove(curr.getData());
                removed = true;
                return removed;
            }
            return removed;
        }
        if (!c.contains(curr.getData())) {
            remove(curr.getData());
            removed = true;
            return retainAllHelper(c, curr.getNext(), removed);
        }
        return retainAllHelper(c, curr.getNext(), removed);
    }


    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Unsupported Operation "
            + "Exception");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Unsupported Operation "
            + "Exception");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported Operation "
            + "Exception");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Unsupported Operation "
            + "Exception");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported Operation "
            + "Exception");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Unsupported Operation "
            + "Exception");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Unsupported Operation "
            + "Exception");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Unsupported Operation "
            + "Exception");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Unsupported Operation "
            + "Exception");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Unsupported Operation "
            + "Exception");
    }
}