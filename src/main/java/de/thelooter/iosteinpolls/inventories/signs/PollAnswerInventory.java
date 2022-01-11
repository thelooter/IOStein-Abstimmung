package de.thelooter.iosteinpolls.inventories.signs;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.util.Poll;
import de.thelooter.iosteinpolls.util.signs.SignMenuFactory1_17;
import org.bukkit.entity.Player;

import java.util.Collections;

public class PollAnswerInventory {

    public PollAnswerInventory(Player player) {
        open(null,player);
    }

    public void open(Poll poll, Player player) {
        SignMenuFactory1_17 factory = new SignMenuFactory1_17(IOSteinPolls.getInstance());
        factory.newMenu(Collections.emptyList());;
    }
}
