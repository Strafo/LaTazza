package utils;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T,R> extends Function<T,R> {

    @Override
    default R apply(final T elem) {
        try {
            R r=applyThrows(elem);
            return r;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    R applyThrows(T elem) throws Exception;
}


