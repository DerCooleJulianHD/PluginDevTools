package de.api.devtools.scoreboard.score;

import de.api.devtools.scoreboard.builder.ScoreboardBuilder;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class SimpleScore implements IScore {

    private final ScoreboardBuilder builder;

    private final int id;
    private String content;

    public SimpleScore(@NonNull ScoreboardBuilder builder, @NonNull String content, int id) {
        this.builder = builder;
        this.content = content;
        this.id = id;
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
    public @NonNull String getPrefix() {
        final String prefix = content;
        return prefix.substring(0, 16);
    }

    @Override
    public String getContent() {
        final String suffix = content;
        return suffix.substring(16, suffix.length() -1);
    }

    @Override
    public @NonNull String getFullContent() {
        return content;
    }

    @Override
    public @NonNull ScoreboardBuilder getScoreboardBuilder() {
        return builder;
    }

    @Override
    public void showScore() {
        final Objective objective = builder.getMainObjective();

        if (objective.getScore(getFullContent()).isScoreSet())
            return;

        objective.getScore(getFullContent()).setScore(id);
    }

    @Override
    public void hideScore() {
        final Scoreboard scoreboard = builder.getBoard();
        final Objective objective = builder.getMainObjective();

        if (!objective.getScore(getFullContent()).isScoreSet())
            return;

        scoreboard.resetScores(getFullContent());
    }
}
