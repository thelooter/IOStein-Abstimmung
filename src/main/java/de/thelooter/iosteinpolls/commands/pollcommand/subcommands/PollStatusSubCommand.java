package de.thelooter.iosteinpolls.commands.pollcommand.subcommands;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.inventories.PollStatusInventory;
import org.bukkit.entity.Player;

public class PollStatusSubCommand {
    public PollStatusSubCommand(Player player) {
        if (IOSteinPolls.getInstance().getCurrentPoll().getQuestion() == null) {
            player.sendMessage("§cAktuell läuft keine Umfrage!");
            return;
        }
        new PollStatusInventory().createPollStatusInventory(player);
    }
}
