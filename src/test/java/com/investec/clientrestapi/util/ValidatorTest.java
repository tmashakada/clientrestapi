package com.investec.clientrestapi.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ValidatorTest {
    @Test
    void givenIdNumber_IsIdNumberValid_ShouldReturnTrue(){
       assertThat(Validator.isIdNumberValid("8605065397083")).isEqualTo(true);
       assertThat(Validator.isIdNumberValid("860B065397083")).isEqualTo(false);
       assertThat(Validator.isIdNumberValid("86050653970")).isEqualTo(false);
    }

    @Test
    void givenAnInteger_SumTheDigits_ShouldReturnSum(){
        int sum=Validator.getSum(374789);
        assertThat(sum).isEqualTo(38);
    }
    @Test
    void givenStringCheckWetherStringIsNumeric_ShouldReturnTrueOrFalse(){
        boolean isNumeric=Validator.isNumeric("860B065397083");
        boolean isNumeric2=Validator.isNumeric("6789");
        boolean isNumeric3=Validator.isNumeric("");
        assertThat(isNumeric).isEqualTo(false);
        assertThat(isNumeric2).isEqualTo(true);
        assertThat(isNumeric3).isEqualTo(false);
    }
}