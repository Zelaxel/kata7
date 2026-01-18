package software.ulpgc.kata7.application.io;

import software.ulpgc.kata7.architecture.io.Record;
import software.ulpgc.kata7.architecture.model.Monster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class DatabaseRecord implements Record {
    
    private final Connection connection;
    private final PreparedStatement statement;

    public DatabaseRecord(Connection connection) throws SQLException {
        this.connection = connection;
        createTableIfNotExists();
        statement = connection.prepareStatement("INSERT INTO monsters (name, type, isLarge, games) VALUES (?, ?, ?, ?)");
    }

    private void createTableIfNotExists() throws SQLException {
        connection.createStatement().execute(
                """
                        CREATE TABLE IF NOT EXISTS monsters(
                            name TEXT,
                            type TEXT,
                            isLarge BOOLEAN,
                            games TEXT
                        )
                        """
        );
    }

    @Override
    public void put(Stream<Monster> monster) {
        try {
            monster.forEach(this::record);
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void record(Monster monster) {
        try {
            write(monster);
            statement.addBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void write(Monster monster) throws SQLException {
        statement.setString(1, monster.name());
        statement.setString(2, monster.type().toString());
        statement.setBoolean(3, monster.isLarge());
        statement.setString(4, toText(monster.games()));
    }

    private String toText(List<String> games) {
        return games.stream().skip(1).reduce(games.getFirst(), (s0,s1) -> s0 + "," + s1);
    }
}
