package com.silensoft.micromock.mockdb.expectations;

import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandler;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ExpectationsStore {
    void insertExpectations(final @NotNull DatabaseHandler databaseHandler, final @NotNull List<@NotNull String> expectationDefinitions) throws MockDbException;
}
