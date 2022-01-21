package de.thelooter.iosteinpolls.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PollResultInventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        //Components
        final Component titleComponent = event.getView().title();
        final Component pollCreateTitleComponent = LegacyComponentSerializer.legacy('§').deserialize("§8» §e§lERGEBNIS");

        //Clicked Item
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        if (titleComponent.equals(pollCreateTitleComponent)) {
            event.setCancelled(true);
        }
    }
}
