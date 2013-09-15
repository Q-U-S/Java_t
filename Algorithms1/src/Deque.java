
import java.util.Iterator;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable<Item> {
    private int iNum; // size of the Deque
    private DecElement eHead; // first element
    private DecElement eTail; // last element

    // the identity element
    private class DecElement {
	private Item item;
	private DecElement next;
	private DecElement prev;

	public DecElement(Item initItem) {
	    next = null;
	    prev = null;
	    item = initItem;
	}
    }

    /**
     * construct an empty deque
     */
    public Deque() {
	iNum = 0;
    }

    /**
     * is the deque empty?
     * 
     * @return boolean
     */
    public boolean isEmpty() {
	return iNum == 0;
    }

    /**
     * 
     * @returnthe number of items on the deque
     */
    public int size() {
	return iNum;
    }

    /**
     * insert the item at the front
     * 
     * @param item
     */
    public void addFirst(Item item) {
	if (item == null)
	    throw new java.lang.NullPointerException("No item passed.");
	DecElement eTemp;
	if(isEmpty()){
	    eHead = new DecElement(item);
	    eTail = eHead;
	}else{
        	eHead.next = new DecElement(item); // Create new firest
        	eTemp = eHead; // Save current first
        	eHead = eHead.next; // Set new eHead
        	eHead.prev = eTemp; // Finish init of the new eHead
	}
	iNum += 1; // Increment deque size
    }

    public void addLast(Item item) { // insert the item at the end
	if (item == null)
	    throw new java.lang.NullPointerException("No item passed.");
	DecElement eTemp;
	
	if(isEmpty()){
	    eHead = new DecElement(item);
	    eTail = eHead;
	}else{
        	eTail.prev = new DecElement(item); // Init new last element
        	eTemp = eTail;
        	eTail = eTail.prev;
        	eTail.next = eTemp;
	}
	iNum += 1; // Increment deque size
    }

    /**
     * delete and return the item at the front
     * 
     * @return first item
     */
    public Item removeFirst() { //
	if (isEmpty())
	    throw new java.util.NoSuchElementException("No element!");
	Item iToReturn;
	iToReturn = eHead.item;
	if (iNum > 1) {
	    eHead = eHead.prev;
	    eHead.next = null;
	} else {
	    eHead = null;
	    eTail = null;
	}
	iNum -= 1;
	return iToReturn;
    }

    /**
     * delete and return the item at the end
     * 
     * @return
     */
    public Item removeLast() {
	if (isEmpty())
	    throw new java.util.NoSuchElementException("No element!");

	Item iToReturn;
	iToReturn = eTail.item;
	if (iNum > 1) {
	    eTail = eTail.next;
	    eTail.prev = null;
	} else {
	    eHead = null;
	    eTail = null;
	}
	iNum -= 1;
	return iToReturn;
    }

    /**
     * Return an iterator to the deque that iterates through the items in FIFO
     * order.
     */
    public Iterator<Item> iterator() {
	return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
	private DecElement current = eHead;

	public boolean hasNext() {
	    return current.prev != null;
	}

	public void remove() {
	    throw new java.lang.UnsupportedOperationException(
		    "No implementation of remove!");
	}

	public Item next() {
	    if (!hasNext())
		throw new NoSuchElementException();
	    Item item = current.item;
	    current = current.prev;
	    return item;
	}
    }

    public static void main(String[] args){
	Deque<Integer> tDeq = new Deque<Integer>();
	int i;
	for(i = 0 ; i < 10 ; i++){
	    tDeq.addFirst(i);
	}
	
	for (int num : tDeq) {
	    StdOut.println(num);
	}
	StdOut.println("Hello world!");
    }
    // java.util.NoSuchElementException if the client calls the next() method in
    // the iterator and there are no more items to return
    // in iterator java.lang.UnsupportedOperationException
    // Throw a java.lang.NullPointerException if the client attempts to add a
    // null item; throw a java.util.NoSuchElementException
    // if the client attempts to remove an item from an empty deque; throw a
    // java.lang.UnsupportedOperationException
    // if the client calls the remove() method in the iterator; throw a
    // java.util.NoSuchElementException
    // if the client calls the next() method in the iterator and there are no
    // more items to return.

}
