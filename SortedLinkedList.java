package storage;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedList <T extends Comparable<? super T>>
    implements Iterable<T>
{
    private Node<T> head;  
    private int length;

    public SortedLinkedList()
    {
        head = null;
        length = 0;

    }

    public void add(T entry)
    {
        if (head == null || entry.compareTo(head.getData()) <= 0)
        {
            head = new Node<>(entry, head);

        }
        else
        {
            Node<T> prevNode = getPrevious(entry);
            prevNode.setLink(new Node<>(entry, prevNode.getLink()));

        }

        length++;

    }

    public T remove(int position)
    {
        if (position < 0 || position >= length)
        {
            return null;
        }

        Node<T> remove;
        if (position == 0)
        {
            remove = head;
            head = head.getLink();
        }
        else
        {
            Node<T> prevNode = head;
            for (int i = 0; i < position -1; i++)
            {
                prevNode = prevNode.getLink();
            }
            remove = prevNode.getLink();
            prevNode.setLink(remove.getLink());

        }
        length--;

        return remove.getData();

    }

    public void clear()
    {
        head = null;
        length = 0;

    }

    public T getEntry(int position)
    {
        if (position < 0 || position >= length)
        {
            return null;
        }

        Node<T> current = head;
        for (int i = 0; i < position; i++)
        {
            current = current.getLink();
        }

        return current.getData();

    }

    public int getPosition(T entry)
    {
        Node<T> current = head;
        int position = 0;

        while (current != null)
        {
            if (current.getData().equals(entry))
            {
                return position;
            }
            current = current.getLink();
            position++;

        }

        throw new IllegalArgumentException("Element not found");

    }

    public boolean contains(T entry)
    {
        Node<T> current = head;
        while (current != null)
        {
            if (current.getData().equals(entry))
            {
                return true;
            }
            current = current.getLink();
        }

        return false;

    }

    public int getLength()
    {
        return length;
    }

    public boolean isEmpty()
    {
        return length == 0;
    }

    public void display()
    {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + " ");
        }
       
        System.out.println();

    }

    public Iterator<T> iterator()
    {
        return new SLLIterator(head);
    }

    private Node<T> getPrevious(T entry)
    {
        Node<T> current = head;
        Node<T> prevNode = null;
        while (current != null && entry.compareTo(current.getData()) > 0)
        {
            prevNode = current;
            current = current.getLink();
        }

        return prevNode;

    }

    private class SLLIterator implements Iterator<T>
    {
        private boolean calledNext;
        private Node<T> prevNode;
        private Node<T> currNode;
        private Node<T> nextNode;

        public SLLIterator(Node<T> firstNode)
        {
            this.currNode = firstNode;
            this.nextNode = firstNode;
            this.prevNode = null;
            this.calledNext = false;
        }

        public boolean hasNext()
        {
            return nextNode != null;
        }

        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }

            prevNode = currNode;
            currNode = nextNode;
            nextNode = nextNode.getLink();
            calledNext = true;

            return currNode.getData();

        }

        public void remove()
        {
            if (!calledNext)
            {
                throw new IllegalStateException();
            }

            if (prevNode == null)
            {
                throw new IllegalStateException();
            }
           
            if (currNode == prevNode)
            {
                prevNode = null;
            }
            else
            {
                prevNode.setLink(nextNode);
            }
            currNode = nextNode;
            calledNext = false;

        }

    }

}