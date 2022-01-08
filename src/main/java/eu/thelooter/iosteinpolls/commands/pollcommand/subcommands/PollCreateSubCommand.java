package eu.thelooter.iosteinpolls.commands.pollcommand.subcommands;

import eu.thelooter.iosteinpolls.inventories.PollCreateInventory;
import org.bukkit.entity.Player;

public class PollCreateSubCommand {

    public PollCreateSubCommand(Player player) {
        createPoll(player);
    }

    private void createPoll(Player player) {

        player.openInventory(new PollCreateInventory().createPollCreateInventory(player));

        //TODO Open Create Poll GUI
    }
}
