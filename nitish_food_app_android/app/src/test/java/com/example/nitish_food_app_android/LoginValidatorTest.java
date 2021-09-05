package com.example.nitish_food_app_android;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class LoginValidatorTest {

    @Test
    public void testIsLoginCheck() {
        String email = "user1@gmail.com";
        String password = "user1";
        LoginValidator loginValidator = new LoginValidator();
        Assert.assertThat(String.format("Username and password does not match. "), loginValidator.isLogin(email, password), is(true));
    }

}
