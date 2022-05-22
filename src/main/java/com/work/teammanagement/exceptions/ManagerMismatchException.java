package com.work.teammanagement.exceptions;

public class ManagerMismatchException extends Exception {
    public ManagerMismatchException(String managerUsername1, String managerUsername2) {
        super(String.format("%s != %s", managerUsername1, managerUsername2));
    }
}
