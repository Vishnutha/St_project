package com.example.st_project.st;

import java.util.ArrayList;

public class LinkedList<I extends Number> {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;

    public void insert(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public ArrayList<Integer> display() {
        Node current = head;
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (current != null) {
//            System.out.print(current.data + " ");
            arrayList.add(current.data);
            current = current.next;
        }
        return arrayList;
    }

//    public static void main(String[] args) {
//        LinkedList list = new LinkedList();
//        list.insert(1);
//        list.insert(2);
//        list.insert(3);
//        list.display();
//    }
}
