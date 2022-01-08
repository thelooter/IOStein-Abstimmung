package eu.thelooter.iosteinpolls.inventories;

import eu.thelooter.iosteinpolls.util.items.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PollCreateInventory {

    private final TextComponent INVENTORY_TITLE = LegacyComponentSerializer.legacy('§').deserialize("§8» §f§lPOLL §8create");

    public Inventory createPollCreateInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 4 * 9, INVENTORY_TITLE);


        ItemStack background = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                .setName(Component.empty())
                .toItemStack();

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, background);
        }

        ItemStack question = new ItemBuilder(Material.PAPER)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§8» §dAbstimmungsfrage").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§eGebe hier die Frage ein die du").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§ebei deiner Abstimmung fragen willst").decoration(TextDecoration.ITALIC, false))
                .toItemStack();

        ItemStack pollTime = new ItemBuilder(Material.CLOCK)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§8» §bAbstimmungszeit").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§aDauer » §a10 Minuten").decoration(TextDecoration.ITALIC, false))
                .toItemStack();

        inventory.setItem(12, question);
        inventory.setItem(16, pollTime);

        return inventory;
    }
}
