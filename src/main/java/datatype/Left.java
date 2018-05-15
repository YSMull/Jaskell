package datatype;

public class Left<L> {

    public L value;

    public Left(L lVal) {
        value = lVal;
    }

    @Override
    public String toString() {
        return "Left " + value;
    }
}