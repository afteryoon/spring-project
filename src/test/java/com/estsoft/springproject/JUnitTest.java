package com.estsoft.springproject;

import com.estsoft.springproject.entity.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JUnitTest {

    @Test
    public void test() throws Exception{
        //given
            int a = 1;
            int b = 2;

        //when :검증하고 싶은 메소드 호출
            int sum = a + b;

        //then
//        Assertions.assertEquals(3, sum);
        Assertions.assertThat(a+b).isEqualTo(sum);
//        Assertions.assertThat(sum).isEven();
        Assertions.assertThat(sum).isOdd();
    }

}
