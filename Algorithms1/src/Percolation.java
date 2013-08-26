/*
 * It is all about percolation =)
 */
//import WeightedQuickUnionUF;


public class Percolation {
	private WeightedQuickUnionUF wqfItems;
	private boolean[] wqfItemStatus;
	private int iEleNumTotal;
	private int iN;

	public Percolation(int N){              // create N-by-N grid, with all sites blocked
	   int i;	   
	   iEleNumTotal = N*N + 2;
	   iN = N;

	   wqfItems = new WeightedQuickUnionUF(iEleNumTotal);
	   wqfItemStatus = new boolean[iEleNumTotal];
	   for (i = 0 ; i < iEleNumTotal; i ++){
		   wqfItemStatus[i] = false;
	   }
	   wqfItemStatus[iEleNumTotal - 2] = true;
	   wqfItemStatus[iEleNumTotal - 1] = true;
	   
	}
	public void open(int i, int j){         // open site (row i, column j) if it is not already
	   int iElementId;
	   if (i < 1 || i > iN) throw new IndexOutOfBoundsException("row index i out of bounds");
	   if (j < 1 || j > iN) throw new IndexOutOfBoundsException("col index i out of bounds");
	   
	   i -= 1;
	   j -= 1;
	   iElementId = i*iN + j;
	   
	   if(wqfItemStatus[iElementId]){
		   return;
	   }

	   //   /\
	   //   || 
	   if(i>0){
		   if(wqfItemStatus[(i-1)*iN + j]){
			   wqfItems.union((i-1)* iN + j, iElementId);
		   }
	   }else{
		   wqfItems.union(iEleNumTotal - 2, iElementId);
	   }
	   
	   
	   //   ||
	   //   \/ 
	   if(i<iN - 1){
		   if(wqfItemStatus[(i+1)* iN + j]){
			   wqfItems.union((i+1)* iN + j, iElementId);   
		   }
		   
	   }else{
		   wqfItems.union(iEleNumTotal - 1, iElementId);
	   }
	   
	   //   <--
	   if(j>0){
		   if(wqfItemStatus[i* iN + j - 1]){
			   wqfItems.union(i* iN + j - 1, iElementId);
		   }
	   }
	   //   -->
	   if(j<iN-1){
		   if(wqfItemStatus[i* iN + j + 1]){
			   wqfItems.union(i* iN + j + 1, iElementId);
		   }
	   }
	   wqfItemStatus[iElementId] = true;
	   return;
	   
	}
	public boolean isOpen(int i, int j){    // is site (row i, column j) open?
	   if (i < 1 || i > iN) throw new IndexOutOfBoundsException("row index i out of bounds");
	   if (j < 1 || j > iN) throw new IndexOutOfBoundsException("col index i out of bounds");
	   
	   return wqfItemStatus[(i-1)*iN + j-1];
	}
	public boolean isFull(int i, int j){    // is site (row i, column j) full?
	   if (i < 1 || i > iN) throw new IndexOutOfBoundsException("row index i out of bounds");
	   if (j < 1 || j > iN) throw new IndexOutOfBoundsException("col index i out of bounds");
	   return wqfItems.connected(iEleNumTotal-2, (i-1)*iN + j-1);
	}
	public boolean percolates(){            // does the system percolate?
	   return wqfItems.connected(iEleNumTotal-2, iEleNumTotal-1);
	}
}
