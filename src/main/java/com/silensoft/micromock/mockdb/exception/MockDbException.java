package com.silensoft.micromock.mockdb.exception;

import org.jetbrains.annotations.NotNull;

public class MockDbException extends Exception {

    public MockDbException(final @NotNull String message) {
        super(message);
    }

    public MockDbException(final @NotNull String message, final @NotNull Throwable cause) {
        super(message, cause);
    }
}
