package by.teachmeskills.hw_07072023;

public class Run {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        myLinkedList.addLast(5);
        myLinkedList.addLast(6);
        myLinkedList.addFirst(0);
        myLinkedList.addFirst(4);
        System.out.println(myLinkedList);
        myLinkedList.remove(0);
        System.out.println(myLinkedList);
    }
}
