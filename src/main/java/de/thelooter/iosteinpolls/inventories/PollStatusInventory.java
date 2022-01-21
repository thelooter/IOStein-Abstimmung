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

public class PollStatusInventory {

    private final TextComponent TITLE_COMPONENT = LegacyComponentSerializer.legacy('§').deserialize("§8» §6§lAbstimmung");

    public void createPollStatusInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(player, 3 * 9, TITLE_COMPONENT);

        ItemStack background = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                .setName(Component.empty())
                .toItemStack();

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, background);
        }

        ItemStack positive = new ItemBuilder(Material.LIME_STAINED_GLASS)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§a→ " + IOSteinPolls.getInstance().getCurrentPoll().getPositiveAnswer()).decoration(TextDecoration.ITALIC,false))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§eStimmen:").decoration(TextDecoration.ITALIC,false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§b{§e" + IOSteinPolls.getInstance().getCurrentPoll().getPositiveVotes() + "§b} §efür §a" + IOSteinPolls.getInstance().getCurrentPoll().getPositiveAnswer()).decoration(TextDecoration.ITALIC,false))
                .toItemStack();

        ItemStack negative = new ItemBuilder(Material.RED_STAINED_GLASS)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§c→ " + IOSteinPolls.getInstance().getCurrentPoll().getNegativeAnswer()).decoration(TextDecoration.ITALIC,false))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§eStimmen:").decoration(TextDecoration.ITALIC,false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§b{§e" + IOSteinPolls.getInstance().getCurrentPoll().getNegativeVotes() + "§b} §efür §c" + IOSteinPolls.getInstance().getCurrentPoll().getNegativeAnswer()).decoration(TextDecoration.ITALIC,false))
                .toItemStack();


        inventory.setItem(11, positive);
        inventory.setItem(15, negative);

        player.openInventory(inventory);

    }

}
