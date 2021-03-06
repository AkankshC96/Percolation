import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

import princeton.*;

/**
 * Animates the results of opening sites in a percolation system
 * 
 * From Princeton COS 226, Kevin Wayne 
 * Modified by Owen Astrachan, January 2008
 * Modified by Jeff Forbes, October 2008
 * Modified by Akanksh Chaudhary, 2016 
 */

public class PercolationVisualizer {
	public static int RANDOM_SEED = 1234;
	public static Random ourRandom = new Random(RANDOM_SEED);

	/**
	 * Draws a square of color c at (row,col) on a N*N grid
	 */
	public static void draw(int row, int col, int N, Color c) {
		StdDraw.setPenColor(c);
		StdDraw.filledSquare(col + .5, N - row - .5, .45);
	}

	public static void main(String[] args) {
		// Animate 20 times a second if possible
		final int DEFAULT_DELAY = 1000 / 20; // in milliseconds
		String input = "20"; // default
		if (args.length == 1) // use command-line arguments for testing/grading
			input = args[0];
		else
			input = JOptionPane.showInputDialog("Enter N", "20");
		int N = Integer.parseInt(input); // N-by-N lattice

		// set x- and y-scale
		StdDraw.setXscale(0, N);
		StdDraw.setYscale(0, N);
		// draw a black box
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledSquare(N / 2.0, N / 2.0, N / 2.0);

		IPercolate perc = new PercolationDFS(N);
		//IPercolate perc = new PercolationUF(N, new QuickUWPC(N));
		//IPercolate perc = new PercolationUF(N, new QuickFind(N));
		// TODO repeatedly declare sites open, draw, & pause until the system
		// percolates
		ArrayList<Integer> alist = new ArrayList<Integer>();
		for (int i=0; i< N*N; i++){
			alist.add(i);
		}	
		Collections.shuffle(alist, ourRandom);
		for (int i=0; i< N*N; i++){
			int value=alist.get(i);
			int row= value / (N);
			int col=value % (N);
			perc.open(row,col);
			for (int j=0; j<N; j++){
				for(int k=0; k<N; k++){
					if(perc.isFull(j, k)){
						draw(j, k,  N, Color.CYAN);
						//StdDraw.setPenColor(StdDraw.BLUE);
					}
					else if (perc.isOpen(j, k)){
						//StdDraw.setPenColor(StdDraw.WHITE);
						draw(j, k,  N, Color.WHITE);
					}
					else{
						//StdDraw.setPenColor(StdDraw.BLACK);
						draw(j, k,  N, Color.BLACK);
					}	
				}
			}
			// wait DEFAULT_DELAY milliseconds and then display
			StdDraw.show(DEFAULT_DELAY);
			if(perc.percolates())
				break;
		}
		for (int j=0; j<N; j++){
			for(int k=0; k<N; k++){
				if(perc.isFull(j, k)){
					draw(j, k,  N, Color.CYAN);
					//StdDraw.setPenColor(StdDraw.BLUE);
				}
				else if (perc.isOpen(j, k)){
					//StdDraw.setPenColor(StdDraw.WHITE);
					draw(j, k,  N, Color.WHITE);
				}
				else{
					StdDraw.setPenColor(StdDraw.BLACK);
					draw(j, k,  N, Color.BLACK);
				}	
			}
		}
		// wait DEFAULT_DELAY milliseconds and then display
		StdDraw.show(DEFAULT_DELAY);

	}
}
