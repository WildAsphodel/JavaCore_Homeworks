package Lesson3;

public class Apple extends Fruit{
    private float size = 1.0f;

    public float getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "size=" + size +
                '}';
    }
}
