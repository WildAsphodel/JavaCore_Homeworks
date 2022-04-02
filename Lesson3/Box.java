package Lesson3;

import java.util.ArrayList;

public class Box<T extends Fruit> implements Comparable<T>{
    private ArrayList<T> arrayFruit;

    public Box(ArrayList<T> arrayFruit) {
        this.arrayFruit = arrayFruit;
    }

    public ArrayList<T> getArrayFruit() {
        return arrayFruit;
    }

    public void setArrayFruit(ArrayList<T> arrayFruit) {
        this.arrayFruit = arrayFruit;
    }

    @Override
    public String toString() {
        return "Box{" +
                "arrayFruit=" + arrayFruit +
                '}';
    }

    public float getWeight() {
        return arrayFruit.size() * arrayFruit.get(0).getSize();
    }



    @Override
    public int compareTo(T o) {
        return 0;
    }

    public boolean compare(Box<?> o) {
        return (this.getWeight() - o.getWeight() == 0);
    }

    public void replaceFruits(Box<T> o) {
        o.arrayFruit = (ArrayList<T>) this.arrayFruit.clone();
        this.arrayFruit.clear();
    }

    public static void main(String[] args) {
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple());
        apples.add(new Apple());
        apples.add(new Apple());
        apples.add(new Apple());
        apples.add(new Apple());
        apples.add(new Apple());
        ArrayList<Apple> apples2 = new ArrayList<>();
        ArrayList<Orange> oranges = new ArrayList<>();
        oranges.add(new Orange());
        oranges.add(new Orange());
        oranges.add(new Orange());
        oranges.add(new Orange());
        Box<Apple> boxApple= new Box<>(apples);
        Box<Apple> boxApple2= new Box<>(apples2);
        Box<Orange> boxOrange= new Box<>(oranges);
        System.out.println(boxApple.compare(boxOrange));
        System.out.println(boxApple2);
        System.out.println(boxApple);
        boxApple.replaceFruits(boxApple2);
        System.out.println(boxApple2);
        System.out.println(boxApple);
    }
}
