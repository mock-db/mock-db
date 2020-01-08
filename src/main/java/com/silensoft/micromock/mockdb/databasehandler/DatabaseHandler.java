package com.silensoft.micromock.mockdb.databasehandler;

import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface DatabaseHandler extends AutoCloseable {
    void recreateTable(final @NotNull String tableName) throws MockDbException;
    void insertDefinitionsToTable(final @NotNull List<@NotNull String> definitions, final @NotNull String tableName) throws MockDbException;
}
