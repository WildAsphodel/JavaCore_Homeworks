package Lesson1;

public class Main {
    public static void main(String[] args) {
        Team team = new Team(new Cat("Barsik", 5), new Cat("Parsik", 50),
                new Cat("Narsik", 12), new Cat("Larsik", 25));
        System.out.println(team);
        Course c = new Course(3, 10, 20);
        c.doIt(team);
        team.showResults();
    }
}
