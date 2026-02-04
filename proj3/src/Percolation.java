import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // TODO: Add any necessary instance variables.
    private int gridSize;           // 网格大小 N
    private boolean[] openSites;    // 记录每个格子是否打开
    private WeightedQuickUnionUF ufFull; // 并查集
    private WeightedQuickUnionUF ufPerc;
    private int openCount;          // 已打开的格子数量
    private int virtualTop;         // 虚拟顶部节点索引
    private int virtualBottom;      // 虚拟底部节点索引

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N <= 0) {
            throw new IllegalArgumentException("N必须为正数");
        }
        gridSize = N;
        openSites = new boolean[N * N]; // 所有元素自动初始化为 false
        ufFull = new WeightedQuickUnionUF(N * N + 1);
        ufPerc = new WeightedQuickUnionUF(N * N + 2);
        openCount = 0;
        virtualTop = N * N;
        virtualBottom = N * N + 1;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        if (openSites[xyTo1D(row, col)]) {
            return;
        }

        openSites[xyTo1D(row, col)] = true;

        openCount++;

        if (row == 0) {
            ufPerc.union(xyTo1D(row, col), virtualTop);
            ufFull.union(xyTo1D(row, col), virtualTop);
        }

        if (row == gridSize - 1) {
            ufPerc.union(xyTo1D(row, col), virtualBottom);
        }

        //上
        if (isValid(row - 1, col) && isOpen(row - 1, col)) {
            ufPerc.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            ufFull.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }

        //下
        if (isValid(row + 1, col) && isOpen(row + 1, col)) {
            ufPerc.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            ufFull.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }

        //左
        if (isValid(row, col - 1) && isOpen(row, col - 1)) {
            ufPerc.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            ufFull.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }

        //右
        if (isValid(row, col + 1) && isOpen(row, col + 1)) {
            ufPerc.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            ufFull.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }


    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return openSites[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return ufFull.connected(xyTo1D(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openCount;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return ufPerc.connected(virtualTop, virtualBottom);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    private int xyTo1D(int row, int col) {
        return gridSize * row + col;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row <= gridSize - 1 && col >= 0 && col <= gridSize - 1;
    }

}
