package de.api.devtools.scoreboard.score;

import de.api.devtools.scoreboard.builder.ScoreboardBuilder;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface IScore {
    int getScore();

    void setContent(String content);

    @NonNull String getPrefix();

    String getContent();

    @NonNull String getFullContent();

    @NonNull ScoreboardBuilder getScoreboardBuilder();

    void showScore();

    void hideScore();
}
