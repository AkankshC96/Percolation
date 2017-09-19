import java.util.Arrays;

/**
 * Simulate a system to see its Percolation Threshold, but use a UnionFind
 * implementation to determine whether simulation occurs. The main idea is that
 * initially all cells of a simulated grid are each part of their own set so
 * that there will be n^2 sets in an nxn simulated grid. Finding an open cell
 * will connect the cell being marked to its neighbors --- this means that the
 * set in which the open cell is 'found' will be unioned with the sets of each
 * neighboring cell. The union/find implementation supports the 'find' and
 * 'union' typical of UF algorithms.
 * <P>
 * 
 * @author Owen Astrachan
 * @author Jeff Forbes
 * @author Akanksh Chaudhary
 *
 */

public class PercolationUF implements IPercolate {
	private final int OUT_BOUNDS = -1;
	private int [][] myGrid;
	private int myTop;
	private int myBottom;
	private IUnionFind myUF;
	private int N;
	

	/**
	 * Constructs a Percolation object for a nxn grid that uses unionThing to
	 * store sets representing the cells and the top/source and bottom/sink
	 * virtual cells
	 */
	public PercolationUF(int n, IUnionFind unionThing) {
		// TODO complete PercolationUF construction
        myGrid = new int[n][n];
        unionThing.initialize(n*n+2);
        myUF =  unionThing;
        for (int i=0; i<n; i++) {
 		   Arrays.fill(myGrid[i], BLOCKED);  
 		 }
        myBottom=n*n+1;
        myTop=n*n;
        N=n;
	}

	/**
	 * Return an index that uniquely identifies (row,col), typically an index
	 * based on row-major ordering of cells in a two-dimensional grid. However,
	 * if (row,col) is out-of-bounds, return OUT_BOUNDS.
	 */
	public int getIndex(int row, int col) {
		// TODO complete getIndex
		if (row<0||row>=myGrid.length||col<0||col>=myGrid.length){
			return OUT_BOUNDS;
		} 
		int value=row*(myGrid.length)+col;
		return value;
	}

	public void open(int i, int j) {
		// TODO complete open
		myGrid[i][j]=OPEN;
		connect(i,j);
		if(i==0){
			myUF.union(getIndex(i,j), myTop);
		}
		if(i==myGrid.length-1){
			myUF.union(getIndex(i,j), myBottom);
		}
		
		
	}

	public boolean isOpen(int i, int j) {
		// TODO complete isOpen
		if (i<0||i>=myGrid.length||j<0||j>=myGrid.length){
			throw new IndexOutOfBoundsException();
		}
		if(getIndex(i,j)==OUT_BOUNDS){
			return false;
		}
		if (myGrid[i][j]== OPEN){
			return true;
		}
		return false;
	}

	public boolean isFull(int i, int j) {
		// TODO complete isFull
		if (myUF.connected(myTop,getIndex(i,j))){
			return true;
		}
		return false;
	}

	public boolean percolates() {
		// TODO complete percolates
		return myUF.connected(myTop, myBottom);
	}

	/**
	 * Connect new site (row, col) to all adjacent open sites
	 */
	private void connect(int row, int col) {
		// TODO complete connect
		
		if (row<0||row>=myGrid.length||col<0||col>=myGrid.length){
			return;
		}
		if(isFull(row,col)||!isOpen(row, col)){ 
			return;
		}
        if (row == 0) {
            myUF.union(getIndex(row,col), myTop);
        }
        if (row == N-1) {
        	myUF.union(getIndex(row,col), myBottom);
        }

        if (col >= 1 && isOpen(row, col - 1)) {
        	myUF.union(getIndex(row,col), getIndex(row,col-1));
        }
        if (col <= N-2 && isOpen(row, col + 1)) {
        	myUF.union(getIndex(row,col), getIndex(row,col+1));
        }
        if (row >= 1 && isOpen(row - 1, col)) {
        	myUF.union(getIndex(row,col), getIndex(row-1,col));
        }
        if (row <= N-2 && isOpen(row + 1, col)) {
        	myUF.union(getIndex(row,col), getIndex(row+1,col));
        }
	}

}
