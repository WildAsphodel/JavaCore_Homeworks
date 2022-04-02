package Lesson3;

public class Orange extends Fruit{
    private float size = 1.5f;

    public float getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "size=" + size +
                '}';
    }
}
