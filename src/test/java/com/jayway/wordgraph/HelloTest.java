package com.jayway.wordgraph;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class HelloTest {
    // @BEGIN_VERSION HELLO_FUNCTION
    @Test
    public void simpleFunctionShouldProduceResult() throws Exception {
        Integer expected = 9;
        Integer result = Hello.square.apply(3);
        assertThat(result, equalTo(expected));
    }
    // @END_VERSION HELLO_FUNCTION
}
