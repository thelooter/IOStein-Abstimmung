package de.thelooter.iosteinpolls.database;

import de.thelooter.iosteinpolls.IOSteinPolls;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseProvider {

    private final Connection connection;
    private final IOSteinPolls plugin;

    public DatabaseProvider() {
        this.plugin = IOSteinPolls.getInstance();
        this.connection = connect();

        createTables();

        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        File databaseFile = new File(plugin.getDataFolder(), "database.db");

        if (!databaseFile.exists()) {
            plugin.saveResource(plugin.getDataFolder() + "/database.db", false);
        }
    }

    private Connection connect() {
        if (!isConnecced()) {
            try {
                Class.forName("org.sqlite.JDBC");
                Connection localConnection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() + "/database.db");
                plugin.getLogger().info("Connected to SQLite database");
                return localConnection;
            } catch (SQLException | ClassNotFoundException e) {
                plugin.getLogger().warning("Could not connect to SQLite database");
                e.printStackTrace();
            }

        }
        return null;
    }

    public void createTables() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `polls` " +
                    "(question TEXT, duration INTEGER,access INTEGER, creator TEXT,positive_answer TEXT, negative_answer TEXT, positive_votes INTEGER, negative_votes INTEGER)");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnecced() {
        return (connection != null);
    }

    public Connection getConnection() {
        return connection;
    }
}
