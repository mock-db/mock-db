package com.silensoft.micromock.mockdb;

import com.silensoft.micromock.mockdb.configfile.ConfigFileReader;
import com.silensoft.micromock.mockdb.configfile.ConfigFileReaderImpl;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import com.silensoft.micromock.mockdb.expectations.ExpectationsParserImpl;
import mockit.Tested;
import mockit.Verifications;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@SuppressWarnings({"InstanceVariableOfConcreteClass", "DoubleBraceInitialization"})
public class ExpectationsParserTests {
    private static final String FIRST_EXPECTED_EXPECTATION_DEFINITION = "statement.executeQuery(\"SELECT * FROM table1\"); result = resultSet;";

    private static final String SECOND_EXPECTED_EXPECTATION_DEFINITION =
            "statement.executeQuery(\"SELECT * FROM table2\"); times = 1; result =" + ExpectationsParserImpl.NEWLINE +
            "| id | name             | age |" + ExpectationsParserImpl.NEWLINE +
            "| 1  | Petri Silen      | 46  |" + ExpectationsParserImpl.NEWLINE +
            "| 2  | Petra Silen      | 43  |;";

    private static final String THIRD_EXPECTED_EXPECTATION_DEFINITION = "statement.executeQuery(\"SELECT * FROM table3\"); result = 1;";
    private static final String FOURTH_EXPECTED_EXPECTATION_DEFINITION = "statement.executeQuery(\"SELECT * FROM table4\"); result = 1;";

    @Tested
    private @NotNull ExpectationsParserImpl expectationsParser;

    @Test
    void testParseExpectationDefinitions() {
        final @NotNull ConfigFileReader configFileReader = new ConfigFileReaderImpl();

        try {
            //noinspection ConstantConditions
            final @NotNull List<@NotNull String> configFileLines = configFileReader
                    .readConfigFileLines(getClass().getClassLoader().getResource("testcase.txt"));

            final @NotNull List<@NotNull String> expectationDefinitions = expectationsParser.parseExpectationsDefinitions(configFileLines);

            new Verifications() {{
                assertEquals(expectationDefinitions.size(), 4);
                assertEquals(expectationDefinitions.get(0), FIRST_EXPECTED_EXPECTATION_DEFINITION);
                assertEquals(expectationDefinitions.get(1), SECOND_EXPECTED_EXPECTATION_DEFINITION);
                assertEquals(expectationDefinitions.get(2), THIRD_EXPECTED_EXPECTATION_DEFINITION);
                assertEquals(expectationDefinitions.get(3), FOURTH_EXPECTED_EXPECTATION_DEFINITION);
            }};

        } catch (final MockDbException exception) {
            fail("Expected successful parsing of expectations, but exception was thrown");
        }
    }
}
