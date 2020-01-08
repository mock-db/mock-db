package com.silensoft.micromock.mockdb;

import com.google.inject.Inject;
import com.silensoft.micromock.mockdb.configfile.ConfigFileReader;
import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandler;
import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandlerFactory;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import com.silensoft.micromock.mockdb.expectations.ExpectationsParser;
import com.silensoft.micromock.mockdb.expectations.ExpectationsStore;
import com.silensoft.micromock.mockdb.mocks.MocksParser;
import com.silensoft.micromock.mockdb.mocks.MocksStore;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.List;

public class MockDbImpl implements MockDb {

    @Inject
    private @NotNull ConfigFileReader configFileReader;

    @Inject
    private @NotNull MocksParser mocksParser;

    @Inject
    private @NotNull DatabaseHandlerFactory databaseHandlerFactory;

    @Inject
    private @NotNull ExpectationsParser expectationsParser;

    @Inject
    private @NotNull MocksStore mocksStore;

    @Inject
    private @NotNull ExpectationsStore expectationsStore;

    @Override
    public void initializeLocalMockDb(final @NotNull String databaseName, final @NotNull URL testConfigFile) throws MockDbException {
        final @NotNull List<@NotNull String> configFileLines = configFileReader.readConfigFileLines(testConfigFile);
        final @NotNull DatabaseHandler databaseHandler = databaseHandlerFactory.createNewDatabaseHandler(databaseName);

        final @NotNull List<@NotNull String> mockDefinitions = mocksParser.parseMockDefinitions(configFileLines);
        mocksStore.insertMocks(databaseHandler, mockDefinitions);

        final @NotNull List<@NotNull String> expectationDefinitions = expectationsParser.parseExpectationsDefinitions(configFileLines);
        expectationsStore.insertExpectations(databaseHandler,expectationDefinitions);
    }

    @Override
    public void initializeRemoteMockDb(final @NotNull String host, final int port, final @NotNull String databaseName, final @NotNull URL testConfigFile) {
        // TODO: implement
        throw new UnsupportedOperationException();
    }
}
