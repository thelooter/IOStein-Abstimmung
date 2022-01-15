package de.thelooter.iosteinpolls.commands.pollcommand.subcommands;

import de.thelooter.iosteinpolls.IOSteinPolls;
import org.bukkit.entity.Player;

public class PollDeleteSubCommand {
    public PollDeleteSubCommand(Player player) {
        IOSteinPolls.getInstance().getCurrentPoll().delete();
    }
}
