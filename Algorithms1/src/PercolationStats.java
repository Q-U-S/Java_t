//import Percolation
//import StdRandom

public class PercolationStats {
	private int iNSize;
	private int iTimesNum;
	public PercolationStats(int N, int T){    // perform T independent computational experiments on an N-by-N grid
		iNSize = N;
		iTimesNum = T;
		double dTime;
		Stopwatch tWatch = new Stopwatch(); // init
		dTime = tWatch.elapsedTime();// get elapsed time
		
	}
	public double mean(){                     // sample mean of percolation threshold
		return 0;
		
	}
	public double stddev(){                   // sample standard deviation of percolation threshold
		return 0;
	}
	public double confidenceLo(){             // returns lower bound of the 95% confidence interval
		return 0;
	}
	public double confidenceHi(){             // returns upper bound of the 95% confidence interval
		return 0;
	}
	public static void main(String[] args){   // test client, described below
		int N,T;
		PercolationStats psCurExp;
		if(args.length < 2) throw new IndexOutOfBoundsException("Not enongh arguments!\n");
		N = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);
        psCurExp = new PercolationStats( N, T);
		//StdRandom.uniform(a, b)
	}
}