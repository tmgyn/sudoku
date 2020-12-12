import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Sudoku {

    private final Matrix matrix = new Matrix(9);

    public Sudoku load(File file) {
        try (Scanner scanner = new Scanner(file)) {
            List<String> tokens = new ArrayList<>();
            while (scanner.hasNext()) {
                tokens.addAll(Arrays.asList(scanner.nextLine().split(" ")));
            }

            for (String token : tokens) {
                int x = Integer.valueOf(Character.toString(token.charAt(0)));
                int y = Integer.valueOf(Character.toString(token.charAt(1)));
                int value = Integer.valueOf(Character.toString(token.charAt(2)));
                matrix.setValue(x, y, value);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Failed to load sudoku", e);
        }
        return this;
    }


    public Sudoku loadWithStream(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {

            byte[] bytes = fis.readAllBytes();
            String decodedBytes = new String(bytes, StandardCharsets.UTF_8);
            String[] tokensArr = decodedBytes.split(" ");
            List<String> tokens = Arrays.asList(tokensArr);


            // String buffer = "";
            // int read;
            // while (!Objects.equals((read = fis.read()), -1)) {
            // if (!Character.toString(read).equals(" ")) {
            // buffer = buffer + Character.toString(read);
            // } else {
            // tokens.add(buffer);
            // buffer = "";
            // }
            // }
            // tokens.add(buffer);

            for (String token : tokens) {
                int x = Integer.valueOf(Character.toString(token.charAt(0)));
                int y = Integer.valueOf(Character.toString(token.charAt(1)));
                int value = Integer.valueOf(Character.toString(token.charAt(2)));
                matrix.setValue(x, y, value);
            }
            return this;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load Sudoku with InputStream", e);
        }
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public Sudoku print() {
        matrix.print();
        return this;
    }

    public Sudoku isValidMatrix() {
        for (int i = 0; i < matrix.getSize(); i++) {
            isValidCol(i);
            isValidRow(i);
        }
        return this;
    }

    public Sudoku isValidMatrix3x3(int pos) {
        Set<Integer> values = getSet(matrix.getSize());
        Matrix matrix3x3 = getMatrix3x3(pos);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!values.remove(matrix3x3.getValue(x, y))) {
                    throw new IllegalStateException("Matrix3x3 is invalid.");
                }
            }
        }
        if (!values.isEmpty()) {
            throw new IllegalStateException("Matrix3x3 is invalid.");
        }
        return this;
    }

    private Matrix getMatrix3x3(int pos) {
        Matrix matrix3x3 = new Matrix(3);
        pos = (pos - 1);
        int xPos = (pos % 3) * 3;
        int yPos = pos - (pos % 3);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                matrix3x3.setValue(x, y, matrix.getValue(x + xPos, y + yPos));
            }
        }
        return matrix3x3;
    }

    private void isValidRow(int y) {
        Set<Integer> values = getSet(matrix.getSize());
        for (int x = 0; x < matrix.getSize(); x++) {
            if (!values.remove(matrix.getValue(x, y))) {
                throw new IllegalStateException("matrix is invalid.");
            }
        }

        if (!values.isEmpty()) {
            throw new IllegalStateException("matrix is invalid.");
        }
    }

    private void isValidCol(int x) {
        Set<Integer> values = getSet(matrix.getSize());
        for (int y = 0; y < matrix.getSize(); y++) {
            if (!values.remove(matrix.getValue(x, y))) {
                throw new IllegalStateException("matrix is invalid.");
            }
        }
        if (!values.isEmpty()) {
            throw new IllegalStateException("matrix is invalid.");
        }
    }

    private static Set<Integer> getSet(int size) {
        Set<Integer> values = new HashSet<>();
        for (int i = 1; i <= size; i++) {
            values.add(i);
        }
        return values;
    }

}
