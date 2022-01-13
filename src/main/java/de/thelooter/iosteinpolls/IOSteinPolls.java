package de.thelooter.iosteinpolls;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
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

    private ProtocolManager manager;

    @Override
    public void onEnable() {





        instance = this;

        connection = new DatabaseProvider().getConnection();

        pollManager = new PollManager(this);
        currentPoll = new Poll();

        manager = ProtocolLibrary.getProtocolManager();

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

    public ProtocolManager getManager() {
        return manager;
    }
}
