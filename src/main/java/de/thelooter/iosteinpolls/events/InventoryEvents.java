package de.thelooter.iosteinpolls.events;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.inventories.PollQuestionInventory;
import de.thelooter.iosteinpolls.inventories.signs.PollAnswerInventory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryEvents implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {


        Player player = (Player) event.getWhoClicked();

        //Components
        final Component titleComponent = event.getView().title();
        final Component pollCreateTitleComponent = LegacyComponentSerializer.legacy('§').deserialize("§8» §f§lPOLL §8create");

        //Clicked Item
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }


        if (titleComponent.equals(pollCreateTitleComponent)) {

            switch (clickedItem.getType()) {
                case BLACK_STAINED_GLASS_PANE -> event.setCancelled(true);
                case OAK_SIGN -> {
                    event.setCancelled(true);
                    Bukkit.getScheduler().runTask(IOSteinPolls.getInstance(), () -> new PollAnswerInventory(player));
                }
                case PAPER -> {
                    event.setCancelled(true);
                    Bukkit.getScheduler().runTask(IOSteinPolls.getInstance(), () ->
                            new PollQuestionInventory(IOSteinPolls.getInstance()).createPollQuestionInventory(player));
                }
                case LIME_DYE, RED_DYE, CLOCK, PLAYER_HEAD -> {
                    event.setCancelled(true);
                }

            }
        }
    }
}
