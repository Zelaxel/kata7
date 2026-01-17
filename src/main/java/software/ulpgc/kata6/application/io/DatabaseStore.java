package software.ulpgc.kata6.application.io;

import software.ulpgc.kata6.architecture.io.Store;
import software.ulpgc.kata6.architecture.model.Monster;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public record DatabaseStore(Connection connection) implements Store {
    @Override
    public Stream<Monster> monsters() {
        try {
            return loadFrom(query());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Monster> loadFrom(ResultSet query) {
        return Stream.generate(()->readMonster(query)).takeWhile(Objects::nonNull);
    }

    private Monster readMonster(ResultSet query) {
        try {
            return query.next()? toMonster(query) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Monster toMonster(ResultSet query) throws SQLException {
        return new Monster(
                query.getString(1),
                toType(query.getString(2)),
                query.getBoolean(3),
                toGames(query.getString(4))
        );
    }

    private List<String> toGames(String string) {
        return List.of(string.split(","));
    }

    private Monster.Type toType(String string) {
        return Monster.Type.valueOf(string);
    }

    private ResultSet query() throws SQLException {
        return connection.createStatement().executeQuery("SELECT name, type, isLarge, games FROM monsters");
    }
}
