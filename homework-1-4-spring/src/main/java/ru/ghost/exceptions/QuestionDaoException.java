package ru.ghost.exceptions;

public class QuestionDaoException extends RuntimeException {
    public QuestionDaoException (final String message, final Throwable cause) {
        super(message, cause);
    }
}
