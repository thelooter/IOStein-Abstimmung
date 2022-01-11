package de.thelooter.iosteinpolls.manager;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.util.Poll;

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
                poll.setPositiveVotes(poll.getPositiveVotes() + 1);
                preparedStatement.setInt(1, poll.getPositiveVotes() + 1);
                preparedStatement.setInt(2, poll.getNegativeVotes());
            } else {

                preparedStatement.setInt(1, poll.getPositiveVotes());
                preparedStatement.setInt(2, poll.getNegativeVotes() + 1);
            }
            preparedStatement.setString(3, poll.getQuestion());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
