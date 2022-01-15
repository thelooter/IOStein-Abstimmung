package de.thelooter.iosteinpolls.inventories;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.util.items.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PollSubmissionInventory {

    private final TextComponent INVENTORY_TITLE = LegacyComponentSerializer.legacy('§').deserialize("§8» §6§lAbstimmung");

    public Inventory createPollSubmissionInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 3 * 9, INVENTORY_TITLE);

        ItemStack background = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                .setName(Component.empty())
                .toItemStack();

        ItemStack yes = new ItemBuilder(Material.LIME_TERRACOTTA)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§aJa!").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§7Stimme hiermit für §a " +IOSteinPolls.getInstance().getCurrentPoll().getPositiveAnswer() + " §7ab"))
                .toItemStack();

        ItemStack no = new ItemBuilder(Material.RED_TERRACOTTA)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§cNein!").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§7Stimme hiermit für §c" + IOSteinPolls.getInstance().getCurrentPoll().getNegativeAnswer() +" §7ab"))
                .toItemStack();

        ItemStack question = new ItemBuilder(Material.PAPER)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§7Frage:").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§7" + IOSteinPolls.getInstance().getCurrentPoll().getQuestion()))
                .toItemStack();

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, background);
        }

        inventory.setItem(11, yes);
        inventory.setItem(15, no);
        inventory.setItem(18, question);

        return inventory;
    }
}
