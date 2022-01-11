package de.thelooter.iosteinpolls.commands.pollcommand;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.commands.pollcommand.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PollCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            IOSteinPolls.getInstance().getLogger().info("Only players can execute this command!");
            return false;
        }

        if (args.length == 0) {
            new PollSubmissionSubCommand(player);
            return true;
        }


        if (args.length == 1) {
            switch (args[0]) {
                case "create" -> {
                    if (!player.hasPermission("iosteinpolls.create")) {
                        player.sendMessage("You don't have permission to create a poll!");
                        return true;
                    }
                    new PollCreateSubCommand(player);
                    return true;
                }
                //TODO create poll create GUI
                case "end" -> {
                    if (!player.hasPermission("iosteinpolls.end")) {
                        player.sendMessage("You don't have permission to end a poll!");
                        return true;
                    }
                    new PollEndSubCommand(player);
                    return true;
                }
                //TODO end Poll
                case "delete" -> {
                    if (!player.hasPermission("iosteinpolls.delete")) {
                        player.sendMessage("You don't have permission to delete a poll!");
                        return true;
                    }
                    new PollDeleteSubCommand(player);
                    return true;
                }
                //TODO Delete Poll
                case "status" -> {
                    if (!player.hasPermission("iosteinpolls.status")) {
                        player.sendMessage("You don't have permission to see the status of a poll!");
                        return true;
                    }
                    new PollStatusSubCommand(player);
                    return true;
                }
                //TODO create poll status GUI
            }
        }
        return false;
    }

}
