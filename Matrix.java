import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private final List<int[]> matrix = new ArrayList<>();

    public Matrix(int size) {
        for (int x = 0; x < size; x++) {
            matrix.add(x, new int[size]);
        }
    }

    public int getSize() {
        return matrix.size();
    }

    public int getValue(int x, int y) {
        return matrix.get(x)[y];
    }

    public void setValue(int x, int y, int value) {
        matrix.get(x)[y] = value;
    }

    public Matrix transpose() {
        int size = matrix.size();
        Matrix t = new Matrix(size);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                t.setValue(x, y, this.getValue(y, x));
            }
        }
        return t;
    }

    public void print() {
        StringBuilder b = new StringBuilder();
        for (int x = 0; x < matrix.size(); x++) {
            for (int y = 0; y < matrix.size(); y++) {
                b.append(matrix.get(x)[y]);
                b.append("\t");
            }
            b.append("\n");
        }
        System.out.println(b);
    }
}
