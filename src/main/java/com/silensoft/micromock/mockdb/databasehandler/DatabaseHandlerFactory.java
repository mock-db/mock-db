package com.silensoft.micromock.mockdb.databasehandler;

import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

public interface DatabaseHandlerFactory {
    @NotNull DatabaseHandler createNewDatabaseHandler(final @NotNull String databaseName) throws MockDbException;
}
