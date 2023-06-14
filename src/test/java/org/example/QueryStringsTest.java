package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class QueryStringsTest {
    //operand1=11&operator=*&operand2=55
    @DisplayName("QueryStrings 를 생성한다.")
    @Test
    void create() {
        QueryStrings queryStrings = new QueryStrings("operand1=11&operator=*&operand2=55");

        assertThat(queryStrings).isNotNull();
        assertThat(queryStrings).isEqualTo(
                new QueryStrings(
                        new QueryString("operand1", "11"),
                        new QueryString("operator", "*"),
                        new QueryString("operand2", "55")
                )
        );
    }

    @DisplayName("QueryStrings 생성을 실패한다.")
    @Test
    void createFail() {
        assertThatCode(() -> new QueryStrings("operand1=11&operator*&operand2=55"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 QueryString 포맷을 가진 문자열입니다.");
    }
}
