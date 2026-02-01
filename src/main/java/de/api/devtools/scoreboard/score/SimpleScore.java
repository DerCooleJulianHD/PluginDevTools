package de.api.devtools.scoreboard.score;

import de.api.devtools.scoreboard.builder.ScoreboardBuilder;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class SimpleScore implements IScore<String> {

    private final ScoreboardBuilder builder;

    private final int id;
    private String content;

    public SimpleScore(@NonNull ScoreboardBuilder builder, @NonNull String content, int id) {
        this.builder = builder;
        this.content = content;
        this.id = id;
        showScore();
    }

    @Override
    public int getScore() {
        return id;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public @NonNull ScoreboardBuilder getScoreboardBuilder() {
        return builder;
    }

    @Override
    public void showScore() {
        final Objective objective = builder.getMainObjective();

        if (objective.getScore(content).isScoreSet())
            return;

        objective.getScore(content).setScore(id);
    }

    @Override
    public void hideScore() {
        final Scoreboard scoreboard = builder.getBoard();
        final Objective objective = builder.getMainObjective();

        if (!objective.getScore(content).isScoreSet())
            return;

        scoreboard.resetScores(content);
    }
}
