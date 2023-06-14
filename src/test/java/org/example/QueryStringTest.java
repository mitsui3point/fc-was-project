package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringTest {
    /**
     * operand1=11&operator=*&operand2=55
     */
    @DisplayName("QueryString 을 생성한다.")
    @Test
    void create() {
        QueryString queryString = new QueryString("operand1=11");

        assertThat(queryString).isNotNull();
        assertThat(queryString).isEqualTo(new QueryString("operand1","11"));
    }
}
