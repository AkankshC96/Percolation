import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

import princeton.*;

/**
 * Print statistics on Percolation: prompts the user for N and T, performs T
 * independent experiments on an N-by-N grid, prints out the 95% confidence
 * interval for the percolation threshold, and prints mean and std. deviation
 * of timings
 * 
 * @author Kevin Wayne
 * @author Professor Jeff Forbes
 * @author Akanksh Chaudhary
 */

public class PercolationStats {
	public static int RANDOM_SEED = 1234;
	public static Random ourRandom = new Random(RANDOM_SEED);
	
	public static double mean(ArrayList<Double> args ) {
		double sum=0;
		for (int i=0; i<args.size();i++){
			sum+=args.get(i);
		}
		
		double mean= sum/args.size();
		return mean;
	}
	public static double sum(ArrayList<Double> args){
		double sum=0;
		for (int i=0; i<args.size();i++){
			sum+=args.get(i);
		}
		return sum;
	}
	public static double stddev(ArrayList<Double> args ){
		double sum=0;
		double sd = 0;
		for (int i=0; i<args.size();i++){
			sum+=args.get(i);
		}
		double mean= sum/args.size();
		
		for (int j=0; j<args.size();j++){
		    sd=sd+ Math.pow(args.get(j) - mean, 2);
		}
		return Math.sqrt(sd/(args.size()-1));
	}
	
	public static void main(String[] args) {
		int N, T;
		if (args.length == 2) { // use command-line arguments for
								// testing/grading
			N = Integer.parseInt(args[0]);
			T = Integer.parseInt(args[1]);
		} else {
			String input = JOptionPane.showInputDialog("Enter N and T", "20 100");
			// TODO: parse N and T from input
			String[] value= input.split(" ");
			N= Integer.parseInt(value[0]);
			T= Integer.parseInt(value[1]);
		}
		// TODO: Perform T experiments for N-by-N grid

		double count=0;
		
		ArrayList<Double> counts =new ArrayList<Double>(); 
		ArrayList<Double> times =new ArrayList<Double>(); 
		for(int i=0;i<T;i++){
			double timeStart= System.currentTimeMillis();
			//IPercolate perc = new PercolationDFS(N);
			IPercolate perc = new PercolationUF(N, new QuickUWPC(N));
			//IPercolate perc = new PercolationUF(N, new QuickFind());
			ArrayList<Integer> alist = new ArrayList<Integer>();
			for (int j=0; j< N*N; j++){
				alist.add(j);
			}
			Collections.shuffle(alist,ourRandom);
			for (int k=0; k< N*N; k++){
				int value=alist.get(0);
				int row= value/N;
				int col=value%N;
				alist.remove(0);
				perc.open(row,col);
				count++;
				if (perc.percolates()){
					break;
			}
			
			}
			
			double timeEnd= System.currentTimeMillis();	
			times.add(timeEnd-timeStart);
			//System.out.println(count/(N*N));
			counts.add(count/(N*N));
			count=0;
		}
		double mean = mean(times);
        double stddev = stddev(times);
        double[] interval = new double[2];
        double sum = sum(times);        
        interval[0] = mean - ((1.96 * stddev) / Math.sqrt(T));
        interval[1] = mean + ((1.96 * stddev) / Math.sqrt(T));
        
        double mean2 = mean(counts);
        double stddev2 = stddev(counts);
        double[] interval2 = new double[2];
        
        interval2[0] = mean2 - ((1.96 * stddev2) / Math.sqrt(T));
        interval2[1] = mean2 + ((1.96 * stddev2) / Math.sqrt(T));
		// TODO: print statistics and confidence interval
        System.out.println("mean of times = " + mean/1000);
        System.out.println("stddev of times  = " + stddev/1000);
        System.out.println("95% confidence interval of times = " + interval[0]/1000 + ", " + interval[1]/1000);
        System.out.println("sum of times = " + sum/1000);
        System.out.println("mean percolation threshold = " + mean2);
        System.out.println("stddev percolation threshold  = " + stddev2);
        System.out.println("95% confidence interval percolation threshold = " + interval2[0] + ", " + interval2[1]);
	}
}
