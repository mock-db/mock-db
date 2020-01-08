package com.silensoft.micromock.mockdb.expectations;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface ExpectationsParser {
    String EXPECTATIONS_IN_ANY_ORDER_HEADER = "Expectations in any order:";
    String EXPECTATIONS_IN_SEQUENCE_HEADER = "Expectations in sequence:";

    @NotNull List<@NotNull String> parseExpectationsDefinitions(final @NotNull Collection<@NotNull String> configFileLines);
}
