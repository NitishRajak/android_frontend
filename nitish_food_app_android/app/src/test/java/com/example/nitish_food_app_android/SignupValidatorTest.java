package com.example.nitish_food_app_android;
import org.junit.Assert;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class SignupValidatorTest {

    @Test
    public void testIsAccountCreated() {
        String name = "apple";
        String email = "apple2@gmail.com";
        String password = "apple";
        SignupValidator signupValidator = new SignupValidator();
        Assert.assertThat(String.format("Account did not created with email: %s ", email), signupValidator.createAccount(name, email, password), is(true));
    }

}
