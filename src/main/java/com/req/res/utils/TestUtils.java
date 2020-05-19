package com.req.res.utils;

/*
Created by SP
*/

import java.util.Random;

public class TestUtils {

    // random value method

    public static String getRandomValue(){
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }
}
