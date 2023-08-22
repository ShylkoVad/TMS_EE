package by.teachmeskills.hw_07072023;

import java.util.NoSuchElementException;

public class MyLinkedList<E> {
    private Node<E> header;
    private Node<E> last;
    private int size;

    private static class Node<E> {
        Node<E> prev;
        E value;
        Node<E> next;

        Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(E element) {
        if (isEmpty())
            header = new Node<>(null, element, null);
        else {
            Node<E> temp = header;
            header = new Node<>(null, element, temp);
            header.next.prev = header;
        }
        size++;
    }

    public void addLast(E element) {
        if (isEmpty())
            header = new Node<>(null, element, null);
        else {
            Node<E> temp = header;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node<>(temp, element, null);
        }
        size++;
    }

    public void addBefore(E elementUp, E element) {
        if (isEmpty())
            throw new NoSuchElementException("Element " + elementUp.toString() + " not found.");
        Node<E> currentNode = header;
        while (currentNode != null && !currentNode.value.equals(elementUp))
            currentNode = currentNode.next;
        if (currentNode == null)
            throw new NoSuchElementException("Element " + elementUp.toString() + " not found.");
        Node<E> newNode = new Node<>(currentNode.prev, element, currentNode);
        if (currentNode.prev != null) {
            currentNode.prev.next = newNode;
        } else {
            header = newNode;
            currentNode.prev = newNode;
        }
        size++;
    }

    public void addAfter(E elementAfter, E element) {
        if (isEmpty())
            throw new NoSuchElementException("Element " + elementAfter.toString() + " not found.");
        Node<E> currentNode = header;
        while (currentNode != null && !currentNode.value.equals(elementAfter))
            currentNode = currentNode.next;
        if (currentNode == null)
            throw new NoSuchElementException("Element " + elementAfter.toString() + " not found.");
        Node<E> newNode = new Node<>(currentNode, element, currentNode.next);
        if (currentNode.next != null)
            currentNode.next.prev = newNode;
        currentNode.next = newNode;
        size++;
    }

    public void remove(E element) {
        if (isEmpty())
            throw new NoSuchElementException("Element " + element.toString() + " not found");
        if (header.value.equals(element)) {
            header = header.next;
            return;
        }

        Node<E> currentNode = header;
        while (currentNode != null && !currentNode.value.equals(element))
            currentNode = currentNode.next;
        if (currentNode == null)
            throw new NoSuchElementException("Element " + element.toString() + " not found.");
        if (currentNode.next != null)
            currentNode.next.prev = currentNode.prev;

        currentNode.prev.next = currentNode.next;
        size--;
    }

    public Node<E> getIndex(int index) {
        Node<E> find;
        if (index < (size >> 1)) {
            find = header;
            for (int i = 0; i < index; i++)
                find = find.next;
        } else {
            find = last;
            for (int i = size - 1; i > index; i--)
                find = find.prev;
        }
        return find;
    }

    public E getFirst() {
        return header.value;
    }

    public E getLast() {
        return last.value;
    }

    public boolean contains(E element) {
        Node<E> temp = header;
        boolean returnable = false;
        while (temp != null) {
            if (element.equals(temp.value)) {
                returnable = true;
                break;
            }
            temp = temp.next;
        }
        return returnable;
    }

    public void clear() {
        header = null;
        last = null;
    }

    @Override
    public String toString() {
        Node<E> temp = header;
        StringBuilder builder = new StringBuilder("{");

        while (temp != null) {
            builder.append(temp.value).append(",");
            temp = temp.next;
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");
        return builder.toString();
    }
}
