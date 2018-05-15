import java.util.Arrays;

public class EitherSample {


    public static void main(String[] args) {

        Left<Integer> left = new Left<>(123);
        Right<String> right = new Right<>("hello");
        System.out.println(left); // Left 123
        System.out.println(right); // Right hello

        Either<Integer, String> either = new Either<>(left, right);
        System.out.println(either); // Left 123, Right hello


        // test isLeft & isRight
        // isLeft :: Either a b -> Bool
        // isRight :: Either a b -> Bool
        System.out.println(Either.isLeft(left)); // true
        System.out.println(Either.isLeft(right)); // false
        System.out.println(Either.isRight(left)); // false
        System.out.println(Either.isRight(right)); // true

        System.out.println(Either.isLeft(new Either<>(left))); // true
        System.out.println(Either.isLeft(new Either<>(right))); // false
        System.out.println(Either.isRight(new Either<>(left))); // false
        System.out.println(Either.isRight(new Either<>(right))); // true



        // test fromLeft & fromRight
        // fromLeft :: a -> Either a b -> a
        // fromRight :: b -> Either a b -> b

        System.out.println(Either.fromLeft(456, left)); // 123
        System.out.println(Either.fromLeft(456, right)); // 456
        System.out.println(Either.fromRight("world", left)); // "world"
        System.out.println(Either.fromRight("world", right)); // "hello"

        System.out.println(Either.fromLeft(456, new Either<>(left))); // 123
        System.out.println(Either.fromLeft(456, new Either<>(right))); // 456
        System.out.println(Either.fromRight("world", new Either<>(left))); // "world"
        System.out.println(Either.fromRight("world", new Either<>(right))); // "hello"


        // test either
        // either :: (a -> c) -> (b -> c) -> Either a b -> c

        Integer doubleLeft =  Either.either(((Integer n) -> 2 * n), String::length, new Left<>(123));
        System.out.println(doubleLeft); // 246
        Integer lengthRight = Either.either(((Integer n) -> 2*n), String::length, new Right<>("hello"));
        System.out.println(lengthRight); // 5


        // test functions return type

        System.out.println(test(1)); // Left 123
        System.out.println(test(-1)); // Right Hello


        // not type safe
        // test lefts & rights
        // lefts :: [Either a b] -> [a]
        // rights :: [Either a b] -> [b]

        Either[] eithers = {
                new Either<>(new Left<>(1)),
                new Either<>(new Left<>(2)),
                new Either<>(new Left<>(3)),
                new Either<>(new Right<>("hello")),
                new Either<>(new Right<>("world"))
        };

        Integer[] lefts = Either.lefts(eithers, Integer.class);
        System.out.println(Arrays.toString(lefts)); // [1, 2, 3]

        String[] rights = Either.rights(eithers, String.class);
        System.out.println(Arrays.toString(rights)); // ["hello", "world"]
    }

    public static Either<Integer, String> test(int a) {
        if (a > 0) {
            return new Either<>(new Left<>(123));
        } else {
            return new Either<>(new Right<>("Hello"));
        }
    }
}

