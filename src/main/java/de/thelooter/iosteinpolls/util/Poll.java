package de.thelooter.iosteinpolls.util;

import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.manager.PollManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class Poll implements Cloneable {
    private Player creator;
    private String question;
    private int duration = 0;
    private String positiveAnswer;
    private String negativeAnswer;
    private int positiveVotes;
    private int negativeVotes;
    private boolean onlyTeamAccess = false;

    private int taskID = 0;

    private final PollManager pollManager;

    public Poll(Player creator, String question, int duration, String positiveAnswer, String negativeAnswer,
                int positiveVotes, int negativeVotes, IOSteinPolls plugin) {
        this.creator = creator;
        this.question = question;
        this.duration = duration;
        this.positiveAnswer = positiveAnswer;
        this.negativeAnswer = negativeAnswer;
        this.positiveVotes = positiveVotes;
        this.negativeVotes = negativeVotes;

        pollManager = new PollManager(plugin);
    }

    public Poll(Poll poll, IOSteinPolls plugin) {
        this.creator = poll.getCreator();
        this.question = poll.getQuestion();
        this.duration = poll.getDuration();
        this.positiveAnswer = poll.getPositiveAnswer();
        this.negativeAnswer = poll.getNegativeAnswer();
        this.positiveVotes = poll.getPositiveVotes();
        this.negativeVotes = poll.getNegativeVotes();

        pollManager = new PollManager(plugin);
    }

    public Poll(IOSteinPolls plugin) {
        pollManager = new PollManager(plugin);
    }

    public Player getCreator() {
        return creator;
    }

    public String getQuestion() {
        return question;
    }

    public int getDuration() {
        return duration;
    }

    public String getPositiveAnswer() {
        return positiveAnswer;
    }

    public String getNegativeAnswer() {
        return negativeAnswer;
    }

    public int getPositiveVotes() {
        return positiveVotes;
    }

    public int getNegativeVotes() {
        return negativeVotes;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setCreator(Player creator) {
        this.creator = creator;

        if (creator != null) {
            pollManager.setCreator(this, creator);
        }
    }

    public void setQuestion(String question) {
        this.question = question;

        if (question != null) {
            pollManager.setQuestion(this, question);
        }
    }

    public void setDuration(int duration) {
        this.duration = duration;


    }

    public void setPositiveAnswer(String positiveAnswer) {
        this.positiveAnswer = positiveAnswer;

        if (positiveAnswer != null) {
            pollManager.setPositiveAnswer(this, positiveAnswer);

        }
    }

    public void setNegativeAnswer(String negativeAnswer) {
        this.negativeAnswer = negativeAnswer;
        if (negativeAnswer != null) {
            pollManager.setNegativeAnswer(this, negativeAnswer);

        }

    }

    public void setPositiveVotes(int positiveVotes) {
        this.positiveVotes = positiveVotes;

    }

    public void setNegativeVotes(int negativeVotes) {
        this.negativeVotes = negativeVotes;
        pollManager.setNegativeVotes(this, negativeVotes);
    }

    public boolean isOnlyTeamAccess() {
        return onlyTeamAccess;
    }

    public void setOnlyTeamAccess(boolean onlyTeamAccess) {
        this.onlyTeamAccess = onlyTeamAccess;

        pollManager.setAccess(this, onlyTeamAccess);
    }

    public void addVote(boolean positive) {
        if (positive) {
            positiveVotes++;
            pollManager.submitVote(this, true, creator);
        } else {
            negativeVotes++;
            pollManager.submitVote(this, false, creator);

        }
    }

    public void delete() {
        pollManager.deletePoll(this);
        this.question = null;
        this.creator = null;
        this.duration = 0;
        this.positiveAnswer = null;
        this.negativeAnswer = null;
        this.positiveVotes = 0;
        this.negativeVotes = 0;
        this.onlyTeamAccess = false;

    }

    public void endVote() {
        pollManager.endPoll(this);
        Bukkit.getScheduler().cancelTask(taskID);
    }

}

