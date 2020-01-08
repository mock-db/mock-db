package com.silensoft.micromock.mockdb.mocks;

import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandler;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface MocksStore {
    void insertMocks(final @NotNull DatabaseHandler databaseHandler, final @NotNull List<@NotNull String> mockDefinitions) throws MockDbException;
}
