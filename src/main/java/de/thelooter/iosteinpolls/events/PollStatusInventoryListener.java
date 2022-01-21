package de.thelooter.iosteinpolls.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PollStatusInventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        final Component title = event.getView().title();

        final Component pollStatusInventoryTitle = LegacyComponentSerializer.legacy('§').deserialize("§8» §6§lAbstimmung");

        if (title.equals(pollStatusInventoryTitle)) {
            event.setCancelled(true);
        }
    }
}
