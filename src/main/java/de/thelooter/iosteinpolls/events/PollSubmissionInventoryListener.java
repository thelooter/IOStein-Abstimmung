package de.thelooter.iosteinpolls.events;

import de.thelooter.iosteinpolls.IOSteinPolls;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PollSubmissionInventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        //Components
        final Component titleComponent = event.getView().title();
        final Component pollSubmissionComponent = LegacyComponentSerializer.legacy('§').deserialize("§8» §6§lAbstimmung");

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        if (titleComponent.equals(pollSubmissionComponent)) {
            switch (clickedItem.getType()) {
                case BLACK_STAINED_GLASS_PANE, PAPER -> event.setCancelled(true);
                case LIME_TERRACOTTA -> {
                    event.setCancelled(true);
                    IOSteinPolls.getInstance().getCurrentPoll().addVote(true);
                    player.closeInventory();
                    player.sendMessage("§eDu hast für §a" + IOSteinPolls.getInstance().getCurrentPoll().getPositiveAnswer() + " §egestimmt");
                                    }
                case RED_TERRACOTTA -> {
                    event.setCancelled(true);
                    IOSteinPolls.getInstance().getCurrentPoll().addVote(false);
                    player.closeInventory();
                    player.sendMessage("§eDu hast für §c" + IOSteinPolls.getInstance().getCurrentPoll().getNegativeAnswer() + " §egestimmt");


                }
            }
        }
    }
}
