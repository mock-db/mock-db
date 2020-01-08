package com.silensoft.micromock.mockdb;

import com.silensoft.micromock.mockdb.configfile.ConfigFileReader;
import com.silensoft.micromock.mockdb.configfile.ConfigFileReaderImpl;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import com.silensoft.micromock.mockdb.mocks.MocksParserImpl;
import mockit.Tested;
import mockit.Verifications;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@SuppressWarnings({"InstanceVariableOfConcreteClass", "DoubleBraceInitialization"})
public class MocksParserTests {
    private static final String FIRST_EXPECTED_MOCK_DEFINITION = "Statement statement;";
    private static final String SECOND_EXPECTED_MOCK_DEFINITION = "ResultSet resultSet;";
    private static final String THIRD_EXPECTED_MOCK_DEFINITION = "PreparedStatement preparedStatement     ;";

    @Tested
    private @NotNull MocksParserImpl mockParser;

    final @NotNull ConfigFileReader configFileReader = new ConfigFileReaderImpl();

    @Test
    void testParseMockDefinitions() {
        try {
            //noinspection ConstantConditions
            final @NotNull List<@NotNull String> configFileLines = configFileReader
                    .readConfigFileLines(getClass().getClassLoader().getResource("testcase.txt"));

            final @NotNull List<@NotNull String> mockDefinitions = mockParser.parseMockDefinitions(configFileLines);

            new Verifications() {{
                assertEquals(mockDefinitions.size(), 3);
                assertEquals(mockDefinitions.get(0), FIRST_EXPECTED_MOCK_DEFINITION);
                assertEquals(mockDefinitions.get(1), SECOND_EXPECTED_MOCK_DEFINITION);
                assertEquals(mockDefinitions.get(2), THIRD_EXPECTED_MOCK_DEFINITION);
            }};

        } catch (final MockDbException exception) {
            fail("Expected successful parsing of mocks, but exception was thrown");
        }
    }
}
