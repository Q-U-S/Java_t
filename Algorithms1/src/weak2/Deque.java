package weak2;

public class Deque<Item> implements Iterable<Item> {
    /**
     * construct an empty deque
     */
    public Deque(){
	
    }
    
    /**
     * is the deque empty?
     * @return boolean
     */
    public boolean isEmpty()           // 
    public int size()                  // return the number of items on the deque
    public void addFirst(Item item){    // insert the item at the front
	java.lang.NullPointerException
    }
    public void addLast(Item item){     // insert the item at the end
	java.lang.NullPointerException
    }
    public Item removeFirst(){          // delete and return the item at the front
	java.util.NoSuchElementException
    }
    public Item removeLast(){           // delete and return the item at the end
	java.util.NoSuchElementException
    }
    public Iterator<Item> iterator(){   // return an iterator over items in order from front to end
	
    }
    //java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return
    // in iterator java.lang.UnsupportedOperationException
    //Throw a java.lang.NullPointerException if the client attempts to add a null item; throw a java.util.NoSuchElementException 
    //if the client attempts to remove an item from an empty deque; throw a java.lang.UnsupportedOperationException 
    //if the client calls the remove() method in the iterator; throw a java.util.NoSuchElementException 
    //if the client calls the next() method in the iterator and there are no more items to return. 
    
}
