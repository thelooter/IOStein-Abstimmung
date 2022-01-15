package de.thelooter.iosteinpolls.inventories;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.util.items.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PollResultInventory {

    private final TextComponent title = LegacyComponentSerializer.legacy('§').deserialize("§8» §eERGEBNIS");

    public void createPollResultInventory(Player player) {

        int positiveVotes = IOSteinPolls.getInstance().getCurrentPoll().getPositiveVotes();
        int negativeVotes = IOSteinPolls.getInstance().getCurrentPoll().getNegativeVotes();

        String positiveAnswer = IOSteinPolls.getInstance().getCurrentPoll().getPositiveAnswer();
        String negativeAnswer = IOSteinPolls.getInstance().getCurrentPoll().getNegativeAnswer();

        Inventory inventory = player.getServer().createInventory(player, 27, title);

        ItemStack background = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                .setName(Component.empty())
                .toItemStack();

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, background);
        }

        ItemStack positive = new ItemBuilder(Material.LIME_TERRACOTTA)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§a§l➛ §e" + positiveAnswer))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§bStimmen:"))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§b" + positiveVotes + "§eStimmen für §a" + positiveAnswer))
                .toItemStack();

        ItemStack negative = new ItemBuilder(Material.RED_TERRACOTTA)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§c§l➛ " + negativeAnswer))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§bStimmen:"))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§b" + negativeVotes + "§eStimmen für §a" + negativeAnswer))
                .toItemStack();

        ItemStack description = new ItemBuilder(Material.PAPER)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§eSiehe hier das Ergebnis der Abstimmung ein"))
                .toItemStack();

        inventory.setItem(12, positive);
        inventory.setItem(18, negative);

        inventory.setItem(24, description);

        player.openInventory(inventory);
    }
}
