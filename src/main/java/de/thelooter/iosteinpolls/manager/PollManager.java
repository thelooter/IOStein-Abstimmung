package de.thelooter.iosteinpolls.manager;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.util.Poll;
import de.thelooter.iosteinpolls.util.PollTime;
import de.thelooter.iosteinpolls.util.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PollManager {

    private final IOSteinPolls plugin;

    public PollManager(IOSteinPolls plugin) {
        this.plugin = plugin;
    }

    public void createPoll(Poll poll) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("INSERT INTO polls (question, duration, creator, positive_answer, negative_answer, positive_votes, negative_votes) VALUES (?,?,?, ?, ?, ?, ?)");
            preparedStatement.setString(1, poll.getQuestion());
            preparedStatement.setInt(2, poll.getDuration());
            preparedStatement.setString(3, poll.getCreator().getName());
            preparedStatement.setString(4, poll.getPositiveAnswer());
            preparedStatement.setString(5, poll.getNegativeAnswer());
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePoll(Poll poll) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("DELETE FROM polls WHERE question = ?");
            preparedStatement.setString(1, poll.getQuestion());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void submitVote(Poll poll, boolean positive, Player voter) {

        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("UPDATE polls SET positive_votes = ?, negative_votes = ? WHERE question = ?");
            if (positive) {
                preparedStatement.setInt(1, poll.getPositiveVotes());
                preparedStatement.setInt(2, poll.getNegativeVotes());
            } else {
                preparedStatement.setInt(1, poll.getPositiveVotes());
                preparedStatement.setInt(2, poll.getNegativeVotes());
            }
            preparedStatement.setString(3, poll.getQuestion());
            preparedStatement.execute();

            PreparedStatement playerStatement = plugin.getConnection().prepareStatement("INSERT INTO players_voted (player) VALUES (?)");
            playerStatement.setString(1, voter.getName());
            playerStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPositiveAnswer(Poll poll, String answer) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("UPDATE polls SET positive_answer = ? WHERE question = ?");
            preparedStatement.setString(1, answer);
            preparedStatement.setString(2, poll.getQuestion());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setNegativeAnswer(Poll poll, String answer) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("UPDATE polls SET negative_answer = ? WHERE question = ?");
            preparedStatement.setString(1, answer);
            preparedStatement.setString(2, poll.getQuestion());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDuration(Poll poll, int duration) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("UPDATE polls SET duration = ? WHERE question = ?");
            preparedStatement.setInt(1, duration);
            preparedStatement.setString(2, poll.getQuestion());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setQuestion(Poll poll, String question) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("UPDATE polls SET question = ? WHERE question = ?");
            preparedStatement.setString(1, question);
            preparedStatement.setString(2, poll.getQuestion());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAccess(Poll poll, boolean access) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("UPDATE polls SET access = ? WHERE question = ?");
            preparedStatement.setBoolean(1, access);
            preparedStatement.setString(2, poll.getQuestion());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPositiveVotes(Poll poll, int votes) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("UPDATE polls SET positive_votes = ? WHERE question = ?");
            preparedStatement.setInt(1, votes);
            preparedStatement.setString(2, poll.getQuestion());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setNegativeVotes(Poll poll, int votes) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("UPDATE polls SET negative_votes = ? WHERE question = ?");
            preparedStatement.setInt(1, votes);
            preparedStatement.setString(2, poll.getQuestion());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCreator(Poll poll, Player creator) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("UPDATE polls SET creator = ? WHERE question = ?");
            preparedStatement.setString(1, creator.getName());
            preparedStatement.setString(2, poll.getQuestion());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasPlayerVoted(Player player) {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("SELECT * FROM players_voted WHERE player = ?");
            preparedStatement.setString(1, player.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void clearPlayers() {
        try {
            PreparedStatement preparedStatement = plugin.getConnection().prepareStatement("DELETE FROM players_voted");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int startPollTimer(Poll poll) {
        BukkitTask task = plugin.getServer().getScheduler().runTaskLater(plugin, () -> endPoll(poll), PollTime.values()[poll.getDuration()].ticks);

        return task.getTaskId();


    }

    public void endPoll(Poll poll) {
        IOSteinPolls.getInstance().setFinishedPoll(new Poll(poll,plugin)); //Create new Poll Instance to avoid reference to old Object which will be reset later

        clearPlayers();

        List<String> messageStrings = new ArrayList<>();
        messageStrings.add("§7§l--------------------------");
        messageStrings.add(" ");
        messageStrings.add("§6Abstimmungsergebnis");
        messageStrings.add("§evon §c " + IOSteinPolls.getInstance().getFinishedPoll().getCreator().getName());
        messageStrings.add(" ");
        messageStrings.add("§7" + IOSteinPolls.getInstance().getFinishedPoll().getQuestion());
        messageStrings.add(" ");
        messageStrings.add("§e[Klicke für's Ergebnis]");
        messageStrings.add(" ");
        messageStrings.add("§7§l--------------------------");

        List<String> paddedStrings = StringUtils.pad(messageStrings);

        List<TextComponent> textComponents = new ArrayList<>();

        for (String string : paddedStrings) {
            if (string.contains("§e[Klicke für's Ergebnis]")) {
                textComponents.add(LegacyComponentSerializer.legacy('§').deserialize(string)
                        .clickEvent(ClickEvent.runCommand("/poll results")));
                continue;
            }
            textComponents.add(Component.text(string));
        }


        if (IOSteinPolls.getInstance().getFinishedPoll().isOnlyTeamAccess()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("iostein.polls.team")) {

                    for (TextComponent textComponent : textComponents) {
                        player.sendMessage(textComponent);
                    }

                }
            }
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (TextComponent textComponent : textComponents) {
                    player.sendMessage(textComponent);
                }
            }
        }

        IOSteinPolls.getInstance().getCurrentPoll().setCreator(null);
        IOSteinPolls.getInstance().getCurrentPoll().setQuestion(null);
        IOSteinPolls.getInstance().getCurrentPoll().setPositiveVotes(0);
        IOSteinPolls.getInstance().getCurrentPoll().setNegativeVotes(0);
        IOSteinPolls.getInstance().getCurrentPoll().setDuration(0);
        IOSteinPolls.getInstance().getCurrentPoll().setNegativeAnswer(null);
        IOSteinPolls.getInstance().getCurrentPoll().setPositiveAnswer(null);
        IOSteinPolls.getInstance().getCurrentPoll().setOnlyTeamAccess(false);


    }
}
