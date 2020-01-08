package com.silensoft.micromock.mockdb;

import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandler;
import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandlerImpl;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.fail;

@SuppressWarnings("DoubleBraceInitialization")
public class DatabaseHandlerTests {
    private static final String DATABASE_NAME = "testdb";
    private static final String TABLE_NAME = "testtable";
    private static final String EXPECTED_CONNECTION_URL = "jdbc:h2:~/testdb";

    @Mocked
    private @NotNull Connection connectionMock;

    @Mocked
    private @NotNull PreparedStatement preparedStatementMock;

    @BeforeClass
    void setupDriverManagerMock() {
        new MockUp<DriverManager>() {
            @Mock
            Connection getConnection(final @NotNull String connectionUrl, final String userName, final String password) throws SQLException {
                if (connectionUrl.equals(EXPECTED_CONNECTION_URL)) {
                    return connectionMock;
                }
                throw new SQLException();
            }
        };
    }

    @Test
    void testRecreateTable() {

        try (final @NotNull DatabaseHandler databaseHandler = new DatabaseHandlerImpl(DATABASE_NAME)) {
            new Expectations() {{
                connectionMock.prepareStatement(DatabaseHandlerImpl.DROP_TABLE_PREPARED_STATEMENT); result = preparedStatementMock;
                preparedStatementMock.setString(1, TABLE_NAME);
                preparedStatementMock.execute();
                connectionMock.prepareStatement(DatabaseHandlerImpl.CREATE_TABLE_PREPARED_STATEMENT); result = preparedStatementMock;
                preparedStatementMock.setString(1, TABLE_NAME);
                preparedStatementMock.execute();
            }};

            databaseHandler.recreateTable(TABLE_NAME);

        } catch (final Exception exception) {
            fail("Expected successful recreation of table, but exception was thrown");
        }
    }

    @Test
    void testInsertDefinitionsToTable() {
        try (final @NotNull DatabaseHandler databaseHandler = new DatabaseHandlerImpl(DATABASE_NAME)) {

            final @NotNull List<@NotNull String> definitions = List.of("definition1", "definition2");

            new Expectations() {{
                connectionMock.prepareStatement(DatabaseHandlerImpl.INSERT_PREPARED_STATEMENT); result = preparedStatementMock;
                preparedStatementMock.setString(1, TABLE_NAME);
                preparedStatementMock.setInt(2, 0);
                preparedStatementMock.setString(3, definitions.get(0));
                preparedStatementMock.execute();
                preparedStatementMock.setInt(2, 1);
                preparedStatementMock.setString(3, definitions.get(1));
                preparedStatementMock.execute();
            }};

           databaseHandler.insertDefinitionsToTable(definitions, TABLE_NAME);

        } catch (final Exception exception) {
            fail("Expected successful insert of definitions to table, but exception was thrown");
        }
    }

}
