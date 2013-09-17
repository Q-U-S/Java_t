
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
    
    //private boolean[] rQueueStorageDP; //Because data proof... or some thing
    //private int rqStorageSize;
    //private int lastIt;
    //private int firstIt;
    private int numIt;
    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue(){
	rQueueStorage = (Item[]) new Object[2];
	//rQueueStorageDP = new boolean[2];
	numIt = 0;
	//firstIt = 0;
	//lastIt = 0;
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
	int i = 0;
	Item tmpItem;
	numIt += 1;
	/*
	 * Resize array if needed
	 */
	if(numIt > rQueueStorage.length){
	    Item[] newRQueueStorage = (Item[]) new Object[rQueueStorage.length*2]; 
	    for(i = 0 ; i < rQueueStorage.length; i++){
		newRQueueStorage[i] = rQueueStorage[i];
		rQueueStorage[i] = null;
	    }
	    rQueueStorage = newRQueueStorage;
	}
	/*
	 * Implement one shuffle iteration
	 */
	i = StdRandom.uniform(numIt);
	tmpItem = rQueueStorage[i];
	rQueueStorage[i] = item;
	rQueueStorage[numIt - 1] = tmpItem;
	return;
    }
    
    /**
     * delete and return a random item
     * @return
     */
    public Item dequeue(){              
	if(numIt == 0) throw new java.util.NoSuchElementException("Unable to get item from empty queue.\n"); 
	Item retItem;
	/*
	 * Resize if needed
	 */
	numIt -= 1;
	retItem = rQueueStorage[numIt];
	if(rQueueStorage.length/4 > numIt){
	    Item[] newRQueueStorage = (Item[]) new Object[rQueueStorage.length/2];
	    for(int i = 0;i < numIt; i++){
		newRQueueStorage[i] = rQueueStorage[i];
		rQueueStorage[i] = null;
	    }
	    rQueueStorage = newRQueueStorage;
	}
	return retItem;
    }
    
    /**
     *  return (but do not delete) a random item
     *  executes reorder elements
     * @return
     */
    public Item sample(){
	Item retItem;
	int idItem;
	/*
	 * We return always the last element, so we must ensure that same element will
	 * not be return each time we call this procedure
	 * We shuffle it(last one) with others
	 */
	retItem = rQueueStorage[numIt - 1];
	idItem = StdRandom.uniform(numIt);
	rQueueStorage[numIt - 1] = rQueueStorage[idItem];
	rQueueStorage[idItem] = retItem;
	return retItem;
    }
    /**
     *  return an independent iterator over items in random order
     */
    public Iterator<Item> iterator(){
	return new RandomQueueIter();
    }
    
    class RandomQueueIter implements Iterator<Item>{
	Item rQueueStorageIt[];
	int avIt; //Steal avalible items
	
	public RandomQueueIter() {
	    int i = 0;
	    rQueueStorageIt = (Item[]) new Object[numIt];
	    
	    for(i = 0 ; i < numIt; i++){
		rQueueStorageIt[i] = rQueueStorage[i];
	    }
	    avIt = rQueueStorageIt.length;
	    // TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean hasNext() {
	    // TODO Auto-generated method stub
	    return avIt != 0;
	}

	@Override
	public Item next() {
	    // TODO Auto-generated method stub
	    if(avIt == 0) throw new java.util.NoSuchElementException("No elements left.");
	    avIt -= 1;
	    Item retIt = rQueueStorageIt[avIt];
	    
	    rQueueStorageIt[avIt] = null;
	    return retIt;
	}

	@Override
	public void remove() {
	    throw new java.lang.UnsupportedOperationException("Remove are not supported for iterator.\n");
	    // TODO Auto-generated method stub
	    //return ;
	}
	
    }
    
    public static void main(String[] args){
	RandomizedQueue<Integer> testQueue= new RandomizedQueue<Integer>();
	int j = 0;
	for(j = 0 ; j < 10; j++){
	    testQueue.enqueue(j);
	}
	for (Integer val : testQueue) {
	    StdOut.println(val);
	}
	
	StdOut.println("Hello world! from Randomized Queue!");
	
	
    }
    /**Throw a java.lang.NullPointerException if the client attempts to add a null item; 
     * throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item from an empty randomized queue;
     *  throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator;
     *  throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return. 
     */
}