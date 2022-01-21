package de.thelooter.iosteinpolls.commands.pollcommand.subcommands;

import de.thelooter.iosteinpolls.IOSteinPolls;
import org.bukkit.entity.Player;

public class PollEndSubCommand {
    public PollEndSubCommand(Player player) {
        if (IOSteinPolls.getInstance().getCurrentPoll().getQuestion() == null) {
            player.sendMessage("§cAktuell läuft keine Umfrage!");
            return;
        }
        IOSteinPolls.getInstance().getCurrentPoll().endVote();
    }
}
