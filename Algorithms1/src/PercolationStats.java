/*----------------------------------------------------------------
 *  Author:        Andrei Perapiolkin
 *  Written:       28/8/2013
 *  Last updated:  28/8/2013
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats
 *  
 *  Use class Percolation to solve problem of finding number of
 *  random inserts in square size N*N to make it percolates from 
 *  top to bottom.
 *
 *  java PercolationStats 200 100
 *  mean                    = 0.5929934999999997
 *  stddev                  = 0.00876990421552567
 *  95% confidence interval = 0.5912745987737567, 0.5947124012262428
 *
 *----------------------------------------------------------------*/

public class PercolationStats {
    private double[] iExpResX; 		// Experiment result log
    private double perstMean; 		// Mean calculated value
    private double perstStdDev; 	// Standard deviation comp. value
    private double perstConfHi; 	// Confidence interval high value
    private double perstConfLo; 	// Confidence interval low value

    /**
     * By creating an object of PercolationStats
     * perform T independent computational experiments on an N-by-N grid
     * 
     * Throws a IllegalArgumentException if N or T is less then 0.
     */
    public PercolationStats(int N, int T) { 

	double dTmp = 0;		// Tmp double storage

	int i = 0;			// int iterator
	int iOpen;
	int x, y;
	if (N <= 0 || T <= 0)
	    throw new java.lang.IllegalArgumentException();
	iExpResX = new double[T];
	for (i = 0; i < T; i++) {	//  Fill iExpResX[i] with experiment 
	    				//        data on the cube size N*N
	    iOpen = 0;			//  num of random open before percolation
	    Percolation pPercExp = new Percolation(N);
	    while (true) {
		x = StdRandom.uniform(1, N + 1);
		y = StdRandom.uniform(1, N + 1);
		if (pPercExp.isOpen(x, y)) {
		    continue;
		}
		pPercExp.open(x, y);
		iOpen += 1;
		if (pPercExp.percolates()) {
		    break;
		}
	    }
	    iExpResX[i] = iOpen / (Math.pow(N, 2));
	}

	for (i = 0; i < T; i++) { 	//  Sum all exp proportional results
	    dTmp += iExpResX[i];
	}
	perstMean = dTmp / T;		//  Calculate mean

	dTmp = 0;

	for (i = 0; i < T; i++) {
	    dTmp += Math.pow(iExpResX[i] - perstMean, 2);
	}
	perstStdDev = dTmp / (T - 1);	//  Calculate standard deviation

	/*
	 * Confidential interval
	 */
	perstConfHi = perstMean + (1.96 * perstStdDev) / Math.sqrt(T); 
	perstConfLo = perstMean - (1.96 * perstStdDev) / Math.sqrt(T);

    }
    
    /**
     * sample mean of percolation threshold
     * @return double mean
     */
    public double mean() { // 
	return perstMean;

    }
    
    /**
     * sample standard deviation of percolation threshold
     * @return  standard deviation value
     */
    public double stddev() { 
	return Math.sqrt(perstStdDev);
    }

    /**
     * @returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() { 
				   
	return perstConfLo;
    }

    /**
     * @returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() { 
				   
	return perstConfHi;
    }

    public static void main(String[] args) { 
	int N, T;
	PercolationStats psCurExp;
	if (args.length < 2)
	    throw new IndexOutOfBoundsException("Not enongh arguments!\n");
	N = Integer.parseInt(args[0]);
	T = Integer.parseInt(args[1]);
	psCurExp = new PercolationStats(N, T);

	StdOut.println("mean                    = " + psCurExp.mean());
	StdOut.println("stddev                  = " + psCurExp.stddev());
	StdOut.println("95% confidence interval = " + psCurExp.confidenceLo()
		+ " " + psCurExp.confidenceHi());
    }
}