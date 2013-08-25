/*
 * It is all about percolation =)
 */
import algs4.WeightedQuickUnionUF


public class Percolation {
	private WeightedQuickUnionUF wqfItems;
	private boolean[] wqfItemStatus;
	private int iEleNumTotal;
	
	public static void main(String[] args){
		return;
	}
	
   public Percolation(int N){              // create N-by-N grid, with all sites blocked
	   
	   iEleNumTotal = N*N + 2;
	   wqfItems = WeightedQuickUnionUF(iEleNumTotal);
	   wqfItemStatus = new boolean[iEleNumTotal];
	   for (i = 0 ; i < iEleNumTotal; i ++){
		   wqfItemStatus[iEleNumTotal] = false;
	   }
	   open(0,0);
	   open(N,N+1);
	   
   }
   public void open(int i, int j){         // open site (row i, column j) if it is not already
	   int iElementId = i*N + j;
	   if(wqfItemStatus[iElementId]){
		   return;
	   }

	   //   /\
	   //   || 
	   if(i>0){
		   wqfItems.union((i-1)* N + j, iElementId);
	   }else{
		   wqfItems.union(0, iElementId);
	   }
	   
	   
	   //   ||
	   //   \/ 
	   if(i<N){
		   wqfItems.union((i+1)* N + j, iElementId);
	   }else{
		   wqfItems.union(iEleNumTotal - 1, iElementId);
	   }
	   
	   //   <--
	   if(j>0){
		   wqfItems.union((i)* N + j - 1, iElementId);
	   }
	   //   -->
	   if(j<N){
		   wqfItems.union((i)* N + j + 1, iElementId);
	   }
	   wqfItemStatus[iElementId] = true;
	   return;
	   
   }
   public boolean isOpen(int i, int j){    // is site (row i, column j) open?
	   return wqfItemStatus[i*N + j];
   }
   public boolean isFull(int i, int j){    // is site (row i, column j) full?
	   return wqfItems.connected(0, i*N + j);
   }
   public boolean percolates(){            // does the system percolate?
	   return wqfItems.connected(0, iEleNumTotal-1);
   }
}