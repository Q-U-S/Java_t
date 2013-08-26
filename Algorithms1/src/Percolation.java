/*
 * It is all about percolation =)
 */
//import WeightedQuickUnionUF;


public class Percolation {
	private WeightedQuickUnionUF wqfItems;
	private boolean[] wqfItemStatus;
	private int iEleNumTotal;
	private int iN;
	
	public static void main(String[] args){
		System.out.println("this si percolation!\n");
		return;
	}
	
   public Percolation(int N){              // create N-by-N grid, with all sites blocked
	   int i;	   
	   iEleNumTotal = N*N + 2;
	   iN = N;

	   wqfItems = new WeightedQuickUnionUF(iEleNumTotal);
	   wqfItemStatus = new boolean[iEleNumTotal];
	   for (i = 0 ; i < iEleNumTotal; i ++){
		   wqfItemStatus[i] = false;
	   }
	   wqfItemStatus[0] = true;
	   wqfItemStatus[N*N + 1] = true;
	   
   }
   public void open(int i, int j){         // open site (row i, column j) if it is not already
	   int iElementId = i*iN + j;
	   if(i > iN || j > iN || i < 0 || j < 0){
		   return;
	   }
	   if(wqfItemStatus[iElementId]){
		   return;
	   }

	   //   /\
	   //   || 
	   if(i>0){
		   if(wqfItemStatus[(i-1)* iN + j]){
			   wqfItems.union((i-1)* iN + j, iElementId);
		   }
	   }else{
		   wqfItems.union(0, iElementId);
	   }
	   
	   
	   //   ||
	   //   \/ 
	   if(i<iN){
		   if(wqfItemStatus[(i+1)* iN + j]){
			   wqfItems.union((i+1)* iN + j, iElementId);   
		   }
		   
	   }else{
		   wqfItems.union(iEleNumTotal - 1, iElementId);
	   }
	   
	   //   <--
	   if(j>0){
		   if(wqfItemStatus[(i)* iN + j - 1]){
			   wqfItems.union((i-1)* iN + j - 1, iElementId);
		   }
	   }
	   //   -->
	   if(j<iN){
		   if(wqfItemStatus[(i)* iN + j + 1]){
			   wqfItems.union((i-1)* iN + j + 1, iElementId);
		   }
	   }
	   wqfItemStatus[iElementId] = true;
	   return;
	   
   }
   public boolean isOpen(int i, int j){    // is site (row i, column j) open?
	   return wqfItemStatus[i*iN + j];
   }
   public boolean isFull(int i, int j){    // is site (row i, column j) full?
	   if (i <= 0 || i > iN) throw new IndexOutOfBoundsException("row index i out of bounds");
	   if (j <= 0 || j > iN) throw new IndexOutOfBoundsException("row index i out of bounds");
	   return wqfItems.connected(0, (i-1)*iN + j);
   }
   public boolean percolates(){            // does the system percolate?
	   return wqfItems.connected(0, iEleNumTotal-1);
   }
}
