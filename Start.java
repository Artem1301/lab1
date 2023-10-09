import java.util.Random;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;


public class Start {
    public static void main(String[] args) {
        Matrix obj = new Matrix();
        obj.getElement(3, 3);
        obj.multiMatrix();
        obj.getIndex(4);
//      obj.sumMatrix();

        // Initialization for List
        SparseList<Integer> sparseList = new SparseList<>();
        sparseList.set(0, 5);
        sparseList.set(1, 10);
        sparseList.set(2, 15);
        sparseList.set(3, 20);

        System.out.println("Value of an element in the List for index: " + sparseList.get(1));
        System.out.println("Index of an element in the List for value: " + sparseList.findFirstIndex(15));
//      sparseList.searchCondition(5, 20);    
        System.out.println(sparseList.containsValue(10));
    }
}

class Matrix extends Data {
    void showMatrix() {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(" " + mtrx1[i][j]);
            }
            System.out.println(" ");
        }
    }

    void getElement(int row, int col) {
        System.out.println(mtrx1[row][col]);
    }

    void getIndex(int number) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (mtrx1[i][j] == number) {
                    System.out.println("Index of number " + number + " is [" + i + "]" + "[" + j + "]");
                }
            }
        }
    }

    void sumMatrix() {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                mtrx1[i][j] += mtrx2[i][j];
            }
        }
        showMatrix();
    }

    void multiMatrix() {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                mtrx1[i][j] *= mtrx2[i][j];
            }
        }
        showMatrix();
    }
}

class SparseList<T> {
        private Map<Integer, T> elements;

        public SparseList() {
            elements = new HashMap<>();
        }

        public void set(int index, T value) {
            elements.put(index, value);
        }

        public T get(int index) {
            return elements.getOrDefault(index, null);
        }

        public boolean containsValue(T value) {
            return elements.containsValue(value);
        }

        public int findFirstIndex(T value) {
            for (Entry<Integer, T> entry : elements.entrySet()) {
                if (entry.getValue().equals(value)) {
                    return entry.getKey();
                }
            }
            return -1;
        }

        public void searchCondition(int min, int max) {
            int count = 0;

            for (Map.Entry<Integer, T> entry : elements.entrySet()) {
                if (entry.getValue() instanceof Integer) {
                    Integer intValue = (Integer) entry.getValue();
                    if (intValue > min && intValue < max) {
                        count++;
                    }
                }
            }

           System.out.println(count);
        }
    }

class Data {
    final int num = 4;
    int[][] mtrx1 = new int[num][num];
    int[][] mtrx2 = new int[num][num];
    Random random = new Random();

    // An initialization block for Matrix
    {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                mtrx1[i][j] = random.nextInt(10);
                mtrx2[i][j] = random.nextInt(10);
            }
        }
    }
}
