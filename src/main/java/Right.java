class Right<R> {

    public R value;

    Right(R rVal) {
        value = rVal;
    }

    @Override
    public String toString() {
        return "Right " + value;
    }
}
