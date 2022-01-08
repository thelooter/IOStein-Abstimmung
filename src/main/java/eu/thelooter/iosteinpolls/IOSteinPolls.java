package eu.thelooter.iosteinpolls;

import eu.thelooter.iosteinpolls.commands.pollcommand.PollCommand;
import eu.thelooter.iosteinpolls.database.DatabaseProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

public final class IOSteinPolls extends JavaPlugin {


    private static IOSteinPolls instance;
    private static Connection connection;


    @Override
    public void onEnable() {



        instance = this;

        connection = new DatabaseProvider().getConnection();


        //Command Registration
        getCommand("poll").setExecutor(new PollCommand());

    }

    @Override
    public void onDisable() {
    }

    public static IOSteinPolls getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        return connection;
    }
}
