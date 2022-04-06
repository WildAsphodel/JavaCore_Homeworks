package Lesson4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

public class Words {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("book", "cat", "word", "cat", "fox", "tree", "java", "fox", "lesson", "map", "list", "fox", "fox"));
        Set<String> uniqueWords = new HashSet<>(words);
        System.out.println(uniqueWords);
        counterWords(words, uniqueWords);
    }
    public static void counterWords(ArrayList<String> words, Set<String> uniqueWords) {
        int counter = 0;
        String word;
                for (String s: uniqueWords) {
                    word = s;
                    counter = Collections.frequency(words, word);
                System.out.println("Word " + word + " repeats " + counter + " times");
        }
    }
}
