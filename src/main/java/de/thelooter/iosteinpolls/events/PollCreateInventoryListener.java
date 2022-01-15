package de.thelooter.iosteinpolls.events;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.inventories.PollQuestionInventory;
import de.thelooter.iosteinpolls.inventories.signs.PollAnswerInventory;
import de.thelooter.iosteinpolls.util.PollTime;
import de.thelooter.iosteinpolls.util.items.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PollCreateInventoryListener implements Listener {


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
                case CLOCK -> {
                    event.setCancelled(true);

                    int duration = IOSteinPolls.getInstance().getCurrentPoll().getDuration();


                    if (duration >= PollTime.values().length - 1) {
                        duration = 0;
                    } else {
                        duration++;
                    }

                    IOSteinPolls.getInstance().getCurrentPoll().setDuration(duration);
                    IOSteinPolls.getInstance().getPollManager().setDuration(IOSteinPolls.getInstance().getCurrentPoll(), duration);

                    String loreString = PollTime.values()[duration].loreString;

                    ItemStack newTime = new ItemBuilder(Material.CLOCK)
                            .setName(LegacyComponentSerializer.legacy('§').deserialize("§8» §bAbstimmungszeit"))
                            .addLoreLine(Component.empty())
                            .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§aDauer » §b" + loreString))
                            .toItemStack();

                    event.getView().getTopInventory().setItem(event.getSlot(), newTime);

                }
                case PLAYER_HEAD -> {
                    event.setCancelled(true);

                    IOSteinPolls.getInstance().getCurrentPoll().setCreator(player);
                    IOSteinPolls.getInstance().getPollManager().createPoll(IOSteinPolls.getInstance().getCurrentPoll());

                    if (IOSteinPolls.getInstance().getCurrentPoll().isOnlyTeamAccess()) {
                        System.out.println("YAAA");
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize("§7▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒"), "iostein.polls.team");
                        Bukkit.broadcast(Component.empty(), "iostein.polls.team");
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize("               §6Abstimmung"), "iostein.polls.team");
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize("§evon §c" + IOSteinPolls.getInstance().getCurrentPoll().getCreator().getName()), "iostein.polls.team");
                        Bukkit.broadcast(Component.empty(), "iostein.polls.team");
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize(IOSteinPolls.getInstance().getCurrentPoll().getQuestion()), "iostein.polls.team");
                        Bukkit.broadcast(Component.empty(), "iostein.polls.team");
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize("§e{Klicke zum Abstimmen"), "iostein.polls.team");
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize("§7▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒"), "iostein.polls.team");
                    } else {
                        System.out.println("NOOOO");
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize("§7▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒"));
                        Bukkit.broadcast(Component.empty());
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize("               §6Abstimmung"));
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize("             §evon §c" + IOSteinPolls.getInstance().getCurrentPoll().getCreator().getName()));
                        Bukkit.broadcast(Component.empty());
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize(IOSteinPolls.getInstance().getCurrentPoll().getQuestion()));
                        Bukkit.broadcast(Component.empty(), "iostein.polls.team");
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize("        §e[Klicke zum Abstimmen]"));
                        Bukkit.broadcast(LegacyComponentSerializer.legacy('§').deserialize("§7▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒"));
                    }


                    player.closeInventory();
                }
                case LIME_DYE -> {

                    event.setCancelled(true);

                    IOSteinPolls.getInstance().getCurrentPoll().setOnlyTeamAccess(true);
                    IOSteinPolls.getInstance().getPollManager().setAccess(IOSteinPolls.getInstance().getCurrentPoll(), true);

                    ItemStack teamAccess = new ItemBuilder(Material.RED_DYE)
                            .setName(LegacyComponentSerializer.legacy('§').deserialize("§8» §cZugriff"))
                            .addLoreLine(Component.empty())
                            .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§enur §cTeam"))
                            .toItemStack();

                    event.getView().getTopInventory().setItem(event.getSlot(), teamAccess);




                }
                case RED_DYE -> {

                    event.setCancelled(true);

                    IOSteinPolls.getInstance().getCurrentPoll().setOnlyTeamAccess(false);
                    IOSteinPolls.getInstance().getPollManager().setAccess(IOSteinPolls.getInstance().getCurrentPoll(), false);

                    ItemStack teamAccess = new ItemBuilder(Material.LIME_DYE)
                            .setName(LegacyComponentSerializer.legacy('§').deserialize("§8» §aZugriff"))
                            .addLoreLine(Component.empty())
                            .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§eAlle"))
                            .toItemStack();



                    event.getView().getTopInventory().setItem(event.getSlot(), teamAccess);
                }

            }
        }
    }
}
