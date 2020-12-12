import java.io.File;

public class Main {

    public static void main(String[] args) {
        new Sudoku()
        .loadWithStream(new File("example.txt"))
        .print()
        .isValidMatrix()
        .getMatrix()
        .transpose()
        .print();
    }

}
