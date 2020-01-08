package com.silensoft.micromock.mockdb;

import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandler;
import com.silensoft.micromock.mockdb.exception.MockDbException;
import com.silensoft.micromock.mockdb.expectations.ExpectationsStoreImpl;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.fail;

@SuppressWarnings({"DoubleBraceInitialization", "InstanceVariableOfConcreteClass"})
public class ExpectationsStoreTests {

    @Tested
    private @NotNull ExpectationsStoreImpl expectationsStore;

    @Mocked
    private @NotNull DatabaseHandler databaseHandlerMock;

    @Mocked
    private @NotNull List<@NotNull String> mockedExpectationDefinitions;

    @Test
    void testInsertExpectations() {
        try {
            new Expectations() {{
                databaseHandlerMock.recreateTable(ExpectationsStoreImpl.EXPECTATIONS_TABLE_NAME);
                databaseHandlerMock.insertDefinitionsToTable(mockedExpectationDefinitions, ExpectationsStoreImpl.EXPECTATIONS_TABLE_NAME);
            }};

            expectationsStore.insertExpectations(databaseHandlerMock, mockedExpectationDefinitions);

        } catch (final MockDbException exception) {
            fail("Expected successful insertion of expectations, but exception was thrown");
        }
    }
}
