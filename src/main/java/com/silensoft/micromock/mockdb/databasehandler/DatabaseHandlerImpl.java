package com.silensoft.micromock.mockdb.databasehandler;

import com.silensoft.micromock.mockdb.exception.MockDbException;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseHandlerImpl implements DatabaseHandler {
    public static final String DROP_TABLE_PREPARED_STATEMENT = "DROP TABLE IF NOT EXISTS ?";
    public static final String CREATE_TABLE_PREPARED_STATEMENT = "CREATE TABLE ? (id INT PRIMARY KEY, definition VARCHAR(65536)";
    public static final String INSERT_PREPARED_STATEMENT = "INSERT INTO ? (id, definition) VALUES (?, ?)";
    private static final String DB_ACCESS_ERROR = "Database access error";
    private static final String TOO_LONG_DEFINITION_ERROR = "Definition is too long";
    private static final int DEFINITION_MAX_LENGTH = 65536;

    private final Connection connection;

    public DatabaseHandlerImpl(final @NotNull String databaseName) throws MockDbException {
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/" + databaseName, "sa", "");
        } catch (final Exception exception) {
            throw new MockDbException(DB_ACCESS_ERROR, exception);
        }
    }

    @Override
    public void recreateTable(final @NotNull String tableName) throws MockDbException {
        executeDdlStatement(DROP_TABLE_PREPARED_STATEMENT, tableName);
        executeDdlStatement(CREATE_TABLE_PREPARED_STATEMENT, tableName);
    }

    @Override
    public void insertDefinitionsToTable(final @NotNull List<@NotNull String> definitions, final @NotNull String tableName) throws MockDbException {
        if (connection != null) {
            try (final PreparedStatement statement = connection.prepareStatement(INSERT_PREPARED_STATEMENT)) {
                if (statement != null) {
                    statement.setString(1, tableName);
                    int id = 0;
                    for (final @NotNull String definition : definitions) {
                        id = insertDefinition(statement, id, definition);
                    }
                }
            }  catch (final Exception exception) {
                throw new MockDbException(DB_ACCESS_ERROR, exception);
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void executeDdlStatement(final @NotNull String ddlStatement, final @NotNull String tableName) throws MockDbException {
        if (connection != null) {
            try (final PreparedStatement statement = connection.prepareStatement(ddlStatement)) {
                if (statement != null) {
                    statement.setString(1, tableName);
                    statement.execute();
                }
            } catch (final Exception exception) {
                throw new MockDbException(DB_ACCESS_ERROR, exception);
            }
        }
    }

    private static int insertDefinition(
            final @NotNull PreparedStatement statement,
            final int id,
            final @NotNull String definition
    ) throws MockDbException, SQLException {

        if (definition.length() > DEFINITION_MAX_LENGTH) {
            throw new MockDbException(TOO_LONG_DEFINITION_ERROR);
        }

        statement.setInt(2, id);
        statement.setString(3, definition);
        statement.execute();
        return id + 1;
    }
}
