package com.example.nitish_food_app_android;
import org.junit.Assert;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class EmailValidationTest {
    @Test
    public void testIsEmailValid() {
        String testEmail = "nitish@gmail.com";
        Assert.assertThat(String.format("Email Validity Test failed for %s ", testEmail), EmailValidation.checkEmailForValidity(testEmail), is(true));
    }

    @Test
    public void testIsEmailValid2() {
        String testEmail = "   nitish@gmail.com   ";
        Assert.assertThat(String.format("Email Validity Test failed for %s ", testEmail), EmailValidation.checkEmailForValidity(testEmail), is(true));
    }


}
