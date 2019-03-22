package utils;
import java.util.function.Predicate;

@FunctionalInterface
public interface ThrowingPredicate<T> extends Predicate<T> {

    @Override
    default boolean test(final T elem){
        try {
            return testThrows(elem);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    boolean testThrows(T elem) throws Exception;
}


