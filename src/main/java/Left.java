class Left<L> {

    public L value;

    Left(L lVal) {
        value = lVal;
    }

    public Right getRight() {
        return null;
    }

    public Left<L> getLeft() {
        return this;
    }

    @Override
    public String toString() {
        return "Left " + value;
    }
}