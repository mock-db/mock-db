package com.silensoft.micromock.mockdb;

import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandler;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import com.silensoft.micromock.mockdb.mocks.MocksStoreImpl;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.fail;

@SuppressWarnings({"InstanceVariableOfConcreteClass", "DoubleBraceInitialization"})
public class MocksStoreTests {

    @Tested
    private @NotNull MocksStoreImpl mocksStore;

    @Mocked
    private @NotNull DatabaseHandler databaseHandlerMock;

    @Mocked
    private @NotNull List<@NotNull String> mockedMockDefinitions;

    @Test
    void testInsertExpectations() {
        try {
            new Expectations() {{
                databaseHandlerMock.recreateTable(MocksStoreImpl.MOCKS_TABLE_NAME);
                databaseHandlerMock.insertDefinitionsToTable(mockedMockDefinitions, MocksStoreImpl.MOCKS_TABLE_NAME);
            }};

            mocksStore.insertMocks(databaseHandlerMock, mockedMockDefinitions);

        } catch (final MockDbException exception) {
            fail("Expected successful insertion of mocks, but exception was thrown");
        }
    }
}
