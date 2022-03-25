package Lesson1;

public class Team {
    private String teamName = "CatsTeam";
    private String name;
    private int maxSwim;
    Cat[] cats;
    public Team(Cat cat1, Cat cat2, Cat cat3, Cat cat4) {
        this.cats  = new Cat[] {cat1, cat2, cat3, cat4};
    }

    @Override
    public String toString() {
        return "Team " + teamName +
                ": " + '\n' + cats[0].getName() + ", max SwimDistance " + cats[0].getMaxSwim() + '\n'
                + cats[1].getName() + ", max SwimDistance " + cats[1].getMaxSwim() + '\n'
                + cats[2].getName() + ", max SwimDistance " + cats[2].getMaxSwim() + '\n'
                + cats[3].getName() + ", max SwimDistance " + cats[3].getMaxSwim()
                ;
    }
    public void showResults() {
        for (int i = 0; i < cats.length; i++) {
            if (cats[i].status) {
                System.out.println(cats[i].getName() + " successfully swam!");
            }
        }
    }

}
