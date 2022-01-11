package de.thelooter.iosteinpolls.inventories;

import de.thelooter.iosteinpolls.util.Heads;
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

        ItemStack answers = new ItemBuilder(Material.OAK_SIGN)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§8» §6Antwortmöglichkeiten").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§eGebe hier mit die Antwortmöglichkeiten").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§efür deine Abstimmung an, Standardmäßig").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§esind diese auf §aJa §eund §cNein").decoration(TextDecoration.ITALIC, false))
                .toItemStack();

        ItemStack question = new ItemBuilder(Material.PAPER)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§8» §dAbstimmungsfrage").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§eGebe hier die Frage ein die du").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§ebei deiner Abstimmung fragen willst").decoration(TextDecoration.ITALIC, false))
                .toItemStack();

        ItemStack access = new ItemBuilder(Material.LIME_DYE)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§8» §cZugriff").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§eAlle").decoration(TextDecoration.ITALIC, false))
                .toItemStack();

        ItemStack pollTime = new ItemBuilder(Material.CLOCK)
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§8» §bAbstimmungszeit").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§aDauer » §b10 Minuten").decoration(TextDecoration.ITALIC, false))
                .toItemStack();

        ItemStack checkmark = new ItemBuilder(Heads.CHECKMARK.createHead(Heads.CHECKMARK))
                .setName(LegacyComponentSerializer.legacy('§').deserialize("§a» Start").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(Component.empty())
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§eStarte hiermit die Abstimmung wenn").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§edu zufrieden mit deinen Einstellungen").decoration(TextDecoration.ITALIC, false))
                .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§ebist").decoration(TextDecoration.ITALIC, false))
                .toItemStack();


        inventory.setItem(10, answers);
        inventory.setItem(12, question);
        inventory.setItem(14, access);
        inventory.setItem(16, pollTime);

        inventory.setItem(31, checkmark);

        return inventory;
    }
}
