class Right<R> {

    public R value;

    Right(R rVal) {
        value = rVal;
    }

    public Right<R> getRight() {
        return this;
    }

    public Left getLeft() {
        return null;
    }

    @Override
    public String toString() {
        return "Right " + value;
    }
}
