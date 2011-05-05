package com.jayway.wordgraph;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.size;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class HelloTest {
    
    // @BEGIN_VERSION HELLO_GENERICS
    @Test
    public void repeatShouldReturnCollectionWithRepeatedObjects() throws Exception {
        Collection<String> expected = Arrays.asList("apa", "apa");
        Collection<String> result = Hello.repeat("apa", 2);
        assertThat(result, equalTo(expected));
    }
    // @END_VERSION HELLO_GENERICS
    
    // @BEGIN_VERSION HELLO_GENERICS2
    @Test
    public void repeatWithAnotherTypeShouldAlsoReturnCollectionWithRepeatedObjects() throws Exception {
        Collection<Integer> expected = Arrays.asList(6, 6, 6);
        Collection<Integer> result = Hello.repeat(6, 3);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void thereShouldOnlyBeOneRepeatMethod() throws Exception {
        int noRepeatMethods = size(filter(asList(Hello.class.getMethods()), withName("repeat")));
        assertThat(noRepeatMethods, equalTo(1));
    }

    private static Predicate<Method> withName(final String methodName) {
        return new Predicate<Method>() {
            public boolean apply(Method input) {
                return methodName.equals(input.getName());
            }
        };
    }
    // @END_VERSION HELLO_GENERICS2

    // @BEGIN_VERSION HELLO_FUNCTION
    @Test
    public void squareFunctionShouldSquareItsInput() throws Exception {
        Integer expected = 9;
        Function<Integer, Integer> square = Hello.square;
        Integer result = square.apply(3);
        assertThat(result, equalTo(expected));
    }
    // @END_VERSION HELLO_FUNCTION
    
    // @BEGIN_VERSION HELLO_BINARY_FUNCTION

    // This is only here to help you design the generic interface!
    static class Function2TypeVerification implements Function2<String, Color, Point> {
        public Point apply(String a1, Color a2) {
            return null;
        };
    }

    @Test
    public void addFunctionShouldAddItsArguments() throws Exception {
        Integer expected = 19;
        Function2<Integer, Integer, Integer> add = Hello.add;
        Integer result = add.apply(11, 8);
        assertThat(result, equalTo(expected));
    }
    
    // @END_VERSION HELLO_BINARY_FUNCTION
}
