package software.ulpgc.kata5.application.versions.pukei;

import software.ulpgc.kata5.application.io.DatabaseRecord;
import software.ulpgc.kata5.application.io.DatabaseStore;
import software.ulpgc.kata5.application.io.JsonParser;
import software.ulpgc.kata5.application.io.RemoteStore;
import software.ulpgc.kata5.application.view.MainFrame;
import software.ulpgc.kata5.architecture.model.Monster;
import software.ulpgc.kata5.architecture.tasks.HistogramBuilder;
import software.ulpgc.kata5.architecture.viewModel.Histogram;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;

public class Main {
    public static final File database = new File("monsters.db"); 
    static void main() throws SQLException {
        try(Connection connection = openConnection()){
            fillDatabaseIfRequired(connection);
            Stream<Monster> monsters = new DatabaseStore(connection).monsters();
            Histogram<String> histogram = HistogramBuilder.with(monsters)
                    .setTitle("Monsters per game")
                    .setX("Games")
                    .setY("Count")
                    .setLegend("Monsters")
                    .build(m -> m.games().stream());
            MainFrame.create().display(histogram).setVisible(true);
        }
    }

    private static void fillDatabaseIfRequired(Connection connection) throws SQLException {
        if(database.length() > 0) return;
        new DatabaseRecord(connection).put(new RemoteStore(JsonParser::parse).monsters());
    }

    private static Connection openConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + database.getAbsolutePath());
        connection.setAutoCommit(false);
        return connection;
    }
}
