package com.silensoft.micromock.mockdb;

import com.silensoft.micromock.mockdb.configfile.ConfigFileReader;
import com.silensoft.micromock.mockdb.configfile.ConfigFileReaderImpl;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;
import mockit.Verifications;
import org.jetbrains.annotations.NotNull;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@SuppressWarnings({"InstanceVariableOfConcreteClass", "DoubleBraceInitialization"})
public class ConfigFileReaderTests {
    private static final int EXPECTED_LINE_COUNT = 16;

    @Tested
    private @NotNull ConfigFileReaderImpl configFileReader;

    @SuppressWarnings("ConstantConditions")
    final @NotNull URL testCaseFileUrl = getClass().getClassLoader().getResource("testcase.txt");

    @Test
    void testReadConfigFileLines() {
        try {
            final @NotNull List<@NotNull String> lines = configFileReader.readConfigFileLines(testCaseFileUrl);

            new Verifications() {{
                assertEquals(lines.size(), EXPECTED_LINE_COUNT);
                assertEquals(lines.get(0), "Mocks:");
            }};

        } catch (final MockDbException exception) {
            fail("Expected successful read of config file lines, but exception was thrown");
        }
    }

    @Test
    void testReadConfigFileLinesWhenFileIsNotFound() {
        try {
            configFileReader.readConfigFileLines(new URL("http://"));
            fail("MockDbException should have been thrown, but was not thrown.");
        } catch (final MockDbException exception) {
            Assert.assertEquals(exception.getMessage(), ConfigFileReader.CONFIGURATION_FILE_NOT_FOUND);
        } catch (final Exception exception) {
            fail("MockDbException should have been thrown, but different exception was thrown");
        }
    }

    @Test
    void testReadConfigFileLinesWhenFileCannotBeRead() {
       §

        try {
            configFileReader.readConfigFileLines(testCaseFileUrl);
            fail("MockDbException should have been thrown, but was not thrown.");
        } catch (final MockDbException exception) {
            assertEquals(exception.getMessage(), ConfigFileReader.CONFIGURATION_FILE_ACCESS_ERROR);
        } catch (final Exception exception) {
            fail("MockDbException should have been thrown, but different exception was thrown");
        }
    }
}
