package Lesson1;

public class Course {
    private int length;
    int[] pools;

    public Course(int pool1, int pool2, int pool3) {
        this.pools = new int[]{pool1, pool2, pool3};
    }

    public void doIt(Team team) {
        for(int i = 0; i < pools.length; i++) {
            for(int j = 0; j < team.cats.length; j++) {
                if (pools[i] > team.cats[j].getMaxSwim()) {
                    team.cats[j].status = false;
                }
            }
        }
    }
}