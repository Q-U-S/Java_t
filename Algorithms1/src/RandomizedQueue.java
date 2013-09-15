
import java.util.Iterator;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

/*************************************************************************
 * 
 * @author qus
 *
 * @param <Item>
 *************************************************************************/



public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] rQueueStorage;
    private boolean[] rQueueStorageDP; //Because data proof... or some thing
    //private int rqStorageSize;
    private int lastIt;
    private int firstIt;
    private int numIt;
    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue(){
	rQueueStorage = (Item[]) new Object[2];
	rQueueStorageDP = new boolean[2];
	numIt = 0;
	firstIt = 0;
	lastIt = 0;
    }
    
    /**
     *  is the queue empty?
     */
    public boolean isEmpty(){
	return numIt == 0;
    }
    
    /**
     * return the number of items on the queue
     * @return
     */
    public int size(){
	return numIt;
    }
    /**
     * add the item
     * @param item
     */
    public void enqueue(Item item){  
	if(item == null) throw new java.lang.NullPointerException("Unable to add null element to queue");
	
	if(lastIt < rQueueStorage.length){
	    lastIt += 1;
	    numIt+=1;
	    rQueueStorage[lastIt] = item;
	    return;
	}else if(lastIt == rQueueStorage.length){
	    if(rQueueStorage.length/2 > numIt){
		Item tmpIt;
		int i,curFreeId=0;
		for(i = 0; i <= lastIt; i++){
		    if(curFreeId == 0 && rQueueStorageDP[i] == false){
			curFreeId = i;
		    }
		}
	    }
	}
    }
    
    /**
     * delete and return a random item
     * @return
     */
    public Item dequeue(){              
	
    }
    
    /**
     *  return (but do not delete) a random item
     * @return
     */
    public Item sample(){
	
    }
    /**
     *  return an independent iterator over items in random order
     */
    public Iterator<Item> iterator(){
	
    }
    
    /**Throw a java.lang.NullPointerException if the client attempts to add a null item; 
     * throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item from an empty randomized queue;
     *  throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator;
     *  throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return. 
     */
}