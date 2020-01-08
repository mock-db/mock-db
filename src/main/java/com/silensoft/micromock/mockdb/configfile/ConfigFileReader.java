package com.silensoft.micromock.mockdb.configfile;

import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.List;

public interface ConfigFileReader {
    String CONFIGURATION_FILE_NOT_FOUND = "Configuration file not found";
    String CONFIGURATION_FILE_ACCESS_ERROR = "Configuration file access error";

    @NotNull List<@NotNull String> readConfigFileLines(final @NotNull URL testConfigFile) throws MockDbException;
}
