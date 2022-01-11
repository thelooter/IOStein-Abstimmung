package de.thelooter.iosteinpolls.inventories;

import de.thelooter.iosteinpolls.IOSteinPolls;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.entity.Player;

public class PollQuestionInventory {

    private final IOSteinPolls plugin;

    public PollQuestionInventory(IOSteinPolls plugin) {
        this.plugin = plugin;
    }

    public void createPollQuestionInventory(Player player) {

        AnvilGUI.Builder inventory = new AnvilGUI.Builder();

        inventory.title("Frage");
        inventory.plugin(plugin);

        inventory.onClose(guiPlayer -> {
        });

        inventory.onComplete((completePlayer, text) -> {
            if (text.length() == 0) {
                player.sendMessage("Du musst eine Frage eingeben!");
            } else {
                plugin.getCurrentPoll().setQuestion(text);
                plugin.getCurrentPoll().setCreator(player);
            }
            return AnvilGUI.Response.openInventory(new PollCreateInventory().createPollCreateInventory(player));
        });

        inventory.open(player);
    }

}
