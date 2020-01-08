package com.silensoft.micromock.mockdb.expectations;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ExpectationsParserImpl implements ExpectationsParser {

    public static final String NEWLINE = System.getProperty("line.separator");

    @SuppressWarnings("DynamicRegexReplaceableByCompiledPattern")
    @Override
    public @NotNull List<@NotNull String> parseExpectationsDefinitions(final @NotNull Collection<@NotNull String> configFileLines) {
        final @NotNull String expectationDefinitions = configFileLines.stream()
                .dropWhile(configurationLine ->
                        !configurationLine.equalsIgnoreCase(EXPECTATIONS_IN_ANY_ORDER_HEADER) &&
                                !configurationLine.equalsIgnoreCase(EXPECTATIONS_IN_SEQUENCE_HEADER))
                .skip(1L)
                .filter(configurationLine -> !configurationLine.matches("\\s*"))
                .collect(Collectors.joining(NEWLINE));

        return Arrays.stream(expectationDefinitions.split("[ \\t]*;[ \\t]*" + NEWLINE))
                .map(expectationDefinition -> expectationDefinition.endsWith(";") ? expectationDefinition : expectationDefinition + ';')
                .collect(Collectors.toList());
    }
}
