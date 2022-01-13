package de.thelooter.iosteinpolls.inventories.signs;

import de.thelooter.iosteinpolls.util.Poll;
import de.thelooter.iosteinpolls.util.signs.SignMenu;
import org.bukkit.entity.Player;

public class PollAnswerInventory {

    public PollAnswerInventory(Player player) {
        open(null,player);
    }

    public void open(Poll poll, Player player) {
        new SignMenu(player).openSignMenu();

    }
}
