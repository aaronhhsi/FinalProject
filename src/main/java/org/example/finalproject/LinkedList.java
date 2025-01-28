package org.example.finalproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class to represent a linked list of nodes. The list is Iterable to indicate
 that we can iterate over the data in the list.
 */
public class LinkedList<T> implements Iterable<T> {
    /** the first node of the list, or null if the list is empty */
    private LLNode<T> firstNode;
    /** the last node of the list */
    private LLNode<T> lastNode = firstNode;
    /**
     * Creates an initially empty linked list
     */
    public LinkedList() {
        firstNode = null;
    }
    /**
     * Returns the first node.
     */
    protected LLNode<T> getFirstNode() {
        return firstNode;
    }
    /**
     * Changes the front node.
     * @param node the node that will be the first node of the new linked list
     */
    protected void setFirstNode(LLNode<T> node) {
        this.firstNode = node;
    }
    /**
     * Add an element to the front of the linked list
     */
    public void addToFront(T element) {
        setFirstNode(new LLNode<T>(element, getFirstNode()));
    }
    /**
     * Return whether the list is empty
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return (getFirstNode() == null);
    }
    /**
     * Returns the length of the linked list
     * @return the number of nodes in the list
     */
    public int length() {
        int lengthSoFar = 0;
        LLNode<T> nodeptr = getFirstNode();
        while (nodeptr != null) {
            lengthSoFar++;
            nodeptr = nodeptr.getNext();
        }
        return lengthSoFar;
    }
    /**
     * Stores a value in the linked list in the proper place in its "natural order".
     * The values in the list should already be stored in non-decreasing order,
     according to the "natural order"
     * of the type stored in the list.
     * @param element the element to insert
     * @param list the list to insert the element into
     */
    public static <S extends Comparable<? super S>> void insertInOrder(S element,
                                                                       LinkedList<S> list) {
        if (list.isEmpty())
            list.addToFront(element);
        else {
            LLNode<S> nodeptr = list.getFirstNode();
            while (nodeptr.getNext() != null &&
                    nodeptr.getNext().getElement().compareTo(element) < 0)
                nodeptr = nodeptr.getNext();
            nodeptr.setNext(new LLNode<S>(element, nodeptr.getNext()));
        }
    }

    /**
     * Returns an ArrayList that contains the same elements of the linked list and in the same order
     * @return an ArrayList that contains the same elements of the linked list and in the same order
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        LLNode<T> nodeptr = this.getFirstNode();
        while (nodeptr != null) {
            list.add(nodeptr.getElement());
            nodeptr = nodeptr.getNext();
        }
        return list;
    }

    /**
     * Sets the values in the linked list to the inputted array list
     * @param list the inputted array list
     */
    public void setArray(T[] list) {
        if (list != null) {
            for (int i = list.length - 1; i >= 0; i--) {
                addToFront(list[i]);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private LLNode<T> nodeptr = getFirstNode();
            @Override
            public boolean hasNext() {
                return nodeptr != null;
            }

            @Override
            public T next() {
                if (nodeptr == null)
                    throw new NoSuchElementException();
                T save = nodeptr.getElement();
                nodeptr = nodeptr.getNext();
                return save;
            }
        };
    }
}