package Lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
List<Student> students = new ArrayList<>();
        students.add(new Student("Ivan", Arrays.asList(
                new Course("IT"),
                new Course("Math"),
                new Course("Linux"),
                new Course("HTML/CSS"),
                new Course("SQL")
        )));
        students.add(new Student("Petr", Arrays.asList(
                new Course("IT"),
                new Course("Java"),
                new Course("SQL")
        )));
        students.add(new Student("Anna", Arrays.asList(
                new Course("Java"),
                new Course("HTML/CSS"),
                new Course("Linux"),
                new Course("Math")
        )));
        students.add(new Student("Maria", Arrays.asList(
                new Course("IT"),
                new Course("Math"),
                new Course("Java"),
                new Course("HTML/CSS")
        )));

        //1
        System.out.println(students.stream()
                .map(s -> s.getCourseList())
                .flatMap(c -> c.stream())
                .collect(Collectors.toSet()));
        //2
        System.out.println(students.stream()
                .sorted((s1, s2) -> - s1.getCourseList().size() + s2.getCourseList().size())
                .limit(3)
                .collect(Collectors.toList()));
        // 3
        Course course = new Course("Java");
        System.out.println(students.stream()
                .filter(s -> s.getCourseList().contains(course))
                .collect(Collectors.toList()));
    }
}
