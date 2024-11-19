package com.estsoft.springproject.tdd;

import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

//TDD
// 1.게좌 생성
// 2. 잔금 조회
// 3. 입/출금
public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account(10000);
    }

    @Test
    public void testAccount() throws Exception{
        // hamcrest
        MatcherAssert.assertThat(account.getBalance(), is(10000));

    }

    @Test
    public void testDeposit() throws Exception{
        //given

        //when
        account.deposit(10000);
        //then
        assertThat(account.getBalance()).isEqualTo(20000);
    }

    @Test
    public void testWithdraw() throws Exception{
        //given

        //when
        account.withdraw(10000);
        //then
        assertThat(account.getBalance()).isEqualTo(0);
    }
}
