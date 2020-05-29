package Queue.SimpleQueue;

public interface MyQueue<T> {
    void enqueue(T element) throws QueueFullException;
    T dequeue() throws EmptyQueueException;
    boolean isEmpty();
    public T getFirst() throws EmptyQueueException;
}