import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Either<L, R> {

    private Left<L> left;
    private Right<R> right;

    Either(Left<L> lVal, Right<R> rVal) {
        left = lVal;
        right = rVal;
    }

    Either(Left<L> lVal) {
        left = lVal;
    }

    Either(Right<R> rVal) {
        right = rVal;
    }

    public Right<R> getRight() {
        return right;
    }

    public Left<L> getLeft() {
        return left;
    }

    public static boolean isLeft(Left<?> __) {
        return true;
    }

    public static boolean isLeft(Right<?> __) {
        return false;
    }

    public static boolean isLeft(Either<?, ?> either) {
        return either.getLeft() != null;
    }

    public static boolean isRight(Right<?> __) {
        return true;
    }

    public static boolean isRight(Left<?> __) {
        return false;
    }

    public static boolean isRight(Either<?, ?> either) {
        return either.getRight() != null;
    }

    public static <L> L fromLeft(L __, Left<L> left) {
        return left.value;
    }

    public static <L> L fromLeft(L defaultVal, Right<?> __) {
        return defaultVal;
    }

    public static <L> L fromLeft(L defaultVal, Either<L, ?> either) {
        return isLeft(either) ? either.getLeft().value : defaultVal;
    }

    public static <R> R fromRight(R __, Right<R> right) {
        return right.value;
    }

    public static <R> R fromRight(R defaultVal, Left<?> __) {
        return defaultVal;
    }

    public static <R> R fromRight(R defaultVal, Either<?, R> either) {
        return isRight(either) ? either.getRight().value : defaultVal;
    }

    public static <L> L[] lefts(Either<L, ?>[] eithers, Class<L> c) {
        List<L> eitherLeft = Arrays.stream(eithers).filter(Either::isLeft).map(e -> e.getLeft().value).collect(Collectors.toList());
        @SuppressWarnings("unchecked") final L[] res = (L[]) Array.newInstance(c, eitherLeft.size());
        for (int i = 0; i < eitherLeft.size(); i++) {
            res[i] = eitherLeft.get(i);
        }
        return res;
    }

    public static <R> R[] rights(Either<?, R>[] eithers, Class<R> c) {
        List<R> eitherLeft = Arrays.stream(eithers).filter(Either::isRight).map(e -> e.getRight().value).collect(Collectors.toList());
        @SuppressWarnings("unchecked") final R[] res = (R[]) Array.newInstance(c, eitherLeft.size());
        for (int i = 0; i < eitherLeft.size(); i++) {
            res[i] = eitherLeft.get(i);
        }
        return res;
    }

    public static <L, R, U> U either(Function<L, U> f1, Function<R, U> __, Left<L> left) {
        return f1.apply(left.value);
    }

    public static <L, R, U> U either(Function<L, U> __, Function<R, U> f2, Right<R> right) {
        return f2.apply(right.value);
    }

    public static <R, L, U> U either(Function<L, U> f1, Function<R, U> f2, Either<L, R> either) {
        if (isLeft(either)) {
            return f1.apply(either.getLeft().value);
        } else {
            return f2.apply(either.getRight().value);
        }
    }

    @Override
    public String toString() {
        if (isLeft(this) && isRight((this))) {
            return this.getLeft() + ", " + this.getRight();
        } else if (isLeft(this) && !isRight(this)) {
            return this.getLeft().toString();
        } else {
            return this.getRight().toString();
        }
    }


}

