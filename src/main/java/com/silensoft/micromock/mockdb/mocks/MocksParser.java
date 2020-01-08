package com.silensoft.micromock.mockdb.mocks;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface MocksParser {
    @NotNull List<@NotNull String> parseMockDefinitions(final @NotNull Collection<@NotNull String> configFileLines);
}
