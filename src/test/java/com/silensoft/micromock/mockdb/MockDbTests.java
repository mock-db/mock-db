package com.silensoft.micromock.mockdb;

import com.silensoft.micromock.mockdb.configfile.ConfigFileReader;
import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandler;
import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandlerFactory;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import com.silensoft.micromock.mockdb.expectations.ExpectationsParser;
import com.silensoft.micromock.mockdb.expectations.ExpectationsStore;
import com.silensoft.micromock.mockdb.mocks.MocksParser;
import com.silensoft.micromock.mockdb.mocks.MocksStore;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

import static org.testng.Assert.fail;

@SuppressWarnings({"DoubleBraceInitialization", "InstanceVariableOfConcreteClass", "ClassWithTooManyFields"})
public class MockDbTests {

    private static final String DATABASE_NAME = "test";

    @Tested
    private @NotNull MockDbImpl mockDb;

    @Injectable
    @Mocked
    private @NotNull ConfigFileReader configFileReaderMock;

    @Injectable
    @Mocked
    private @NotNull DatabaseHandlerFactory databaseHandlerFactory;

    @Injectable
    @Mocked
    private @NotNull MocksParser mocksParserMock;

    @Injectable
    @Mocked
    private @NotNull ExpectationsParser expectationsParserMock;

    @Injectable
    @Mocked
    private @NotNull MocksStore mocksStoreMock;

    @Injectable
    @Mocked
    private @NotNull ExpectationsStore expectationsStoreMock;

    @Mocked
    private @NotNull URL urlMock;

    @Mocked
    private @NotNull List<@NotNull String> configLines;

    @Mocked
    private @NotNull DatabaseHandler databaseHandlerMock;

    @Mocked
    private @NotNull List<@NotNull String> mockDefinitions;

    @Mocked
    private @NotNull List<@NotNull String> expectationDefinitions;

    @Test
    public void testInitializeLocalMockDb() {
        try {

            new Expectations() {{
                configFileReaderMock.readConfigFileLines(urlMock); result = configLines;
                databaseHandlerFactory.createNewDatabaseHandler(DATABASE_NAME); result = databaseHandlerMock;
                mocksParserMock.parseMockDefinitions(configLines); result = mockDefinitions;
                mocksStoreMock.insertMocks(databaseHandlerMock, mockDefinitions);
                expectationsParserMock.parseExpectationsDefinitions(configLines); result = expectationDefinitions;
                expectationsStoreMock.insertExpectations(databaseHandlerMock, expectationDefinitions);
            }};

            mockDb.initializeLocalMockDb(DATABASE_NAME, urlMock);

        } catch (final MockDbException exception) {
            fail("Expected successful initialization, but exception was thrown");
        }
    }

}
