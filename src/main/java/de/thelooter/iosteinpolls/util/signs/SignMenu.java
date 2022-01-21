package de.thelooter.iosteinpolls.util.signs;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.inventories.PollCreateInventory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SignMenu {


    private final IOSteinPolls plugin = IOSteinPolls.getInstance();
    private final Player player;

    private Location signLocation;

    private boolean secondPage = false;

    private static final List<Player> openInvs = new ArrayList<>();

    public SignMenu(Player player) {
        this.player = player;
        listen();
    }

    public void openSignMenu() {
        if (player == null || !player.isOnline()) {
            return;
        }

        Location location = player.getLocation();

        BlockPosition position = new BlockPosition(location.getBlockX(), location.getBlockY() + (255 - location.getBlockY()), location.getBlockZ());
        signLocation = position.toLocation(location.getWorld());

        player.sendBlockChange(position.toLocation(player.getWorld()), Material.OAK_SIGN.createBlockData());


        PacketContainer signPacket = plugin.getManager().createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);

        signPacket.getBlockPositionModifier().write(0, position);

        try {
            plugin.getManager().sendServerPacket(player, signPacket);
            openInvs.add(player);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    private void listen() {
        plugin.getManager().addPacketListener(new PacketAdapter(plugin, PacketType.Play.Client.UPDATE_SIGN) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (!openInvs.contains(event.getPlayer())) {
                    return;
                }

                BlockPosition position = event.getPacket().getBlockPositionModifier().read(0);

                if (signLocation.equals(position.toLocation(event.getPlayer().getWorld()))) {
                    if (secondPage) {
                        IOSteinPolls.getInstance().getCurrentPoll().setNegativeAnswer(event.getPacket().getStringArrays().read(0)[0]);
                        player.sendBlockChange(position.toLocation(player.getWorld()), Material.AIR.createBlockData());

                        openInvs.remove(event.getPlayer());
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            player.openInventory(new PollCreateInventory().createPollCreateInventory(player));

                        }, 2);


                    } else {
                        IOSteinPolls.getInstance().getCurrentPoll().setPositiveAnswer(event.getPacket().getStringArrays().read(0)[0]);

                        player.sendBlockChange(position.toLocation(player.getWorld()), Material.AIR.createBlockData());

                        secondPage = true;
                        openSignMenu();
                    }
                }

            }
        });
    }


}
