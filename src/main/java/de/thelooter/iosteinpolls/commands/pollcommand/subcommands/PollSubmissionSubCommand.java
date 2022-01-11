package de.thelooter.iosteinpolls.commands.pollcommand.subcommands;

import de.thelooter.iosteinpolls.inventories.PollSubmissionInventory;
import org.bukkit.entity.Player;

public class PollSubmissionSubCommand {

    public PollSubmissionSubCommand(Player player) {
        openSubmissionInventory(player);
    }

    private void openSubmissionInventory(Player player) {
        player.openInventory(new PollSubmissionInventory().createPollSubmissionInventory(player));
    }
}
