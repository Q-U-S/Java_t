/*****************************************************************
 * 
 * @author qus
 *
 *****************************************************************/
//import RandomizedQueue
//import java.lang.st;


public class Subset {

    public static void main(String[] args){
	RandomizedQueue<String> rqItems = new RandomizedQueue<String>();
	int iItem = StdIn.readInt();
	StdOut.println(iItem);
	while (!StdIn.isEmpty()) {
	    String sItem = StdIn.readString();
            //readString();
//            if (!item.equals("-")) s.push(item);
//            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
	    StdOut.println(sItem);
	    rqItems.enqueue(sItem);
        }
	if(rqItems.size() < iItem){
	    iItem = rqItems.size();
	}	
	for(int i = 0 ; i < iItem; i ++){

	    StdOut.println(rqItems.dequeue());
	}
        //StdOut.println("(" + s.size() + " left on stack)");
    }

}