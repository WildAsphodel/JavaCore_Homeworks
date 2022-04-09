package Lesson5;

import java.io.*;

public class AppData {
    private String[] header;
    private int[][] data;

    public AppData(String[] header, int[][] data) {
        this.header = header;
        this.data = data;
    }

    public AppData() {
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public int[][] getData() {
        return data;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return header[0] + ";" + header[1] + ";" + header[2] + "\n" +
                data[0][0] + ";" + data[0][1] + ";" + data[0][2] + "\n" +
                data[1][0] + ";" + data[1][1] + ";" + data[1][2];
    }

    public static void main(String[] args) {
        AppData appData = new AppData();
        int[][] numbers = {{100, 200, 123}, {300, 400, 500}};
        String[] head = {"value1", "value2", "value3"};
        appData.setData(numbers);
        appData.setHeader(head);
        try (PrintWriter printWriter = new PrintWriter("data.csv")) {
            printWriter.println(appData.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data.csv"))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
