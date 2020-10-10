package com.ihfazh.widget;

import java.util.Random;

public class NumberGenerator {
    public static int getNumber(int max){
        Random random = new Random();
        return random.nextInt(max);
    }
}
