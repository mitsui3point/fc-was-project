package org.example.counter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CounterTest {

    @DisplayName("Counter 를 생성한다.")
    @Test
    void create() {
        //when
        Counter counter = new Counter();
        //then
        assertThat(counter).isNotNull();
        assertThat(counter).isInstanceOf(Runnable.class);
    }

    @DisplayName("increment 메서드 실행 시 count 가 1 증가한다.")
    @Test
    void increment() {
        //given
        Counter counter = new Counter();

        //when
        counter.increment();
        int count = counter.getValue();

        //then
        assertThat(count).isEqualTo(1);
    }

    @DisplayName("decrement 메서드 실행 시 count 가 1 감소한다.")
    @Test
    void decrement() {
        //given
        Counter counter = new Counter();

        //when
        counter.increment();
        int increaseCount = counter.getValue();
        counter.decrement();
        int decreaseCount = counter.getValue();

        //then
        assertThat(increaseCount).isEqualTo(1);
        assertThat(decreaseCount).isEqualTo(0);
    }
}
