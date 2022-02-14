import java.util.NoSuchElementException;

public class Queue<E>{
    public Node head;
    public Node tail;
    private int size;

    //queue method that adds a new element to the  end of the Queue (Linked List).
    //This is identical to addLastNode in the Linked List.
    public void enqueue(E e){
        size++;
        if (tail == null)
            head = tail = new Node(e); //empty list
        else
        {
            tail.next = new Node(e); //link new node as last node
            tail = tail.next; //make tail pointer points to last node
        }
    }

    //removes the first Element and returns it back.
    public E dequeue(){
        if(head==null){ //Check to see that there actually is a Node to remove.
            throw new NoSuchElementException();
        }else if (head.next == null){ // If there is only 1 item in the list just point head and tail to null.
            E temp = head.data;
            head = null;
            tail = null;
            size--;
            return temp;
        }else{
            E temp = head.data;
            head = head.next; //set the head pointer to point to the next node, effectively deleting the first node.
            if(head.next == null) tail = head; //if the new head has no next node, it is the last node, so set the tail to it too.
            size--;
            return temp;
        }
    }

    //Returns the first element, but does not remove it.
    public E front(){
        if(head == null) throw new NoSuchElementException();
        return head.data;
    }

    //Returns true if the Queue is empty.
    public boolean isEmpty(){
        return (size == 0);
    }

    //get the size of the queue.
    public int size() {
        return size;
    }

    //method to print out the list
    public void printList()
    {
        Node temp;
        temp = head;
        while (temp != null)
        {
            System.out.print(temp.data + "   ");
            temp = temp.next;
        }
    }

    //class to create nodes as objects
    private class Node
    {
        private E data;  //data field
        private Node next; //link field

        public Node(E element) //constructor method
        {
            data = element;
            next = null;
        }

    }

}