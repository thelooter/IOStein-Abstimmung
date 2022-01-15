package de.thelooter.iosteinpolls.manager;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.util.Poll;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void submitVote(Poll poll, boolean positive) {

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

}
