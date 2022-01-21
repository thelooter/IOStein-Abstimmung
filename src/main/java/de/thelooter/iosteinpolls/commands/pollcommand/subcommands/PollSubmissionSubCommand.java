package de.thelooter.iosteinpolls.commands.pollcommand.subcommands;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.inventories.PollSubmissionInventory;
import org.bukkit.entity.Player;

public class PollSubmissionSubCommand {

    public PollSubmissionSubCommand(Player player) {
        if (IOSteinPolls.getInstance().getCurrentPoll().getQuestion() == null) {
            player.sendMessage("§cAktuell läuft keine Umfrage!");
            return;
        }

        if (IOSteinPolls.getInstance().getPollManager().hasPlayerVoted(player)) {
            player.sendMessage("§cDu hast bereits abgestimmt!");
            return;
        }
        openSubmissionInventory(player);

    }

        private void openSubmissionInventory(Player player) {
        player.openInventory(new PollSubmissionInventory().createPollSubmissionInventory(player));
    }
}
