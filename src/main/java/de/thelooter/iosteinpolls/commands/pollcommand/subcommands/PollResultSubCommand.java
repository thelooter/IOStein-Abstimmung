package de.thelooter.iosteinpolls.commands.pollcommand.subcommands;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.inventories.PollResultInventory;
import org.bukkit.entity.Player;

public class PollResultSubCommand {

    public PollResultSubCommand(Player player) {
        if (IOSteinPolls.getInstance().getCurrentPoll().getQuestion() != null) {
            player.sendMessage("§cDie Umfrage läuft noch! Nutze §a/poll status §cum den aktuellen Stand zu sehen");
            return;
        }
        new PollResultInventory().createPollResultInventory(player);
    }
}
