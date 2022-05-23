package com.work.teammanagement.exceptions;

public class NotEnoughAvailableDaysException extends Exception {
    public NotEnoughAvailableDaysException() {
        super("Not enough available vacation days!");
    }
}
