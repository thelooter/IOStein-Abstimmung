package de.thelooter.iosteinpolls.inventories;

import de.thelooter.iosteinpolls.IOSteinPolls;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PollQuestionInventory {

    private final IOSteinPolls plugin;

    public PollQuestionInventory(IOSteinPolls plugin) {
        this.plugin = plugin;
    }

    public void createPollQuestionInventory(Player player) {

        AnvilGUI.Builder inventory = new AnvilGUI.Builder();

        inventory.title("Frage");
        inventory.plugin(plugin);
        inventory.text("Gib die Frage ein");

        ItemStack leftItem = new ItemStack(Material.PAPER);

        inventory.itemLeft(leftItem);

        inventory.onClose(guiPlayer -> {
        });

        inventory.onComplete((completePlayer, text) -> {

            if (text == null) {
                return AnvilGUI.Response.text("Du musst eine Frage eingeben");
            }
            plugin.getCurrentPoll().setQuestion(text);

            plugin.getCurrentPoll().setCreator(player);
            System.out.println(plugin.getCurrentPoll().getQuestion());
            return AnvilGUI.Response.openInventory(new PollCreateInventory().createPollCreateInventory(player));
        });

        inventory.open(player);
    }

}
