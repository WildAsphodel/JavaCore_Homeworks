package Lesson1;

import java.util.Objects;

public class Cat {
    private String name;
    private int maxSwim;
    boolean status = true;

    public Cat(String name, int maxSwim) {
        this.name = name;
        this.maxSwim = maxSwim;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", maxSwim=" + maxSwim +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getMaxSwim() {
        return maxSwim;
    }
}
