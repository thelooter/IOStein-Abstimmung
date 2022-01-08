package eu.thelooter.iosteinpolls.database;

import eu.thelooter.iosteinpolls.IOSteinPolls;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseProvider {

    private final Connection connection;
    private final IOSteinPolls plugin;

    private File databaseFile;

    public DatabaseProvider() {
        this.plugin = IOSteinPolls.getInstance();
        this.connection = connect();

        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        this.databaseFile = new File(plugin.getDataFolder(), "database.db");

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

    public boolean isConnecced() {
        return (connection != null);
    }

    public Connection getConnection() {
        return connection;
    }
}
