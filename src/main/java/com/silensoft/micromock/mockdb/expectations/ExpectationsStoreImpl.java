package com.silensoft.micromock.mockdb.expectations;

import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandler;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExpectationsStoreImpl implements ExpectationsStore {
    public static final String EXPECTATIONS_TABLE_NAME = "expectations";

    @Override
    public void insertExpectations(final @NotNull DatabaseHandler databaseHandler, final @NotNull List<@NotNull String> expectationDefinitions) throws MockDbException {
        databaseHandler.recreateTable(EXPECTATIONS_TABLE_NAME);
        databaseHandler.insertDefinitionsToTable(expectationDefinitions, EXPECTATIONS_TABLE_NAME);
    }
}
