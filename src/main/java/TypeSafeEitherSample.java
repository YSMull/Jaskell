import java.util.Arrays;

public class TypeSafeEitherSample {

    public static void main(String[] args) {

        Left<Integer> left = new Left<>(123);
        Right<String> right = new Right<>("hello");
        System.out.println(left); // Left 123
        System.out.println(right); // Right hello

        System.out.println(Either.isLeft(left)); // true
        System.out.println(Either.isLeft(right)); // false
        System.out.println(Either.isRight(left)); // false
        System.out.println(Either.isRight(right)); // true

        System.out.println(Either.fromLeft(456, left)); // 123
        System.out.println(Either.fromLeft(456, right)); // 456

        System.out.println(Either.fromRight("world", left)); // "world"
        System.out.println(Either.fromRight("world", right)); // "hello"

        Integer doubleLeft =  Either.either(((Integer n) -> 2 * n), String::length, new Left<>(123));
        System.out.println(doubleLeft); // 246
        Integer lengthRight = Either.either(((Integer n) -> 2*n), String::length, new Right<>("hello"));
        System.out.println(lengthRight); // 5

        System.out.println(test(1)); // Left 123
        System.out.println(test(-1)); // Right Hello

    }

    public static Either<Integer, String> test(int a) {
        if (a > 0) {
            return new Either<>(new Left<>(123));
        } else {
            return new Either<>(new Right<>("Hello"));
        }
    }
}

