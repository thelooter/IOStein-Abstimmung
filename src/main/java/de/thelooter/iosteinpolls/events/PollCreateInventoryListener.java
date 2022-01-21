package de.thelooter.iosteinpolls.events;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.inventories.PollQuestionInventory;
import de.thelooter.iosteinpolls.inventories.signs.PollAnswerInventory;
import de.thelooter.iosteinpolls.util.PollTime;
import de.thelooter.iosteinpolls.util.StringUtils;
import de.thelooter.iosteinpolls.util.items.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

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

                    if (IOSteinPolls.getInstance().getCurrentPoll().getQuestion() == null
                            || IOSteinPolls.getInstance().getCurrentPoll().getPositiveAnswer() == null
                            || IOSteinPolls.getInstance().getCurrentPoll().getNegativeAnswer() == null) {
                        player.closeInventory();
                        player.sendMessage("§cDu hast vergessen alle Werte auszufüllen. Versuche es noch einmal!");
                        return;
                    }

                    IOSteinPolls.getInstance().getCurrentPoll().setCreator(player);

                    IOSteinPolls.getInstance().getPollManager().createPoll(IOSteinPolls.getInstance().getCurrentPoll());

                    int id = IOSteinPolls.getInstance().getPollManager().startPollTimer(IOSteinPolls.getInstance().getCurrentPoll());

                    IOSteinPolls.getInstance().getCurrentPoll().setTaskID(id);

                    List<String> messageStrings = new ArrayList<>();
                    messageStrings.add("§7§l--------------------------");
                    messageStrings.add("");
                    messageStrings.add("§6Abstimmung");
                    messageStrings.add("§evon §c" + IOSteinPolls.getInstance().getCurrentPoll().getCreator().getName());
                    messageStrings.add("");
                    messageStrings.add(IOSteinPolls.getInstance().getCurrentPoll().getQuestion());
                    messageStrings.add("");
                    messageStrings.add("§e[Klicke zum Abstimmen]");
                    messageStrings.add("");
                    messageStrings.add("§7§l--------------------------");

                    List<String> paddedString = StringUtils.pad(messageStrings);



                    List<TextComponent> messageComponents = new ArrayList<>();

                    for (String messageString : paddedString) {

                        if (messageString.contains("§e[Klicke zum Abstimmen]")) {
                            messageComponents.add(LegacyComponentSerializer.legacy('§').deserialize(messageString)
                                    .clickEvent(ClickEvent.runCommand("/poll")));
                            continue;
                        }
                        messageComponents.add(LegacyComponentSerializer.legacy('§').deserialize(messageString));

                    }

                    if (IOSteinPolls.getInstance().getCurrentPoll().isOnlyTeamAccess()) {
                        for (Player loopPlayer : Bukkit.getOnlinePlayers()) {
                            if (loopPlayer.hasPermission("iostein.polls.team")) {
                                for (TextComponent messageComponent : messageComponents) {
                                    loopPlayer.sendMessage(messageComponent);
                                }
                            }
                        }
                    } else {
                        for (Player loopPlayer : Bukkit.getOnlinePlayers()) {
                            for (TextComponent messageComponent : messageComponents) {
                                loopPlayer.sendMessage(messageComponent);
                            }
                        }
                    }


                    player.closeInventory();
                }
                case LIME_DYE -> {

                    event.setCancelled(true);

                    IOSteinPolls.getInstance().getCurrentPoll().setOnlyTeamAccess(true);

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

                    ItemStack teamAccess = new ItemBuilder(Material.LIME_DYE)
                            .setName(LegacyComponentSerializer.legacy('§').deserialize("§8» §aZugriff"))
                            .addLoreLine(Component.empty())
                            .addLoreLine(LegacyComponentSerializer.legacy('§').deserialize("§eAlle"))
                            .toItemStack();


                    event.getView().getTopInventory().setItem(event.getSlot(), teamAccess) ;
                }

            }
        }
    }
}
