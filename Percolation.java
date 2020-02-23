import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] id;
    private final WeightedQuickUnionUF arr;
    private final WeightedQuickUnionUF brr;
    private final int number;
    private int count = 0;
    private boolean flag;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("the parameter should be larger than 0");
        }
        number = n;
        arr = new WeightedQuickUnionUF(number * number + 1);
        brr = new WeightedQuickUnionUF(number * number + 1);
        id = new boolean[number * number + 1];
        flag = false;
        for (int i = 0; i < number * number + 1; i++) {
            id[i] = false;
        }
    }

    private int num(int row, int col) {
        return (row - 1) * number + col - 1;
    }

    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > number || col > number) {
            throw new IllegalArgumentException("the parameter of row or col is illegal");
        }
        if (!id[num(row, col)]) {
            id[num(row, col)] = true;

            if (row == 1) {
                if (id[num(row, col) + number]) {
                    arr.union(num(row, col), num(row, col) + number);
                    brr.union(num(row, col), num(row, col) + number);
                }
                arr.union((num(row, col)), number * number);
            }
            if (row == number) {
                if (number != 1 && id[num(row, col) - number]) {
                    arr.union(num(row, col), num(row, col) - number);
                    brr.union(num(row, col), num(row, col) - number);
                }
                brr.union(num(row, col), number * number);
            }

            if (row > 1 && id[num(row, col) - number]) {
                arr.union(num(row, col), num(row, col) - number);
                brr.union(num(row, col), num(row, col) - number);
            }

            if (row < number && id[num(row, col) + number]) {
                arr.union(num(row, col), num(row, col) + number);
                brr.union(num(row, col), num(row, col) + number);
            }

            if (num(row, col) % number != 0 && id[num(row, col) - 1]) {
                arr.union(num(row, col), num(row, col) - 1);
                brr.union(num(row, col), num(row, col) - 1);
            }

            if (num(row, col) % number != number - 1 && id[num(row, col) + 1]) {
                arr.union(num(row, col), num(row, col) + 1);
                brr.union(num(row, col), num(row, col) + 1);
            }

            if (arr.find(num(row, col)) == arr.find(number * number)
                    && brr.find(num(row, col)) == brr.find(number * number)) {
                flag = true;
            }
            count++;
        }

    }


    public boolean isOpen(int row, int col) {

        if (row < 1 || col < 1 || row > number || col > number) {
            throw new IllegalArgumentException("the parameter should be larger than 0");
        }
        else return id[num(row, col)];
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return flag;
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > number || col > number) {
            throw new IllegalArgumentException("the parameter should be larger than 0");
        }
        return arr.find(num(row, col)) == arr.find(number * number);

    }


    public static void main(String[] args) {
        // no body


    }
}
