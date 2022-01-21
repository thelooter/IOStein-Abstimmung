package de.thelooter.iosteinpolls;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import de.thelooter.iosteinpolls.commands.pollcommand.PollCommand;
import de.thelooter.iosteinpolls.commands.pollcommand.PollCommandCompleter;
import de.thelooter.iosteinpolls.database.DatabaseProvider;
import de.thelooter.iosteinpolls.events.PollCreateInventoryListener;
import de.thelooter.iosteinpolls.events.PollResultInventoryListener;
import de.thelooter.iosteinpolls.events.PollStatusInventoryListener;
import de.thelooter.iosteinpolls.events.PollSubmissionInventoryListener;
import de.thelooter.iosteinpolls.manager.PollManager;
import de.thelooter.iosteinpolls.util.Poll;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

public final class IOSteinPolls extends JavaPlugin {


    private static IOSteinPolls instance;
    private static Connection connection;

    private PollManager pollManager;

    private Poll currentPoll;
    private Poll finishedPoll;


    private ProtocolManager manager;

    @Override
    public void onEnable() {


        instance = this;

        connection = new DatabaseProvider().getConnection();

        pollManager = new PollManager(this);
        currentPoll = new Poll(this);

        manager = ProtocolLibrary.getProtocolManager();

        //Command Registration
        getCommand("poll").setExecutor(new PollCommand());

        //Tab Completer Registration
        getCommand("poll").setTabCompleter(new PollCommandCompleter());

        //Event Listener Registration
        getServer().getPluginManager().registerEvents(new PollCreateInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PollSubmissionInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PollStatusInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PollResultInventoryListener(), this);


    }

    @Override
    public void onDisable() {
    }

    public static IOSteinPolls getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public PollManager getPollManager() {
        return pollManager;
    }

    public Poll getCurrentPoll() {
        return currentPoll;
    }

    public Poll getFinishedPoll() {
        return finishedPoll;
    }

    public void setFinishedPoll(Poll finishedPoll) {
        this.finishedPoll = finishedPoll;
    }

    public ProtocolManager getManager() {
        return manager;
    }


}
