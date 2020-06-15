package tads.DoubleLinkedList;

public interface Lista<T> {
      void add(T value);
      void remove(int position) throws EmptyList, InvalidIndex;
      Object get(int position);
    }


