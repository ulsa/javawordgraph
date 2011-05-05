package com.jayway.wordgraph;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import com.google.common.base.Function;

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
    @Test
    public void addFunctionShouldAddItsArguments() throws Exception {
        Integer expected = 19;
        Function2<Integer, Integer, Integer> add = Hello.add;
        Integer result = add.apply(11, 8);
        assertThat(result, equalTo(expected));
    }
    // @END_VERSION HELLO_BINARY_FUNCTION
}
