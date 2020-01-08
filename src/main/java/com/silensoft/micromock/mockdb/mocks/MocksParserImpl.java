package com.silensoft.micromock.mockdb.mocks;

import com.silensoft.micromock.mockdb.expectations.ExpectationsParser;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MocksParserImpl implements MocksParser {
    private static final String MOCKS_HEADER = "Mocks:";

    @SuppressWarnings({"DynamicRegexReplaceableByCompiledPattern", "MethodWithMoreThanThreeNegations"})
    @Override
    public @NotNull List<@NotNull String> parseMockDefinitions(final @NotNull Collection<@NotNull String> configFileLines) {
        return configFileLines.stream()
                .dropWhile(configurationLine -> !configurationLine.equalsIgnoreCase(MOCKS_HEADER))
                .skip(1L)
                .takeWhile(configurationLine ->
                        !configurationLine.equalsIgnoreCase(ExpectationsParser.EXPECTATIONS_IN_ANY_ORDER_HEADER) &&
                                !configurationLine.equalsIgnoreCase(ExpectationsParser.EXPECTATIONS_IN_SEQUENCE_HEADER))
                .filter(configurationLine -> !configurationLine.matches("\\s*"))
                .collect(Collectors.toList());
    }
}
