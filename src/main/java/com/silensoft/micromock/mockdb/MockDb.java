package com.silensoft.micromock.mockdb;

import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

public interface MockDb {
    void initializeLocalMockDb(final @NotNull String databaseName, final @NotNull URL testConfigFile) throws MockDbException;
    void initializeRemoteMockDb(final @NotNull String host, final int port, final @NotNull String databaseName, final @NotNull URL testConfigFile);
}
