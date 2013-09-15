
public class SandBox {

    public static void main(String[] args){
	Stopwatch testStopwatch = new Stopwatch();
	
	
	int N = 100000000;
	int sum = 0;
	double time = 0;
	
	for (int i = 0; i*i < N; i++)
	    for (int j = 0; j*j < N*N*N; j++)
	        sum++;
	time = testStopwatch.elapsedTime();
	StdOut.println(time);
    }    
}
