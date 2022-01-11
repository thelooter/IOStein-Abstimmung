package de.thelooter.iosteinpolls;

import de.thelooter.iosteinpolls.commands.pollcommand.PollCommand;
import de.thelooter.iosteinpolls.database.DatabaseProvider;
import de.thelooter.iosteinpolls.events.InventoryEvents;
import de.thelooter.iosteinpolls.manager.PollManager;
import de.thelooter.iosteinpolls.util.Poll;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

public final class IOSteinPolls extends JavaPlugin {


    private static IOSteinPolls instance;
    private static Connection connection;

    private PollManager pollManager;

    private Poll currentPoll;


    @Override
    public void onEnable() {




        instance = this;

        connection = new DatabaseProvider().getConnection();

        pollManager = new PollManager(this);

        //Command Registration
        getCommand("poll").setExecutor(new PollCommand());

        //Event Listener Registration
        getServer().getPluginManager().registerEvents(new InventoryEvents(), this);
    }

    @Override
    public void onDisable() {
    }

    public static IOSteinPolls getInstance() {
        return instance;
    }

    public  Connection getConnection() {
        return connection;
    }

    public PollManager getPollManager() {
        return pollManager;
    }

    public Poll getCurrentPoll() {
        return currentPoll;
    }

}
