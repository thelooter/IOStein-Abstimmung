package de.thelooter.iosteinpolls.inventories;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.util.items.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PollResultInventory {

    private final TextComponent title = LegacyComponentSerializer.legacy('§').deserialize("§8» §e§lERGEBNIS");

    public void createPollResultInventory(Player player) {

        int positiveVotes = IOSteinPolls.getInstance().getFinishedPoll().getPositiveVotes();
        int negativeVotes = IOSteinPolls.getInstance().getFinishedPoll().getNegativeVotes();

        String positiveAnswer = IOSteinPolls.getInstance().getFinishedPoll().getPositiveAnswer();
        String negativeAnswer = IOSteinPolls.getInstance().getFinishedPoll().getNegativeAnswer();

        Inventory inventory = player.getServer().createInventory(player, 27, title);

        ItemStack background = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                .setName(Component.empty())
                .toItemStack();

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, background);
        }

        ItemStack positive = new ItemBuilder(Material.LIME_TERRACOTTA)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§a§l➛ §e" + positiveAnswer).decoration(TextDecoration.ITALIC,false))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§bStimmen:").decoration(TextDecoration.ITALIC,false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§b" + positiveVotes + " §eStimmen für §a" + positiveAnswer).decoration(TextDecoration.ITALIC,false))
                .toItemStack();

        ItemStack negative = new ItemBuilder(Material.RED_TERRACOTTA)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§c§l➛ " + negativeAnswer).decoration(TextDecoration.ITALIC,false))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§bStimmen:").decoration(TextDecoration.ITALIC,false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§b" + negativeVotes + " §eStimmen für §a" + negativeAnswer).decoration(TextDecoration.ITALIC,false))
                .toItemStack();

        ItemStack description = new ItemBuilder(Material.PAPER)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§eSiehe hier das Ergebnis der Abstimmung ein").decoration(TextDecoration.ITALIC,false))
                .toItemStack();

        inventory.setItem(10, positive);
        inventory.setItem(16, negative);

        inventory.setItem(22, description);

        player.openInventory(inventory);
    }
}
