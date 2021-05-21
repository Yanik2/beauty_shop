package com.example.beauty_shop.controller.validator;

import java.time.LocalDate;

public class DateValidator implements Validator {
    public static boolean validate(String date) {
        return LocalDate.now().toString().compareTo(date) <= 0;
    }
}
