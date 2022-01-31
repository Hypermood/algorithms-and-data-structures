package weblab;
import java.io.*;



class ArrayQueue {

    private int[] arr;

    private int head;

    private int size;

    private int tail;

    /**
     * Creates a new ArrayQueue with the given capacity.
     *
     * @param capacity the capacity for this queue
     */
    public ArrayQueue(int capacity) {
        this.arr = new int[capacity];
        this.size = 0;
        this.head = -1;
        this.tail = -1;
    }

    /**
     * Adds the given element to the queue.
     *
     * @param e the element to add to the queue
     * @throws FullQueueException if the queue is full
     */
    public void enqueue(int e) throws FullQueueException {
        if(isFull()){
            throw new FullQueueException();
        }
        else{
            head = (head+1)%this.arr.length;
            arr[head] = e;
            size++;
        }
    }

    /**
     * Removes an element from the queue and returns it.
     *
     * @return the first element in the queue
     * @throws EmptyQueueException if the queue is empty
     */
    public int dequeue() throws EmptyQueueException, FullQueueException {
        int result;
        if(isEmpty()){
            throw new EmptyQueueException();
        }
        else{
            tail = (tail+1)%this.arr.length;
            result = this.arr[tail];
            size--;
        }
        return result;

    }


    public boolean isFull(){

        return this.size == this.arr.length;

    }

    public boolean isEmpty(){

        return this.size ==0;

    }

}


class EmptyQueueException extends RuntimeException {

    @Serial private static final long serialVersionUID = 42L;
}

class FullQueueException extends RuntimeException {

    @Serial private static final long serialVersionUID = 42L;
}