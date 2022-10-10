/**
 * LinkedListNode for CS1331 homework 9. Do not modify this file!
 * @author 1331 TAs
 * @version FINAL 1.0
 * @param <E> Generic type.
 */
public class LinkedListNode<E> {

    private E data;
    private LinkedListNode<E> prev;
    private LinkedListNode<E> next;

    /**
     * Create a LinkedListNode with the given data and null next & prev.
     * @param e The element this node contains.
     */
    public LinkedListNode(E e) {
        this(e, null, null);
    }

    /**
     * Create a LinkedListNode with the given data, prev and next pointer.
     * @param e The element this node contains.
     * @param p The pointer to the previous node in the list.
     * @param n The pointer to the next node in the list.
     */
    public LinkedListNode(E e, LinkedListNode<E> p, LinkedListNode<E> n) {
        data = e;
        prev = p;
        next = n;
    }

    /**
     * Set the data of this node.
     * @param e The element to set to.
     */
    public void setData(E e) {
        data = e;
    }

    /**
     * Get the data of this node.
     * @return The element this node stores.
     */
    public E getData() {
        return data;
    }

    /**
     * Set the node after this node in the list.
     * @param n The node after this node.
     */
    public void setNext(LinkedListNode<E> n) {
        this.next = n;
    }

    /**
     * Get the next node in the list.
     * @return The next node in the list.
     */
    public LinkedListNode<E> getNext() {
        return next;
    }

    /**
     * Set the node before this node in the list.
     * @param p The node before this node.
     */
    public void setPrev(LinkedListNode<E> p) {
        this.prev = p;
    }

    /**
     * Get the previous node in the list.
     * @return The previous node in the list.
     */
    public LinkedListNode<E> getPrev() {
        return prev;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof LinkedListNode) && (data != null
            ? data.equals(((LinkedListNode)o).getData())
            : data == ((LinkedListNode)o).getData());
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : "null";
    }

}
