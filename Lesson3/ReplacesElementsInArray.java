package Lesson3;

import java.util.ArrayList;

public class ReplacesElementsInArray {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("2");
        System.out.println(arr.get(0) + " " + arr.get(1));
        changePlaces(arr, 0, 1);
        System.out.println(arr.get(0) + " " + arr.get(1));
    }

    public static void changePlaces(ArrayList<String> arr, int a, int b) {
        String temp = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, temp);
    }
}
