class Left<L> {

    public L value;

    Left(L lVal) {
        value = lVal;
    }

    @Override
    public String toString() {
        return "Left " + value;
    }
}