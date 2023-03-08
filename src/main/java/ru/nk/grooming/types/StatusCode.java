package ru.nk.grooming.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatusCode {
    private int statusCode;

    public static StatusCode create(int statusCode) {
        return new StatusCode(statusCode);
    }
}
