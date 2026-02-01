package de.api.devtools.scoreboard.score;

import de.api.devtools.scoreboard.builder.ScoreboardBuilder;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface IScore<T> {
    int getScore();

    void setContent(T content);

    T getContent();

    @NonNull ScoreboardBuilder getScoreboardBuilder();

    void showScore();

    void hideScore();
}
