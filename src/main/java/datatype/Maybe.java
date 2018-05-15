package datatype;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Maybe<T> {

    private T value;

    private T getValue() {
        return value;
    }

    private Maybe(T val) {
        this.value = val;
    }

    public static final Maybe Nothing = new Maybe<Object>(null);

    public static <T> Maybe<T> just(T val) {
        return new Maybe<>(val);
    }

    public static <T> T fromJust(Maybe<T> m) {
        if (!isNothing(m)) {
            return m.getValue();
        } else {
            throw new RuntimeException("Maybe.fromJust: Nothing");
        }
    }

    public static <T> T fromMaybe(T defaultVal, Maybe<T> m) {
        if (isNothing(m)) {
            return defaultVal;
        } else {
            return fromJust(m);
        }
    }

    public static boolean isJust(Maybe<?> maybe) {
        return maybe != Nothing;
    }

    public static boolean isNothing(Maybe<?> maybe) {
        return maybe == Nothing;
    }

    public static <A, B> B maybe(B b, Function<A, B> f, Maybe<A> m) {
        if (isNothing(m)) {
            return b;
        } else {
            return f.apply(fromJust(m));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Maybe<T> listToMaybe(T[] arr) {
        if (0 == arr.length) {
            return Maybe.Nothing;
        } else {
            return Maybe.just(arr[0]);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] maybeToList(Maybe<T> m, Class<T> c) {
        if (isNothing(m)) {
            return (T[]) Array.newInstance(c, 0);
        } else {
            T[] arr = (T[]) Array.newInstance(c, 1);
            arr[0] = fromJust(m);
            return arr;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] catMaybes(Maybe<T>[] m, Class<T> c) {
        List<T> t = Arrays.stream(m).filter(Maybe::isJust).map(Maybe::fromJust).collect(Collectors.toList());
        T[] res = (T[]) Array.newInstance(c, t.size());
        for (int i = 0; i < res.length; i++) {
            res[i] = t.get(i);
        }
        return res;
    }

    //mapMaybe :: (a -> Maybe b) -> [a] -> [b]
//    public static <A,B> B[] mapMaybe(Function<A, Maybe<B>> f, A[] arr) {
//        catMaybes(Arrays.stream(arr).map(a->f(a)))
//    }

}
