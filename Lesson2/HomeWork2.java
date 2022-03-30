package Lesson2;

public class HomeWork2 {
    public static void main(String[] args) {
        String[][] array = {{"1", "9", "1", "1"}, {"2", "2", "2", "2"}, {"f", "3", "3", "3"}, {"4", "4", "4", "4"}};
        try {
            summingElementsInArrays(array);
        } catch (MyArraySizeException e) {
            System.out.println(e);
            ;
        } catch (MyArrayDataException e) {
            System.out.println(e);
        }
    }

    static void summingElementsInArrays(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        int[][] intArray = new int[4][4];
        if (array.length != 4 || array[0].length != 4 || array[1].length != 4 || array[2].length != 4 || array[3].length != 4) {
            throw new MyArraySizeException("Массив неверного размера");
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                try {
                    intArray[i][j] = Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверный тип данных '" + array[i][j] + "' в ячейке: [" + i + "] [" + j + "]");
                }
                    sum += intArray[i][j];
            }
        }
        System.out.println(sum);
    }
}
