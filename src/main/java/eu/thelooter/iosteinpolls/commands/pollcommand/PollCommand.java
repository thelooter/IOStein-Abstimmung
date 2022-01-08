package eu.thelooter.iosteinpolls.commands.pollcommand;

import eu.thelooter.iosteinpolls.IOSteinPolls;
import eu.thelooter.iosteinpolls.commands.pollcommand.subcommands.PollCreateSubCommand;
import eu.thelooter.iosteinpolls.commands.pollcommand.subcommands.PollDeleteSubCommand;
import eu.thelooter.iosteinpolls.commands.pollcommand.subcommands.PollEndSubCommand;
import eu.thelooter.iosteinpolls.commands.pollcommand.subcommands.PollStatusSubCommand;
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
            //TODO add Submission GUI
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "create":
                    if (!player.hasPermission("iosteinpolls.create")) {
                        player.sendMessage("You don't have permission to create a poll!");
                        return true;
                    }
                    new PollCreateSubCommand(player);
                    //TODO create poll create GUI
                    break;
                case "end":
                    if (!player.hasPermission("iosteinpolls.end")) {
                        player.sendMessage("You don't have permission to end a poll!");
                    }
                    new PollEndSubCommand(player);
                    //TODO end Poll
                    break;
                case "delete":
                    if (!player.hasPermission("iosteinpolls.delete")) {
                        player.sendMessage("You don't have permission to delete a poll!");
                    }
                    new PollDeleteSubCommand(player);
                    //TODO Delete Poll
                    break;
                case "status":
                    if (!player.hasPermission("iosteinpolls.status")) {
                        player.sendMessage("You don't have permission to see the status of a poll!");
                    }
                    new PollStatusSubCommand(player);
                    //TODO create poll status GUI
                    break;
            }
        }
        return false;
    }

}
