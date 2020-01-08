package com.silensoft.micromock.mockdb.mocks;

import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandler;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MocksStoreImpl implements MocksStore {
    public static final String MOCKS_TABLE_NAME = "mocks";

    @Override
    public void insertMocks(final @NotNull DatabaseHandler databaseHandler, final @NotNull List<@NotNull String> mockDefinitions) throws MockDbException {
        databaseHandler.recreateTable(MOCKS_TABLE_NAME);
        databaseHandler.insertDefinitionsToTable(mockDefinitions, MOCKS_TABLE_NAME);
    }
}
