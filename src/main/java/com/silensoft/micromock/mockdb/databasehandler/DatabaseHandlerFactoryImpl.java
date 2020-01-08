package com.silensoft.micromock.mockdb.databasehandler;

import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

public class DatabaseHandlerFactoryImpl implements DatabaseHandlerFactory {
    @Override
    public @NotNull DatabaseHandler createNewDatabaseHandler(final @NotNull String databaseName) throws MockDbException {
        return new DatabaseHandlerImpl(databaseName);
    }
}
