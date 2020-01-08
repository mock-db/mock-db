package com.silensoft.micromock.mockdb.configfile;

import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class ConfigFileReaderImpl implements ConfigFileReader {

    @Override
    public @NotNull List<@NotNull String> readConfigFileLines(final @NotNull URL testConfigFile) throws MockDbException {
        final @NotNull Path testConfigurationFilePath;

        try {
            testConfigurationFilePath = Objects.requireNonNull(Paths.get(testConfigFile.toURI()));
        } catch (final Exception exception) {
            throw new MockDbException(CONFIGURATION_FILE_NOT_FOUND, exception);
        }

        final @NotNull List<@NotNull String> lines;

        try {
            lines = Files.readAllLines(testConfigurationFilePath);
        } catch (final Exception exception) {
            throw new MockDbException(CONFIGURATION_FILE_ACCESS_ERROR, exception);
        }

        return lines;
    }
}
