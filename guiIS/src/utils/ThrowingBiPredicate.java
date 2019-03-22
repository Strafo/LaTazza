package utils;

import java.util.function.BiPredicate;

@FunctionalInterface
public interface ThrowingBiPredicate<T,U> extends BiPredicate<T,U> {

    @Override
    default boolean test(final T elem1,final U elem2) {
        try {
            return testThrows(elem1,elem2);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    boolean testThrows(T elem1,U elem2) throws Exception;
}


