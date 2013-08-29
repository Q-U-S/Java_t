//import Percolation
//import StdRandom

public class PercolationStats {
//	private int iNSize;
//	private int iTimesNum;
	private double[] iExpResX;
	private double perstMean;
	private double perstStdDev;
	private double perstConfHi;
	private double perstConfLo;
	
	public PercolationStats(int N, int T){    // perform T independent computational experiments on an N-by-N grid
		//iNSize = N;
		//iTimesNum = T;
		double dTmp = 0;
		//double dTime;
		int i = 0;
		int iOpen;
		int x,y;
		if(N <= 0 || T <= 0) throw new java.lang.IllegalArgumentException();
		iExpResX = new double[T];
		for (i = 0; i < T ; i ++){
			iOpen = 0;
			Percolation pPercExp = new Percolation(N);
			while(true){
				x = StdRandom.uniform(1, N + 1);
				y = StdRandom.uniform(1, N + 1);
				if( pPercExp.isOpen(x, y) ){
					continue;
				}
				pPercExp.open(x, y);
				iOpen += 1;				
				if( pPercExp.percolates() ){
					break;
				}
			}

			iExpResX[i] = iOpen/(Math.pow(N,2));
		}
		
		for(i = 0 ; i < T ; i ++){
			dTmp += iExpResX[i];	
		}
		perstMean = dTmp / T;
		
		dTmp = 0;
		
		for(i = 0 ; i < T ; i ++){
			dTmp += Math.pow(iExpResX[i] - perstMean,2);
		}
		perstStdDev = dTmp / (T - 1);
		
		perstConfHi = perstMean + (1.96 * perstStdDev)/Math.sqrt(T);
		perstConfLo = perstMean - (1.96 * perstStdDev)/Math.sqrt(T);
		
		
//		Stopwatch tWatch = new Stopwatch(); // init
//		dTime = tWatch.elapsedTime();// get elapsed time
		
	}
	public double mean(){                     // sample mean of percolation threshold
		return perstMean;
		
	}
	public double stddev(){                   // sample standard deviation of percolation threshold
		return Math.sqrt(perstStdDev);
	}
	public double confidenceLo(){             // returns lower bound of the 95% confidence interval
		return perstConfLo;
	}
	public double confidenceHi(){             // returns upper bound of the 95% confidence interval
		return perstConfHi;
	}
	public static void main(String[] args){   // test client, described below
		int N,T;
		PercolationStats psCurExp;
		if(args.length < 2) throw new IndexOutOfBoundsException("Not enongh arguments!\n");
		N = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);
        psCurExp = new PercolationStats( N, T);

        StdOut.println("mean                    = " + psCurExp.mean());
        StdOut.println("stddev                  = " + psCurExp.stddev());
        StdOut.println("95% confidence interval = " +  psCurExp.confidenceLo() + " " + psCurExp.confidenceHi());
		//StdRandom.uniform(a, b)
	}
}