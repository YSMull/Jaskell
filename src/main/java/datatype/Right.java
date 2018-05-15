package datatype;

public class Right<R> {

    public R value;

    public Right(R rVal) {
        value = rVal;
    }

    @Override
    public String toString() {
        return "Right " + value;
    }
}
