Name: Akanksh Chaudhary
Hours Spent: 40.5
________________________________________________________________________________________________
ANALYSIS:

Part 1: How does doubling N affect the running time?
For PercolationDFS, when N=50 and T=50, the running time is 0.266 seconds. When N=100 and T=50, the running time is 2.869 seconds. When N=200 and T=50, the running time is 37.729 seconds. The big Oh notation is O(n^4). This is because doubling the N, changes the running time by around 11 and 13 times every doubling time respectively.I approximated this to 16 times increase to get the notation.
For PercolationUF with QuickFind, when N=50 and T=50, the running time is 0.175 seconds. When N=100 and T=50, the running time is 2.328 seconds. When N=200 and T=50, the running time is 36.831 seconds. The big Oh notation is O(n^4). This is because doubling the N, changes the running time by around 13 and 16 times every doubling time respectively. I approximated this to 16 times increase to get the notation.  
For PercolationUF with UWPC, when N=50 and T=50, the running time is 0.081 seconds. When N=100 and T=50, the running time is 0.338 seconds. The big Oh notation is O(n^2). This is because doubling the N, changes the running time by four.I approximated this time increase to get the notation.

Part 2: How does doubling T affect the running time?
For PercolationDFS, when N=50 and T=50, the running time is 0.266 seconds. When N=50 and T=100, the running time is 0.469 seconds. When N=50 and T=200, the running time is 0.895 seconds.The big Oh notation is O(n).This is because doubling the N, changes the running time by twice approximately every time.
For PercolationUF with QuickFind, when N=50 and T=50, the running time is 0.175 seconds. When N=50 and T=100, the running time is 0.331 seconds. When N=50 and T=200, the running time is 0.639 seconds. The big Oh notation is O(n).This is because doubling the N, changes the running time by twice approximately every time.  
For PercolationUF with UWPC, when N=50 and T=50, the running time is 0.081 seconds. When N=50 and T=100, the running time is 0.12 seconds. When N=50 and T=200, the running time is 0.194 seconds. The big Oh notation is O(n).This is because doubling the N, changes the running time by twice approximately every time.

Part 3: Measure running time (using calls to System.currentTimeMillis)
of the three versions of your program (DFS, Quick Find, and weighted quick
union with path compression).
3A DFS: When N=50, T=50, the mean running time is 0.00532 seconds. The total time is 0.266 seconds.
3B Quick Find:When N=50, T=50, the mean running time is 0.0035 seconds. The total time is 0.175 seconds.
3C Weighted quick union with path compression:When N=50, T=50, the mean running time is 0.00162 seconds.The total time is 0.081 seconds.

Part 4: Give a formula (using Big-Oh notation) of the running time on your computer (in seconds) as a function of both N and T.
DFS:O(N^4*T) is the running time because doubling N increases the running time by approximately 16, so it is N^4. Doubling T doubles the running time, so you multiply the notation by T. This is derived from problem 2 and 3.
QuickFind:O(N^4*T) is the running time because doubling N increases the running time by approximately 16, so it is N^4. Doubling T doubles the running time, so you multiply the notation by T. This is derived from problem 2 and 3.
UWPC:O(N^2*T) is the running time because doubling N increases the running time by approximately 4, so it is N^2. Doubling T doubles the running time, so you multiply the notation by T. This is derived from problem 2 and 3.

Part 5: Estimate the largest experiment you could  perform in one
[minute, day, year] assuming your computer has enough memory.
5A 1 minute:
For DFS, assuming constant T=50, the max N could be estimated by solving the (base case=0.266)*N^4=60 seconds. When we solve for N, we multiply it by 50, since that was the base case. We get around N=195.
For QuickFind, assuming constant T=50, the max N could be estimated by solving the (base case=0.175)*N^4=60 seconds. When we solve for N, we multiply it by 50, since that was the base case. We get around N=215.
For UWPC, assuming constant T=50, the max N could be estimated by solving the (base case=0.081)*N^2=60 seconds. When we solve for N, we multiply it by 50, since that was the base case. We get N=1360.   
5B 1 day:
For DFS, assuming constant T=50, the max N could be estimated by solving the (base case=0.266)*N^4=86400 seconds. When we solve for N, we multiply it by 50, since that was the base case. We get around N=1200.
For QuickFind, assuming constant T=50, the max N could be estimated by solving the (base case=0.175)*N^4=86400 seconds. When we solve for N, we multiply it by 50, since that was the base case. We get around N=1325.
For UWPC, assuming constant T=50, the max N could be estimated by solving the (base case=0.081)*N^2=86400 seconds. When we solve for N, we multiply it by 50, since that was the base case. We get N=51650. 
5C 1 year:
For DFS, assuming constant T=50, the max N could be estimated by solving the (base case=0.266)*N^4=31536000 seconds. When we solve for N, we multiply it by 50, since that was the base case. We get around N=5435.
For QuickFind, assuming constant T=50, the max N could be estimated by solving the (base case=0.175)*N^4=31536000 seconds. When we solve for N, we multiply it by 50, since that was the base case. We get around N=5793.
For UWPC, assuming constant T=50, the max N could be estimated by solving the (base case=0.081)*N^2=31536000 seconds. When we solve for N, we multiply it by 50, since that was the base case. We get N=986575. 

Part 6: Give a formula (using Big-Oh notation) that describes the amount
of memory (in bytes) that your IPercolate implementation consumes as a function of N.
6A DFS:
myGrid takes O(n^2) memory. Thus the total is O(n^2) where n is 4 bytes. we get O(16 bytes).
6B Quick Find:
myID takes O(n) and myGrid still takes O(n^2).
Thus the total is O(n^2) where n is 4 bytes. we get O(16 bytes).
6C Weighted quick union with path compression:
mySize takes O(n) memory and myID takes O(n) and myGrid still takes O(n^2).
Thus the total is O(n^2) where n is 4 bytes. we get O(16 bytes).
