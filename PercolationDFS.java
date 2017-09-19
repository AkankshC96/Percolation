import java.util.Arrays;

import princeton.*;

/**
 * Simulate percolation thresholds for a grid-base system using depth-first-search,
 * aka 'flood-fill' techniques for determining if the top of a grid is connected
 * to the bottom of a grid.
 * <P>
 * Modified from the COS 226 Princeton code for use at Duke. The modifications
 * consist of supporting the <code>IPercolate</code> interface, renaming methods
 * and fields to be more consistent with Java/Duke standards and rewriting code
 * to reflect the DFS/flood-fill techniques used in discussion at Duke.
 * <P>
 * @author Kevin Wayne, wayne@cs.princeton.edu
 * @author Professor Owen Astrachan, ola@cs.duke.edu
 * @author Professor Jeff Forbes, forbes@cs.duke.edu
 * @author Akanksh Chaudhary
 */


public class PercolationDFS implements IPercolate {
	// possible instance variable for storing grid state
	public int[][] myGrid;

	/**
	 * Initialize a grid so that all cells are blocked.
	 * 
	 * @param n
	 *            is the size of the simulated (square) grid
	 */
	public PercolationDFS(int n) {
		// TODO complete constructor and add necessary instance variables
		 myGrid = new int[n][n];
		 for (int i=0; i<n; i++) {
		   Arrays.fill(myGrid[i], BLOCKED);  
		 }
	}

	public void open(int i, int j) {
		// TODO complete open
		myGrid[i][j] = OPEN;
		clean();
		for(int k=0;k<myGrid.length; k++){
			dfs(0,k);
		}
	}
	public void clean(){
		for(int i=0;i<myGrid.length; i++){
			for(int j=0;j<myGrid.length; j++){
				if (myGrid[i][j]==FULL){
					myGrid[i][j]=OPEN;
				}
			}
		}
	}

	public boolean isOpen(int i, int j) {
		// TODO complete isOpen
		if (myGrid[i][j] == OPEN){
			return true;
		}
		return false;
	}

	public boolean isFull(int i, int j) {
		// TODO complete isFull
		if (myGrid[i][j] == FULL ){
			return true;
		}
		return false;
	}

	public boolean percolates() {
		// TODO: run DFS to find all full sites
		for (int col=0;col < myGrid[0].length;col++){
			dfs(0,col); 
		}
		for (int i = 0; i < myGrid.length; i++) {
            if (myGrid[myGrid.length-1][i]==FULL){ 
            	return true;
        }
		}
		return false;
	}

	/**
	 * Private helper method to mark all cells that are open and reachable from
	 * (row,col).
	 * 
	 * @param row
	 *            is the row coordinate of the cell being checked/marked
	 * @param col
	 *            is the col coordinate of the cell being checked/marked
	 */
	private void dfs(int row, int col) {
		// TODO: complete dfs
		if (row<0||row>=myGrid.length||col<0||col>=myGrid.length){
			return;
		}
		if(isFull(row,col)||!isOpen(row, col)){ 
			return;
		}
		myGrid[row][col]= FULL;
		dfs(row-1,col);
		dfs(row+1,col);
		dfs(row,col-1);
		dfs(row,col+1);
	}

}
