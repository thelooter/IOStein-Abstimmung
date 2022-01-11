package de.thelooter.iosteinpolls.util;

import org.bukkit.entity.Player;

import java.util.Objects;

public class Poll {
    private  Player creator;
    private  String question;
    private  int duration;
    private  String positiveAnswer;
    private  String negativeAnswer;
    private  int positiveVotes;
    private  int negativeVotes;

    public Poll(Player creator, String question, int duration, String positiveAnswer, String negativeAnswer,
                int positiveVotes, int negativeVotes) {
        this.creator = creator;
        this.question = question;
        this.duration = duration;
        this.positiveAnswer = positiveAnswer;
        this.negativeAnswer = negativeAnswer;
        this.positiveVotes = positiveVotes;
        this.negativeVotes = negativeVotes;
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

    public void setCreator(Player creator) {
        this.creator = creator;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPositiveAnswer(String positiveAnswer) {
        this.positiveAnswer = positiveAnswer;
    }

    public void setNegativeAnswer(String negativeAnswer) {
        this.negativeAnswer = negativeAnswer;
    }

    public void setPositiveVotes(int positiveVotes) {
        this.positiveVotes = positiveVotes;
    }

    public void setNegativeVotes(int negativeVotes) {
        this.negativeVotes = negativeVotes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Poll) obj;
        return Objects.equals(this.creator, that.creator) &&
                Objects.equals(this.question, that.question) &&
                this.duration == that.duration &&
                Objects.equals(this.positiveAnswer, that.positiveAnswer) &&
                Objects.equals(this.negativeAnswer, that.negativeAnswer) &&
                this.positiveVotes == that.positiveVotes &&
                this.negativeVotes == that.negativeVotes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creator, question, duration, positiveAnswer, negativeAnswer, positiveVotes, negativeVotes);
    }

    @Override
    public String toString() {
        return "Poll[" +
                "creator=" + creator + ", " +
                "question=" + question + ", " +
                "duration=" + duration + ", " +
                "positiveAnswer=" + positiveAnswer + ", " +
                "negativeAnswer=" + negativeAnswer + ", " +
                "positiveVotes=" + positiveVotes + ", " +
                "negativeVotes=" + negativeVotes + ']';
    }

}

